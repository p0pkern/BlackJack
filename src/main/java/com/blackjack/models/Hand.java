package com.blackjack.models;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * Represents a hand in the game of Black Jack.
 */
@Entity
public class Hand {
	@Id
	private int id;
	
	private List<Integer> hand;
	private int score;
	private boolean bust;
	private boolean handWins;
	
	/**
	 * Default constructor
	 */
	public Hand() {
		hand = new ArrayList<>();
	}
	
	/**
	 * Constructs the hand with the specified parameters
	 * 
	 * @param id		the ID of the hand
	 * @param player	the player ID associated with the hand
	 * @param hand		the list of cards in the hand
	 * @param score		the current score of the hand
	 * @param bust		a flag indicating if the hand is bust (score exceeds a limit)
	 */
	public Hand(int id, List<Integer> hand, int score, boolean bust) {
		this.id = id;
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
	
	
	public boolean isHandWins() {
		return handWins;
	}

	public void setHandWins(boolean handWins) {
		this.handWins = handWins;
	}

	/**
	 * Draws a card and adds it to the hand.
	 * 
	 * @param the card to be added to the hand.
	 */
	public void drawCard(int card) {
		this.hand.add(card);
	}
}
