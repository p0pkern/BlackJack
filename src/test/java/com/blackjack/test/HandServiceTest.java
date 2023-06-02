package com.blackjack.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.blackjack.repository.HandRepository;
import com.blackjack.service.CardService;
import com.blackjack.service.HandService;

import com.blackjack.models.Hand;
import com.blackjack.enums.Rank;
import com.blackjack.exceptions.NoCardExistsException;
import com.blackjack.models.Card;

import java.util.List;
import java.util.ArrayList;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class HandServiceTest {
	
	@Mock
	private HandRepository handRepository;
	
	@Mock
	private CardService cardService;
	
	@InjectMocks
	private HandService handService;
	
	@Test
    public void testGetHand_ExistingHand() {
        Long handId = 1L;
        Hand existingHand = new Hand();
        existingHand.setId(handId);
        Mockito.when(handRepository.existsById(handId)).thenReturn(true);
        Mockito.when(handRepository.getReferenceById(handId)).thenReturn(existingHand);

        Hand hand = handService.getHand(handId);

        assertEquals(existingHand, hand, "Should return existing hand");
    }

    @Test
    public void testGetHand_NonExistingHand() {
        Long handId = 1L;
        Mockito.when(handRepository.existsById(handId)).thenReturn(false);

        Hand hand = handService.getHand(handId);

        assertNotNull(hand, "Should return a new hand");
        assertEquals(handId, hand.getId(), "Hand ID should be set");
    }

    @Test
    public void testSaveHand() {
        Hand hand = new Hand();
        hand.setId(1L);

        handService.saveHand(hand);

        Mockito.verify(handRepository, Mockito.times(1)).save(hand);
    }

    @Test
    public void testScoreHand() {
        Hand hand = new Hand();
        List<Card> cards = new ArrayList<>();
        Card card1 = new Card();
        card1.setRank(Rank.ACE);
        cards.add(card1);
        Card card2 = new Card();
        card2.setRank(Rank.KING);
        cards.add(card2);
        hand.setHand(cards);

        handService.scoreHand(hand);

        assertEquals(21, hand.getScore(), "Score should be calculated correctly");
        assertFalse(hand.isBust(), "Should not be bust");
    }

    @Test
    public void testDrawACard() throws NoCardExistsException {
        Hand hand = new Hand();
        Long cardId = 1L;
        Card card = new Card();
        Mockito.when(cardService.getCard(cardId)).thenReturn(card);

        handService.drawACard(hand, cardId);

        Mockito.verify(cardService, Mockito.times(1)).getCard(cardId);
        Mockito.verify(handRepository, Mockito.times(1)).save(hand);
        assertEquals(1, hand.getHand().size(), "Hand should have one card");
        assertEquals(card, hand.getHand().get(0), "Hand should contain the drawn card");
    }
}
