package com.blackjack.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.blackjack.enums.Rank;
import com.blackjack.enums.Suit;
import com.blackjack.entity.Card;
import com.blackjack.entity.ScoreCard;

class ScoreCardTest {

	@Test
	void testRankOfTwoReturnsTwo() {
		Card card = new Card(Rank.TWO, Suit.CLUB);
		int currScore = 0;
		
		assertEquals(2, ScoreCard.score(card, currScore));
	}
	
	@Test
	void testRankofKingReturnsTen() {
		Card card = new Card(Rank.KING, Suit.CLUB);
		int currScore = 0;
		
		assertEquals(10, ScoreCard.score(card, currScore));
	}
	
	@Test
	void testRankofAceReturnsEleven() {
		Card card = new Card(Rank.ACE, Suit.CLUB);
		int currScore = 0;
		
		assertEquals(11, ScoreCard.score(card, currScore));
	}
	
	@Test
	void testRankofAceReturnsOne() {
		Card card = new Card(Rank.ACE, Suit.CLUB);
		int currScore = 11;
		
		assertEquals(1, ScoreCard.score(card, currScore));
	}
	
	@Test
	void testBustOfCard() {
		Card card = new Card(Rank.KING, Suit.CLUB);
		int score = 18;
		
		assertTrue(ScoreCard.isBust(card, score));
	}
	
	@Test
	void testCardIsNotBust() {
		Card card = new Card(Rank.ACE, Suit.CLUB);
		int score = 11;
		assertFalse(ScoreCard.isBust(card, score));
	}

}
