package com.everis.apiaccounts.service;

import com.everis.apiaccounts.model.entity.AccountResponse;

import io.reactivex.Single;

public interface IAccountService {

	Single<AccountResponse> findAccountsByCardNumber(String cardNumber);
}
