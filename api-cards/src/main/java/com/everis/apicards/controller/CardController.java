package com.everis.apicards.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.everis.apicards.model.entity.Card;
import com.everis.apicards.model.entity.CardResponse;

import io.reactivex.Single;

@RestController
public class CardController {	
	
	@GetMapping("core/cards")
	public Single<CardResponse> getCardsByDocumentNumber(@RequestParam String documentNumber) {
		CardResponse cards = new CardResponse();
		List<Card> lista = new ArrayList<>();
		lista.add(new Card("1111222233334441",true));
		lista.add(new Card("1111222233334442",true));
		lista.add(new Card("1111222233334443",true));
		lista.add(new Card("1111222233334444",false));
		lista.add(new Card("1111222233334445",false));
		lista.add(new Card("1111222233334446",false));
		cards.setCards(lista);		
		return Single.just(cards);
	};

}
