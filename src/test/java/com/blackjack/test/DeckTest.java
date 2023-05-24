package com.blackjack.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.blackjack.entity.Deck;

class DeckTest {
	private static final int cardCount = 52;
	private Deck deck;

	@BeforeEach
	void setup() {
		deck = new Deck();
	}

	@AfterEach
	void tearDown() {
		deck = null;
	}

	@Test
	void testStandardSizeDeck() {
		int deckSize = deck.getDeck(1).size();

		assertEquals(cardCount, deckSize);
	}

	@Test
	void testDeckSize0ShouldRevertToDeckSize1() {
		int deckSize = deck.getDeck(0).size();

		assertEquals(cardCount, deckSize);
	}

	@Test
	void testDeckSizeBelow0ShouldReverToDeckSize1() {
		int deckSize = deck.getDeck(-1).size();

		assertEquals(cardCount, deckSize);
	}

	@Test
	void testDeckSizeOfSizeFiveShouldReturnDeckSizeOfFiveDecks() {

		int deckSize = deck.getDeck(5).size();
		int expected = cardCount * 5;

		assertEquals(expected, deckSize);
	}

}
