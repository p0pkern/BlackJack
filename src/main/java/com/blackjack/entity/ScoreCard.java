package com.blackjack.entity;

public class ScoreCard {
	public static int score(Card card, int currScore) {
		switch(card.getRank()) {
		case TWO: return 2;
		case THREE: return 3;
		case FOUR: return 4;
		case FIVE: return 5;
		case SIX: return 6;
		case SEVEN: return 7;
		case EIGHT: return 8;
		case NINE: return 9;
		case TEN: return 10;
		case JACK: return 10;
		case KING: return 10;
		case QUEEN: return 10;
		case ACE: {
			if(11 + currScore > 21)
				return 1;
			return 11;
		}
		default: return 0;
		}
	}
}
