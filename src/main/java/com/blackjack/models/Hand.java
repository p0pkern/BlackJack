package com.blackjack.models;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

/**
 * Represents a hand in the game of Black Jack.
 */
@Entity
public class Hand {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "hand_id")
	private List<Card> hand;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Player player;
	
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
	public Hand(Long id, List<Card> hand, int score, boolean bust) {
		this.id = id;
		this.hand = hand;
		this.score = score;
		this.bust = bust;
	}
	
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
	
	
	public boolean isHandWins() {
		return handWins;
	}

	public void setHandWins(boolean handWins) {
		this.handWins = handWins;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * Draws a card and adds it to the hand.
	 * 
	 * @param the card to be added to the hand.
	 */
	public void drawCard(Card card) {
		this.hand.add(card);
	}
}
