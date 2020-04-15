package com.avilaq.service;

import com.avilaq.entities.PersonResponse;

import io.reactivex.Single;

public interface IDepositService {

	public Single<PersonResponse> getPersonByDocumentNumber(String documentNumber);
}
