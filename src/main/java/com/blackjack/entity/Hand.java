package com.blackjack.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Hand {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private List<Card> hand;
	private int score;
	private boolean bust;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Card> getHand() {
		return hand;
	}

	public void setHand(List<Card> hand) {
		this.hand = hand;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isBust() {
		return bust;
	}

	public void setBust(boolean bust) {
		this.bust = bust;
	}
	
	public void drawCard(Card card) {
		
	}
}
