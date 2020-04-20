package com.everis.apiaccounts.service;

import org.springframework.stereotype.Service;

import com.everis.apiaccounts.model.entity.AccountResponse;

import io.reactivex.Single;

@Service
public class AccountServiceImpl implements IAccountService{

	@Override
	public Single<AccountResponse> findAccountsByCardNumber(String cardNumber) {
		return Single.fromCallable(() -> getAccounts(cardNumber));
	}

	private AccountResponse getAccounts(String cardNumber) {
		try {
			Thread.sleep(5000);
			return new AccountResponse(getAccountNumber(cardNumber),getAmount(cardNumber));
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
		return null;
	}
	
	private String getAccountNumber(String cardNumber) {
		return cardNumber.concat("XXX");
	}
	
	private double getAmount(String cardNumber) {
		switch(cardNumber) {
			case "1111222233334441": return 1000;
			case "1111222233334442": return 500;
			case "1111222233334443": return 1500;
			default: return 200;
		}
	}
}
