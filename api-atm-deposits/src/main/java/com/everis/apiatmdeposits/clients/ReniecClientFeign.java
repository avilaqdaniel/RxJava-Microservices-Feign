package com.everis.apiatmdeposits.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.everis.apiatmdeposits.model.entity.ValidateIn;
import com.everis.apiatmdeposits.model.entity.ValidationResponse;

import io.reactivex.Single;

@FeignClient(name = "api-reniec", url = "localhost:8003")
public interface ReniecClientFeign {

	@PostMapping("/external/reniec/validate")
	public Single<ValidationResponse> validateReniec(@RequestBody ValidateIn validateIn);
}
