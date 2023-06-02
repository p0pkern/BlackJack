package com.blackjack.service;

import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blackjack.enums.Rank;
import com.blackjack.exceptions.NoCardExistsException;
import com.blackjack.models.Card;
import com.blackjack.models.Hand;
import com.blackjack.models.ScoreCard;
import com.blackjack.repository.HandRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class HandService {
	private final HandRepository handRepository;
	private final CardService cardService;
	private final Logger logger = LoggerFactory.getLogger(HandService.class);
	
	@Autowired
	public HandService(HandRepository handRepository, CardService cardService) {
		this.handRepository = handRepository;
		this.cardService = cardService;
	}
	
	public Hand getHand(Long id) {
		logger.info("Retrieving hand from database id: {}", id);
		boolean exists = handRepository.existsById(id);
		
		Hand hand = null;
		
		if(exists) {
			hand = handRepository.getReferenceById(id);
		} else {
			hand = new Hand();
			hand.setId(1L);
			saveHand(hand);
		}
		
		return hand;
	}
	
	public void saveHand(Hand hand) {
		logger.info("Saving hand to database id:{} ", hand.getId());
		handRepository.save(hand);
	}
	
	public void scoreHand(Hand currPlayer) {
		List<Card> aces = new ArrayList<>();
		for(Card card: currPlayer.getHand()) {
			if(card.getRank() == Rank.ACE)
				aces.add(card);
		}
		
		int score = 0;
		
		for(Card card: currPlayer.getHand()) {
			if(card.getRank() != Rank.ACE) {
				score += ScoreCard.score(card, score);
			}
		}
		
		for(Card card: aces) {
			score += ScoreCard.score(card, score);
		}
		
		currPlayer.setBust(ScoreCard.isBust(score));
		currPlayer.setScore(score);
	}
	
	public void drawACard(Hand currPlayer, Long cardId) throws NoCardExistsException {
		Card card = cardService.getCard(cardId);
		currPlayer.drawCard(card);
		saveHand(currPlayer);
	}
	
}
