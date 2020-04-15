package com.everis.apireniec.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.everis.apireniec.model.entity.ReniecResponse;

import io.reactivex.Single;

@RestController
public class ReniecController {
	
	@PostMapping("/external/reniec/validate")
	public Single<ReniecResponse> validateReniec(@RequestBody String document) {
		ReniecResponse reniecResponse = new ReniecResponse();
		reniecResponse.setEntityName("Reniec");
		reniecResponse.setSuccess(true);
		return Single.just(reniecResponse);
	};
}
