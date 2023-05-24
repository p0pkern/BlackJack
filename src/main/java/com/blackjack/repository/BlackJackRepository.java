package com.blackjack.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blackjack.entity.Card;
import com.blackjack.entity.Deck;

@Repository
public class BlackJackRepository {
	
	public List<Card> getDeck(int size) {
		Deck deck = new Deck();
		return deck.getDeck(size);
	}
}
