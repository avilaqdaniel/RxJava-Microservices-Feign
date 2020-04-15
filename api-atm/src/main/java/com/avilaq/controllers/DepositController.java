package com.avilaq.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avilaq.entities.PersonResponse;
import com.avilaq.service.IDepositService;

import io.reactivex.Single;

@RestController
public class DepositController {

	@Autowired
	private IDepositService service;
	
	@GetMapping("/persons")
	public Single<PersonResponse> getPersonByDocumentNumber(@RequestParam String documentNumber){
		return service.getPersonByDocumentNumber(documentNumber);
	}
}
