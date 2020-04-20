package com.everis.apiatmdeposits.model.service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.everis.apiatmdeposits.clients.AccountsClientFeign;
import com.everis.apiatmdeposits.clients.CardsClientFeign;
import com.everis.apiatmdeposits.clients.FingerprintClientFeign;
import com.everis.apiatmdeposits.clients.PersonClientFeign;
import com.everis.apiatmdeposits.clients.ReniecClientFeign;
import com.everis.apiatmdeposits.exception.BlacklistedPersonException;
import com.everis.apiatmdeposits.exception.PersonNotFoundException;
import com.everis.apiatmdeposits.model.entity.AccountResponse;
import com.everis.apiatmdeposits.model.entity.Card;
import com.everis.apiatmdeposits.model.entity.CardResponse;
import com.everis.apiatmdeposits.model.entity.DepositIn;
import com.everis.apiatmdeposits.model.entity.DepositResponse;
import com.everis.apiatmdeposits.model.entity.PersonResponse;
import com.everis.apiatmdeposits.model.entity.ValidAccount;
import com.everis.apiatmdeposits.model.entity.ValidateIn;
import com.everis.apiatmdeposits.model.entity.ValidationResponse;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

@Service
public class DepositServiceImpl implements IDepositService{	
	
	public static final Logger log = LoggerFactory.getLogger(DepositServiceImpl.class);

	@Autowired
	private PersonClientFeign clientFeignPerson;
	@Autowired
	private FingerprintClientFeign clientFeignFingerprint;
	@Autowired
	private ReniecClientFeign clientFeignReniec;
	@Autowired
	private CardsClientFeign clientFeignCards;
	@Autowired
	private AccountsClientFeign clientFeignAccounts;

	@Override
	public Single<DepositResponse> depositAmount(DepositIn depositIn) {
		return Single.just(clientFeignPerson.getPersonByDocumentNumber(depositIn.getDocumentNumber()))
				.filter(this::validateIfPersonNotFound)
				.map(ResponseEntity::getBody)
				.filter(personResponse -> validateBlacklist(personResponse.isBlacklist()))
				.flatMap(this::validateDocumentNumber)
				.filter(ValidationResponse::isSuccess)
				.map(ValidationResponse::getEntityName)
				.zipWith(
						getAccounts().apply(depositIn.getDocumentNumber()),
						(entityName, accountResponses) -> parseAtmDepositResponse(entityName, accountResponses, depositIn.getAmount()))
				.toSingle();
				
	}
	
	private boolean validateIfPersonNotFound(ResponseEntity<PersonResponse> response) throws PersonNotFoundException{
		if(response.getStatusCodeValue() == HttpStatus.NOT_FOUND.value()) {
			throw new PersonNotFoundException();
		}
		return true;
	}
	
	private boolean validateBlacklist(boolean isBlacklist) throws BlacklistedPersonException{
		if(isBlacklist) throw new BlacklistedPersonException();
		return true;
	}
	
	private Maybe<ValidationResponse> validateDocumentNumber(PersonResponse person){
		ValidateIn validateIn = new ValidateIn(person.getDocument());
		if (person.isFingerprint()) {
			return Single.just(clientFeignFingerprint.validateFingerprint(validateIn))
					.map(response -> parseValidationResponse(response.getEntityName(), response.isSuccess()))
					.toMaybe();
		}else {
			return Single.just(clientFeignReniec.validateReniec(validateIn))
					.map(response -> parseValidationResponse(response.getEntityName(), response.isSuccess()))
					.toMaybe();
		}
	}
	
	private ValidationResponse parseValidationResponse(String enityName, boolean success) {
		return new ValidationResponse(enityName,success);
	}
	
	private Function<String, Maybe<List<AccountResponse>>> getAccounts(){
		return documentNumber -> Single.just(clientFeignCards.getCardsByDocumentNumber(documentNumber))
				.doOnSuccess(cardResponse -> log.info("Card --> {}", cardResponse.getCards().size()))
				.toObservable()
				.flatMapIterable(CardResponse::getCards)
				.filter(Card::getActive)
				.flatMap(card -> Single.just(clientFeignAccounts.getAccountNumber(card.getCardNumber()))
						.toObservable()
						.subscribeOn(Schedulers.io()))
				.toList()
				.toMaybe();
				
	}
	
	private DepositResponse parseAtmDepositResponse(String entityName, List<AccountResponse> accountResponses, double amount) {
		return new DepositResponse(
				entityName,
				getValidAccounts(accountResponses),
				getCustomerAmount(accountResponses, amount));
	}
	
	private List<ValidAccount> getValidAccounts(List<AccountResponse> accountResponses){
		return accountResponses.stream()
				.map(account -> new ValidAccount(account.getAccountNumber()))
				.collect(Collectors.toList());
	}
	
	private double getCustomerAmount(List<AccountResponse> accountResponses, double amount) {
		double sumAmount = accountResponses.stream()
				.map(AccountResponse::getAmount)
				.mapToDouble(Double::doubleValue)
				.sum();
		return sumAmount + amount;
	}

	@Override
	public Single<ResponseEntity<PersonResponse>> getPerson(String document) {
		return Single.just(clientFeignPerson.getPersonByDocumentNumber(document));
	}

}
