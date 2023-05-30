package com.blackjack.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blackjack.entity.Card;
import com.blackjack.enums.Rank;
import com.blackjack.enums.Suit;
import com.blackjack.repository.CardRepository;

@Service
public class CardService {
	private final CardRepository cardRepository;
	private final Logger logger = LoggerFactory.getLogger(CardService.class);
	
	@Autowired
	public CardService(CardRepository cardRepository) {
		this.cardRepository = cardRepository;
	}
	
	public List<Card> getAllCards(){
		logger.info("Retrieving all cards");
		return cardRepository.findAll();
	}
	
	public void saveDeck(List<Card> deck) {
		logger.info("Saving deck");
		cardRepository.saveAll(deck);
	}
	
	public List<Card> generateDeck() {
		logger.info("Generating new deck");
		List<Card> deck = new ArrayList<>();
		
		for(Suit suit : Suit.values()) {
			for(Rank rank: Rank.values()) {
				Card card = new Card();
				card.setRank(rank);
				card.setSuit(suit);
				deck.add(card);
			}
		}
		
		Collections.shuffle(deck);
		
		return deck;
	}
	
	public void deleteAll() {
		logger.info("Deleting all cards");
		cardRepository.deleteAll();
	}
}
