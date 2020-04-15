package com.everis.apiaccounts.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.everis.apiaccounts.model.entity.AccountResponse;

import io.reactivex.Single;

@RestController
public class AccountController {

	@GetMapping("/core/accounts")
	public Single<AccountResponse> getAccountNumber(@RequestParam String cardNumber){
		List<AccountResponse> accounts = new ArrayList<AccountResponse>();
		accounts.add(new AccountResponse("1111222233334441XXX",1000.0));
		accounts.add(new AccountResponse("1111222233334442XXX",500.0));
		accounts.add(new AccountResponse("1111222233334443XXX",1500.0));
		
		return Single.just(accounts.stream()
				.filter(a -> a.getAccountNumber().substring(0, 16).equalsIgnoreCase(cardNumber))
				.findAny()
				.orElse(new AccountResponse("nulo", 10.0)))
				.delay(5, TimeUnit.SECONDS);
	}
}
