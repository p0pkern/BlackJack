package com.blackjack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blackjack.entity.Card;
import com.blackjack.repository.BlackJackRepository;

@Service
public class BlackJackService {
	@Autowired
	private BlackJackRepository blackJackRepository;
	
	public List<Card> getSingleDeck() {
		return this.blackJackRepository.getDeck(1);
	}
}
