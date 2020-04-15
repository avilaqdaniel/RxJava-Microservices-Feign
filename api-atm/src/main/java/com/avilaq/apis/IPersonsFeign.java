package com.avilaq.apis;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.avilaq.entities.PersonResponse;

@FeignClient(name = "api-persons", url = "localhost:8001")
public interface IPersonsFeign {

	@GetMapping("/core/persons")
	public PersonResponse getPersonByDocumentNumber(@RequestParam String documentNumber);
}
