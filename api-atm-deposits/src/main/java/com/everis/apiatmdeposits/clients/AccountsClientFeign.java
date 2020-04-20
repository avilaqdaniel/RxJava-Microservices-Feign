package com.everis.apiatmdeposits.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.everis.apiatmdeposits.model.entity.AccountResponse;

import io.reactivex.Single;

@FeignClient(name = "api-accounts", url = "localhost:8000")
public interface AccountsClientFeign {

	@GetMapping("/core/accounts")
	public AccountResponse getAccountNumber(@RequestParam String cardNumber);
}
