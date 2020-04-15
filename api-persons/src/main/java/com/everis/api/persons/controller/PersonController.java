package com.everis.api.persons.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.everis.api.persons.model.entity.Person;
import com.everis.api.persons.model.service.IPersonService;

import io.reactivex.Single;

@RestController
@RequestMapping(value = "/core/persons")
public class PersonController {
	
	@Autowired
	private IPersonService personService;

	@GetMapping
	public Single<Person> getPersonByDocumentNumber(@RequestParam String documentNumber) {
		return personService.findByDocument(documentNumber);
	}
	
	@GetMapping("/all")
	public List<Person> list(){
		return personService.findAll();
	}
		
}
