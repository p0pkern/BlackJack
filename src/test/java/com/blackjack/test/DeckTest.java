package com.blackjack.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.blackjack.entity.Deck;

class DeckTest {
	private static final int cardCount = 52;
	private Deck deck;

	@AfterEach
	void tearDown() {
		deck = null;
	}

	@Test
	void testStandardSizeDeck() {
		deck = new Deck();
		int deckSize = deck.getDeck().size();

		assertEquals(cardCount, deckSize);
	}

	@Test
	void testDeckSize0ShouldRevertToDeckSize1() {
		deck = new Deck(0);
		int deckSize = deck.getDeck().size();

		assertEquals(cardCount, deckSize);
	}
	
	@Test
	void testDeckSizeBelow0ShouldReverToDeckSize1() {
		deck = new Deck(-1);
		int deckSize = deck.getDeck().size();

		assertEquals(cardCount, deckSize);
	}
	
	@Test
	void testDeckSizeOfSizeFiveShouldReturnDeckSizeOfFiveDecks() {
		deck = new Deck(5);
		int deckSize = deck.getDeck().size();
		int expected = cardCount * 5;
		
		assertEquals(expected, deckSize);
	}

}
