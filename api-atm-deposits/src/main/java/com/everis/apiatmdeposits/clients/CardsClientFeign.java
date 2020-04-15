package com.everis.apiatmdeposits.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.everis.apiatmdeposits.model.entity.CardResponse;

import io.reactivex.Single;

@FeignClient(name = "api-cards", url = "localhost:8004")
public interface CardsClientFeign {

	@GetMapping("core/cards")
	public Single<CardResponse> getCardsByDocumentNumber(@RequestParam String documentNumber);
}
