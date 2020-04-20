package com.everis.apiatmdeposits.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.everis.apiatmdeposits.model.entity.DepositIn;
import com.everis.apiatmdeposits.model.entity.DepositResponse;
import com.everis.apiatmdeposits.model.entity.PersonResponse;
import com.everis.apiatmdeposits.model.service.IDepositService;

import io.reactivex.Single;

@RestController
public class DepositController{
	

	public static final Logger log = LoggerFactory.getLogger(DepositController.class);

	@Autowired
	private IDepositService depositService;	
	
	@PostMapping(value = "/atm/deposits",
				consumes = MediaType.APPLICATION_JSON_VALUE,
				produces = MediaType.APPLICATION_JSON_VALUE)
	public Single<DepositResponse> saveDeposit(@RequestBody DepositIn depositIn) {
		return depositService.depositAmount(depositIn);
	}
	
	@GetMapping("/atm/deposits/person")
	public Single<ResponseEntity<PersonResponse>> getPerson (@RequestParam String document){
		log.info("documento --> {}",document);
		return depositService.getPerson(document);
	}
	
}
