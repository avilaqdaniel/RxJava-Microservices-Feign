package com.everis.apiaccounts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.everis.apiaccounts.model.entity.AccountResponse;
import com.everis.apiaccounts.service.IAccountService;

import io.reactivex.Single;

@RestController
public class AccountController {

	@Autowired
	private IAccountService accountService;
	
	@GetMapping("/core/accounts")
	public Single<AccountResponse> getAccountNumber(@RequestParam String cardNumber) {
		return accountService.findAccountsByCardNumber(cardNumber);
	}
}
