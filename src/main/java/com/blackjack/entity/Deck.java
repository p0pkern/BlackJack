package com.blackjack.entity;

import java.util.List;

import com.blackjack.enums.Rank;
import com.blackjack.enums.Suit;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	private int size;
	
	public Deck() {
		this.size = 1;
	}
	
	public Deck(int size) {
		if(size < 1) this.size = 1;
		else this.size = size;
	}
	
	private List<Card> createADeck() {
		List<Card> newDeck = new ArrayList<>();
		
		for(Rank r: Rank.values()) {
			for(Suit s: Suit.values()) {
				newDeck.add(new Card(r,s));
			}
		}
		Collections.shuffle(newDeck);
		
		return newDeck;
	}
	
	public List<Card> getDeck() {
		List<Card> newDeck = new ArrayList<>();
		if(this.size == 1)
			return createADeck();
		else {
			for(int i = 0; i < this.size; i++) {
				newDeck.addAll(createADeck());
			}
		}
		
		Collections.shuffle(newDeck);
		return newDeck;
	}
}
