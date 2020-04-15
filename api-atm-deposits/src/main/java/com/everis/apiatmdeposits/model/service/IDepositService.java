package com.everis.apiatmdeposits.model.service;

import org.springframework.http.ResponseEntity;

import com.everis.apiatmdeposits.model.entity.DepositIn;
import com.everis.apiatmdeposits.model.entity.DepositResponse;
import com.everis.apiatmdeposits.model.entity.PersonResponse;

import io.reactivex.Single;

public interface IDepositService {
	
	/*Single<DepositResponse> depositAmount(DepositIn depositIn);*/
	
	public Single<PersonResponse> getPerson(String document);
	
}
