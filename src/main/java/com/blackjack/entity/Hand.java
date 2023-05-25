package com.blackjack.entity;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Hand {
	@Id
	private int id;
	
	private int player;
	private List<Integer> hand;
	private int score;
	private boolean bust;
	
	public Hand() {
		hand = new ArrayList<>();
	}
	
	public Hand(int id, int player, List<Integer> hand, int score, boolean bust) {
		this.id = id;
		this.player = player;
		this.hand = hand;
		this.score = score;
		this.bust = bust;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Integer> getHand() {
		return hand;
	}

	public void setHand(List<Integer> hand) {
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
	
	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}
	
	public void drawCard(int card) {
		this.hand.add(card);
	}
}
