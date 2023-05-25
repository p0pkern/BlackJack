package com.blackjack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blackjack.entity.Hand;
import com.blackjack.repository.HandRepository;

@Service
public class HandService {
	private final HandRepository handRepository;
	
	@Autowired
	public HandService(HandRepository handRepository) {
		this.handRepository = handRepository;
	}
	
	public Hand getHand(Long id) {
		return handRepository.getReferenceById(id);
	}
	
	public void saveHand(Hand hand) {
		handRepository.save(hand);
	}
}
