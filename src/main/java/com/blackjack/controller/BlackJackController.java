package com.blackjack.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.blackjack.service.CardService;
import com.blackjack.service.HandService;

import com.blackjack.entity.Card;
import com.blackjack.entity.Hand;

@Controller
public class BlackJackController{
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
		Hand dealer = handService.getHand(1);;
		Hand player = null;
		
		if(deck.isEmpty()) {
			deck = cardService.generateDeck();
			cardService.saveDeck(deck);
		}
		
		dealer.drawCard(1);
		dealer.drawCard(2);
		
		List<Integer> dealerCards = dealer.getHand();
		
		List<Card> dealerHand = new ArrayList<>();
		
		for(int c: dealerCards) {
			dealerHand.add(deck.get(c - 1));
		}
		System.out.println(dealerCards);
		
		model.addAttribute("deck", deck);
		model.addAttribute("dealerHand", dealerHand);
		model.addAttribute("player", player);
		return "index";
	}
}
