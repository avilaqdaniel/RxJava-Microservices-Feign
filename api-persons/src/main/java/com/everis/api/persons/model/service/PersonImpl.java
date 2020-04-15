package com.everis.api.persons.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.everis.api.persons.model.dao.PersonDao;
import com.everis.api.persons.model.entity.Person;

import io.reactivex.Single;

@Service
public class PersonImpl implements IPersonService{

	@Autowired
	private PersonDao personDao;
	
	@Override
	@Transactional(readOnly = true)
	public Single<Person> findByDocument(String document) {
		return Single.just(personDao.findByDocument(document));
	}

	@Override
	public List<Person> findAll() {
		return personDao.findAll();
	}

}
