package com.blackjack.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blackjack.entity.Card;
import com.blackjack.enums.Rank;
import com.blackjack.enums.Suit;
import com.blackjack.repository.CardRepository;

@Service
public class CardService {
	private final CardRepository cardRepository;
	
	@Autowired
	public CardService(CardRepository cardRepository) {
		this.cardRepository = cardRepository;
	}
	
	public List<Card> getAllCards(){
		return cardRepository.findAll();
	}
	
	public void saveDeck(List<Card> deck) {
		cardRepository.saveAll(deck);
	}
	
	public List<Card> generateDeck() {
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
