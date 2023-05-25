package com.blackjack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.blackjack.service.CardService;
import com.blackjack.service.HandService;
import com.blackjack.entity.Card;
import com.blackjack.entity.Hand;

@Controller
public class BlackJackController {
	private final CardService cardService;
	private final HandService handService;
	
	@Autowired
	public BlackJackController(CardService cardService, HandService handService) {
		this.cardService = cardService;
		this.handService = handService;
	}
	
	@GetMapping("/")
	public String getCards(Model model) {
		List<Card> deck = cardService.getAllCards();
		Hand dealer = handService.getHand(0L);
		
		if(deck.isEmpty()) {
			deck = cardService.generateDeck();
			cardService.saveDeck(deck);
		}
		
		model.addAttribute("deck", deck);
		return "index";
	}
}
