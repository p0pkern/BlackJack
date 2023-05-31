package com.blackjack.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.blackjack.enums.Rank;
import com.blackjack.enums.Suit;
import com.blackjack.entity.Card;
class CardTest {

	@Test
	void testConvertRankAce() {
		Card card = new Card(Rank.ACE, Suit.CLUB);
		String convertedRank = card.convertRank();
		assertEquals("A", convertedRank);
	}
	
	@Test
	void testConvertRankTwo() {
		Card card = new Card(Rank.TWO, Suit.DIAMOND);
		String convertedRank = card.convertRank();
		assertEquals("2", convertedRank);
	}
	
	@Test
	void testConvertSuitHeart() {
		Card card = new Card(Rank.TWO, Suit.HEART);
		String convertedSuit = card.convertSuit();
		assertEquals("♥︎", convertedSuit);
	}
	
	@Test
	void testConvertSuitClub() {
		Card card = new Card(Rank.TWO, Suit.CLUB);
		String convertedSuit = card.convertSuit();
		assertEquals("♣", convertedSuit);
	}
	
	@Test
	void testConvertSuitSpade() {
		Card card = new Card(Rank.TWO, Suit.SPADE);
		String convertedSuit = card.convertSuit();
		assertEquals("♠︎", convertedSuit);
	}
	
	@Test
	void testConvertSuitDiamond() {
		Card card = new Card(Rank.TWO, Suit.DIAMOND);
		String convertedSuit = card.convertSuit();
		assertEquals("♦", convertedSuit);
	}	
	

}
