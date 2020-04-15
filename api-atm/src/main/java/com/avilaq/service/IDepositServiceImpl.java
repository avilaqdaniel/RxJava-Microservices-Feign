package com.avilaq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avilaq.apis.IPersonsFeign;
import com.avilaq.entities.PersonResponse;

import io.reactivex.Single;

@Service
public class IDepositServiceImpl implements IDepositService{

	@Autowired
	private IPersonsFeign feignPersons; 
	
	
	@Override
	public Single<PersonResponse> getPersonByDocumentNumber(String documentNumber) {
		return Single.just(feignPersons.getPersonByDocumentNumber(documentNumber));
	}

	
	
}
