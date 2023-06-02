package com.blackjack.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.intThat;

import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;

import com.blackjack.enums.Rank;
import com.blackjack.enums.Suit;
import com.blackjack.models.Card;
import com.blackjack.models.ScoreCard;

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
		int score = 22;
		
		assertTrue(ScoreCard.isBust(score));
	}
	
	@Test
	void testCardIsNotBust() {
		int score = 11;
		assertFalse(ScoreCard.isBust(score));
	}
	
	@Test
	void testProperlyScoringThreeAces() {
		Card ace1 = new Card(Rank.ACE, Suit.CLUB);
		Card ace2 = new Card(Rank.ACE, Suit.DIAMOND);
		Card ace3 = new Card(Rank.ACE, Suit.HEART);
		Card six = new Card(Rank.SIX, Suit.HEART);
		
		List<Card> hand = List.of(six);
		List<Card> aces = List.of(ace1, ace2, ace3);
		
		int expected = 19;
		int actual = 0;
		
		for(Card card: hand)
			actual += ScoreCard.score(card, actual);
		
		for(Card card: aces)
			actual += ScoreCard.score(card, actual);
		
		assertEquals(expected, actual);
		
	}

}
