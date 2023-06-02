package com.blackjack.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.blackjack.models.Hand;
import com.blackjack.models.Player;

import java.util.List;
import java.util.ArrayList;

class PlayerTest {

	@Test
    public void testSettersAndGetters() {
        // Create a list of past hands
        List<Hand> pastHands = new ArrayList<>();
        Hand hand1 = new Hand();
        hand1.setId(1L);
        pastHands.add(hand1);

        // Create the current hand
        Hand currentHand = new Hand();
        currentHand.setId(2L);

        // Create a player
        Player player = new Player();
        player.setId(1L);
        player.setPastHands(pastHands);
        player.setCurrentHand(currentHand);

        assertEquals(1L, player.getId(), "ID should be set correctly");
        assertEquals(pastHands, player.getPastHands(), "Past hands should be set correctly");
        assertEquals(currentHand, player.getCurrentHand(), "Current hand should be set correctly");
    }
}
