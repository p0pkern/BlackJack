package com.blackjack.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.blackjack.enums.Rank;
import com.blackjack.enums.Suit;
import com.blackjack.models.Card;

public class DeckService {
	private final Logger logger = LoggerFactory.getLogger(DeckService.class);
	
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
}
