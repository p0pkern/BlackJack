package com.blackjack.exceptions;

public class DeckFailedToSaveException extends Exception{
	/**
	 * If the deck fails to save, then this exception will let us know
	 * we can't get any cards.
	 */
	private static final long serialVersionUID = -3703023717203741816L;

	public DeckFailedToSaveException(String msg) {
		super(msg);
	}
}
