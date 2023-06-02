package com.blackjack.models;

/**
 * The ScoreCard class provides methods for calculating the score of a card in a
 * game. It assigns a score value to each rank of the card based on the game
 * rules.
 */
public class ScoreCard {
	/**
	 * Calculates the score value of a given card.
	 *
	 * @param card      the card to calculate the score for
	 * @param currScore the current score of the hand
	 * @return the score value of the card
	 */
	public static int score(Card card, int currScore) {

		switch (card.getRank()) {
		case TWO:
			return 2;
		case THREE:
			return 3;
		case FOUR:
			return 4;
		case FIVE:
			return 5;
		case SIX:
			return 6;
		case SEVEN:
			return 7;
		case EIGHT:
			return 8;
		case NINE:
			return 9;
		case TEN:
			return 10;
		case JACK:
			return 10;
		case KING:
			return 10;
		case QUEEN:
			return 10;
		case ACE: {
			if (11 + currScore > 21)
				return 1;
			return 11;
		}
		default:
			return 0;
		}
	}

	public static boolean isBust(Card card, int currScore) {
		return currScore + score(card, currScore) > 21;
	}

	public static boolean isBust(int currScore) {
		return currScore > 21;
	}

	public static boolean isBlackJack(int currScore) {
		return currScore == 21;
	}
	
	
	public static void checkForWinner(Hand dealer, Hand player, boolean stand) {
		// Check for bust
		if (dealer.isBust()) {
			player.setHandWins(true);
		} else if (!dealer.isBust() && player.isBust()) {
			dealer.setHandWins(true);
		} else if (stand && dealer.getScore() > player.getScore()) {
			dealer.setHandWins(true);
		} else if (stand && dealer.getScore() <= player.getScore()) {
			player.setHandWins(true);
		}

		if (ScoreCard.isBlackJack(player.getScore())) {
			player.setHandWins(true);
			dealer.setHandWins(false);
		} else if (ScoreCard.isBlackJack(dealer.getScore())) {
			dealer.setHandWins(true);
			player.setHandWins(false);
		}

	}
}
