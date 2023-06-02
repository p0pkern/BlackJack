package com.blackjack.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blackjack.enums.Rank;
import com.blackjack.enums.Suit;
import com.blackjack.models.Card;
import com.blackjack.repository.CardRepository;

@Service
public class DeckService {
	private final CardRepository cardRepository;
	private final Logger logger = LoggerFactory.getLogger(DeckService.class);
	
	@Autowired
	public DeckService(CardRepository cardRepository) {
		this.cardRepository = cardRepository;
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
		
		cardRepository.saveAll(deck);
		
		return deck;
	}
	
	public List<Card> getDeck(){
		logger.info("Verifying if there are any cards in the database");
		
		if(!cardRepository.existsBy()) {
			generateDeck();
		}
		
		return cardRepository.findAll();
	}
	
	public void saveDeck(List<Card> deck) {
		logger.info("Saving deck");
		cardRepository.saveAll(deck);
	}
	
	public void deleteAll() {
		logger.info("Clearing database of current cards.");
		cardRepository.deleteAll();
	}
}
