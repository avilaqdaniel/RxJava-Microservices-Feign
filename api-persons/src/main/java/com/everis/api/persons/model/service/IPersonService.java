package com.everis.api.persons.model.service;

import java.util.List;

import com.everis.api.persons.model.entity.Person;

import io.reactivex.Single;

public interface IPersonService {

	public Single<Person> findByDocument(String document);
	
	public List<Person> findAll();
}
