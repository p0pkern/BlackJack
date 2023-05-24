package com.blackjack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.blackjack.service.CardService;
import com.blackjack.entity.Card;

@Controller
public class CardController {
	private final CardService cardService;
	
	@Autowired
	public CardController(CardService cardService) {
		this.cardService = cardService;
	}
	
	@GetMapping("/")
	public String getCards(Model model) {
		List<Card> deck = cardService.getAllCards();
		
		if(deck.isEmpty()) {
			deck = cardService.generateDeck();
			cardService.saveDeck(deck);
		}
		
		model.addAttribute("deck", deck);
		return "index";
	}
}
