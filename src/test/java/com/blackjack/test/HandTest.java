package com.blackjack.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.blackjack.models.Hand;

import java.util.List;
import java.util.ArrayList;

class HandTest {
	private Hand hand;

	 @BeforeEach
	    public void setup() {
	        hand = new Hand(1, new ArrayList<>(), 0, false);
	    }
	    
	    @Test
	    public void testGetId() {
	        assertEquals(1, hand.getId());
	    }
	    
	    @Test
	    public void testSetId() {
	        hand.setId(2);
	        assertEquals(2, hand.getId());
	    }
	    
	    @Test
	    public void testGetHand() {
	        List<Integer> expected = new ArrayList<>();
	        assertEquals(expected, hand.getHand());
	    }
	    
	    @Test
	    public void testSetHand() {
	        List<Integer> expected = new ArrayList<>();
	        expected.add(1);
	        hand.setHand(expected);
	        assertEquals(expected, hand.getHand());
	    }
	    
	    @Test
	    public void testGetScore() {
	        assertEquals(0, hand.getScore());
	    }
	    
	    @Test
	    public void testSetScore() {
	        hand.setScore(10);
	        assertEquals(10, hand.getScore());
	    }
	    
	    @Test
	    public void testIsBust() {
	        assertFalse(hand.isBust());
	    }
	    
	    @Test
	    public void testSetBust() {
	        hand.setBust(true);
	        assertTrue(hand.isBust());
	    }
	    
	    @Test
	    public void testDrawCard() {
	        hand.drawCard(5);
	        List<Integer> expected = new ArrayList<>();
	        expected.add(5);
	        assertEquals(expected, hand.getHand());
	    }

}
