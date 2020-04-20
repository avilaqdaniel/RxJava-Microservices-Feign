package com.everis.apiatmdeposits.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.everis.apiatmdeposits.model.entity.PersonResponse;

@FeignClient(name = "api-persons", url = "localhost:8001")
public interface PersonClientFeign {
	
	@GetMapping("/core/persons")
	public ResponseEntity<PersonResponse> getPersonByDocumentNumber(@RequestParam String documentNumber);
	
	@GetMapping("/core/persons/all")
	public List<PersonResponse> list();

}
