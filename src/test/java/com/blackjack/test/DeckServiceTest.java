package com.blackjack.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.blackjack.exceptions.DeckFailedToSaveException;
import com.blackjack.repository.CardRepository;
import com.blackjack.service.DeckService;

@ExtendWith(MockitoExtension.class)
class DeckServiceTest {

	@Mock
	private CardRepository cardRepository;
	
	@InjectMocks
	private DeckService deckService;
	
	@Test
	public void testGenerateDeck() throws DeckFailedToSaveException {
		Mockito.when(cardRepository.saveAll(Mockito.anyList())).thenReturn(null);
		
		boolean result = deckService.generateDeck();
		
		assertTrue(result);
		
		Mockito.verify(cardRepository).saveAll(Mockito.anyList());
	}

}
