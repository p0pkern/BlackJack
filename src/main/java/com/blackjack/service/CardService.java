package com.blackjack.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blackjack.enums.Rank;
import com.blackjack.enums.Suit;
import com.blackjack.models.Card;
import com.blackjack.repository.CardRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CardService {
	private final CardRepository cardRepository;
	private final DeckService deckService;
	private final Logger logger = LoggerFactory.getLogger(CardService.class);
	
	@Autowired
	public CardService(CardRepository cardRepository,
					   DeckService deckService) {
		this.cardRepository = cardRepository;
		this.deckService = deckService;
	}
	
	
	public List<Card> getDeck(){
		logger.info("Verifying if there are any cards in the database");
		
		if(!cardRepository.existsAny()) {
			generateDeck();
		}
		
		return cardRepository.findAll();
	}
	
	public void saveDeck(List<Card> deck) {
		logger.info("Saving deck");
		cardRepository.saveAll(deck);
	}
	
	public void deleteAll() {
		logger.info("Deleting all cards");
		cardRepository.deleteAll();
	}
	
	public void generateDeck() {
		List<Card> deck = deckService.generateDeck();
		cardRepository.saveAll(deck);
	}
}
