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
	
	private List<Card> getCurrentDeck() {
		List<Card> deck = cardService.getAllCards();
		
		if(deck.isEmpty()) {
			deck = cardService.generateDeck();
			cardService.saveDeck(deck);
		}
		
		return deck;
	}
	
	private List<Card> getCurrentHand(List<Integer> hand, List<Card> deck) {
		List<Card> convertedHand = new ArrayList<>();
		
		for(int c: hand) {
			convertedHand.add(deck.get(c));
		}
		
		return convertedHand;
	}
	
	private Hand getCurrentHand(int player) {
		return handService.getHand(player);
	}
	
	@GetMapping("/")
	public String getCards(Model model) {
		List<Card> deck = getCurrentDeck();
		Hand dealer = getCurrentHand(1);
		Hand player = getCurrentHand(2);

		dealer.drawCard(0);
		dealer.drawCard(1);
		
		player.drawCard(2);
		player.drawCard(3);
		
		List<Card> dealerHand = getCurrentHand(dealer.getHand(), deck);
		List<Card> playerHand = getCurrentHand(player.getHand(), deck);
		
		handService.saveHand(player);
		handService.saveHand(dealer);
		
		int dealerScore = 10;
		int playerScore = 20;
		
		model.addAttribute("deck", deck);
		model.addAttribute("dealerHand", dealerHand);
		model.addAttribute("dealerScore", dealerScore);
		model.addAttribute("playerHand", playerHand);
		model.addAttribute("playerScore", playerScore);
		
		return "index";
	}
}
