package com.blackjack.exceptions;

public class NoCardExistsException extends Exception {
	/**
	 * Triggers when trying to pull a card by a specified Long id num
	 * does not find a card.
	 */
	private static final long serialVersionUID = -3733761224956799325L;

	public NoCardExistsException(String msg) {
		super(msg);
	}
}
