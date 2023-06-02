package com.blackjack.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.blackjack.models.Card;
import com.blackjack.models.Hand;
import com.blackjack.models.Player;
import com.blackjack.enums.Rank;
import com.blackjack.enums.Suit;

import java.util.List;
import java.util.ArrayList;

class HandTest {
	
	@Test
	public void testDrawCard() {
		// Create a hand
		Hand hand = new Hand();
		assertEquals(0, hand.getHand().size(), "Hand should be empty initially");

		// Create a card
		Card card = new Card();
		card.setRank(Rank.ACE);
		card.setSuit(Suit.HEART);

		// Draw a card
		hand.drawCard(card);
		assertEquals(1, hand.getHand().size(), "Hand should have one card after drawing");
		assertEquals(card, hand.getHand().get(0), "Drawn card should be added to the hand");
	}

	@Test
	public void testSettersAndGetters() {
		// Create a list of cards
		List<Card> cards = new ArrayList<>();
		Card card1 = new Card();
		card1.setRank(Rank.ACE);
		card1.setSuit(Suit.HEART);
		cards.add(card1);

		// Create a player
		Player player = new Player();

		// Create a hand
		Hand hand = new Hand();
		hand.setId(1);
		hand.setHand(cards);
		hand.setScore(21);
		hand.setBust(false);
		hand.setHandWins(true);
		hand.setPlayer(player);

		assertEquals(1, hand.getId(), "ID should be set correctly");
		assertEquals(cards, hand.getHand(), "Hand should be set correctly");
		assertEquals(21, hand.getScore(), "Score should be set correctly");
		assertFalse(hand.isBust(), "Bust flag should be set correctly");
		assertTrue(hand.isHandWins(), "Hand wins flag should be set correctly");
		assertEquals(player, hand.getPlayer(), "Player should be set correctly");
	}
}
