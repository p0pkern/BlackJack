package com.blackjack.entity;

import com.blackjack.enums.Rank;
import com.blackjack.enums.Suit;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
/**
 * The Card class represents a single card from the standard 52 card, card deck.
 * e.g. the ones that Bicycle has produced.
 * 
 * Key Functionality:
 * - Id will be randomly generated and use to indicate position of card in the deck.
 * - rank will be used for score keeping utilizing the ScoreCard class
 * - suit will be used for UI purposes and ensuring the correct amount of cards are in a deck.
 * 
 * Example Usage:
 * Card card = new Card();
 * Card card = new Card(Rank.TWO, Suit.CLUB) // See Rank and Suit Enums for explanations.
 * 
 *
 */
@Entity
public class Card {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Rank rank;
	private Suit suit;
	
	public Card() {
		
	}
	
	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public Suit getSuit() {
		return suit;
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

}
