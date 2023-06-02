package com.blackjack.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blackjack.models.Card;
import com.blackjack.repository.CardRepository;

@Service
public class CardService {
	private final CardRepository cardRepository;
	
	@Autowired
	public CardService(CardRepository cardRepository) {
		this.cardRepository = cardRepository;
	}
	
	public Card getCard(Long cardId) {
		Optional<Card> cardOptional = cardRepository.findById(cardId);
		return cardOptional.orElse(null);
	}
}
