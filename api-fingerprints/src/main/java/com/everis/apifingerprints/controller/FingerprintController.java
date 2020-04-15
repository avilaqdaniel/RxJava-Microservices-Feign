package com.everis.apifingerprints.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.everis.apifingerprints.model.entity.FingerprintResponse;

import io.reactivex.Single;

@RestController
public class FingerprintController {
	
	@PostMapping("/core/fingerprints/validate")
	public Single<FingerprintResponse> validateFingerprint(@RequestBody String document) {
		FingerprintResponse fingerprintResponse =  new FingerprintResponse();
		fingerprintResponse.setEntityName("Core");
		fingerprintResponse.setSuccess(true);
		return Single.just(fingerprintResponse);
	};
}
