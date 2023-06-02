package com.blackjack.models;

import com.blackjack.enums.Rank;
import com.blackjack.enums.Suit;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Hand hand;
	
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
	
	public String convertRank() {
		switch(this.rank) {
		case TWO:
			return "2";
		case THREE:
			return "3";
		case FOUR:
			return "4";
		case FIVE:
			return "5";
		case SIX:
			return "6";
		case SEVEN:
			return "7";
		case EIGHT:
			return "8";
		case NINE:
			return "9";
		case TEN:
			return "10";
		case JACK:
			return "J";
		case QUEEN:
			return "Q";
		case KING:
			return "K";
		case ACE:
			return "A";
		default:
			return "?";
		}
	}
	
	public String convertSuit() {
		switch(this.suit){
		case DIAMOND:
			return "♦";
		case SPADE:
			return "♠︎";
		case HEART:
			return "♥︎";
		case CLUB:
			return "♣";
		default:
			return "?";
		}
	}
	
	
	@Override
	public String toString() {
		return "id=" + this.id;
	}
}
