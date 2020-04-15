package com.everis.apiatmdeposits.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.everis.apiatmdeposits.model.entity.ValidateIn;
import com.everis.apiatmdeposits.model.entity.ValidationResponse;

import io.reactivex.Single;

@FeignClient(name = "api-fingerprints", url = "localhost:8002")
public interface FingerprintClientFeign {
	
	@PostMapping("/core/fingerprints/validate")
	public Single<ValidationResponse> validateFingerprint(@RequestBody ValidateIn validateIn);

}
