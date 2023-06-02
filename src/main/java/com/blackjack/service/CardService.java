package com.blackjack.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blackjack.exceptions.NoCardExistsException;
import com.blackjack.models.Card;
import com.blackjack.repository.CardRepository;

import jakarta.transaction.Transactional;

@Service
public class CardService {
	private final CardRepository cardRepository;
	
	@Autowired
	public CardService(CardRepository cardRepository) {
		this.cardRepository = cardRepository;
	}
	
	public Card getCard(Long cardId) throws NoCardExistsException {
		Optional<Card> cardOptional = cardRepository.findById(cardId);
		if(!cardOptional.isPresent())
			throw new NoCardExistsException("Card with id: " + cardId + " does not exist.");
		
		return cardOptional.get();
	}
	
	public void printCardIds() {
		List<Card> cards = cardRepository.findAll();
		for(Card card: cards) {
			System.out.println("Card ID: " + card.getId());
		}
	}
}
