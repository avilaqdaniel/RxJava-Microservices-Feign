package com.everis.api.persons.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.everis.api.persons.model.entity.Person;

public interface PersonDao extends JpaRepository<Person, Long> {

	Person findByDocument(String document);
}
