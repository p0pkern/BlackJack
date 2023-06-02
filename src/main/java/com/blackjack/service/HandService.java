package com.blackjack.service;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blackjack.models.Hand;
import com.blackjack.repository.HandRepository;

import jakarta.persistence.EntityManager;

@Service
public class HandService {
	private final HandRepository handRepository;
	private final Logger logger = LoggerFactory.getLogger(HandService.class);
	
	@Autowired
	public HandService(HandRepository handRepository) {
		this.handRepository = handRepository;
	}
	
	public Hand getHand(int id) {
		logger.info("Retrieving hand from database id: {}", id);
		boolean exists = handRepository.existsById(id);
		
		Hand hand = null;
		
		if(exists) {
			hand = handRepository.getReferenceById(id);
		} else {
			hand = new Hand();
			hand.setId(1);
			saveHand(hand);
		}
		
		return hand;
	}
	
	public void saveHand(Hand hand) {
		logger.info("Saving hand to database id:{} ", hand.getId());
		handRepository.save(hand);
	}
	
	public void deleteAll() {
		logger.info("Deleting all hands");
		handRepository.deleteAll();
	}
}
