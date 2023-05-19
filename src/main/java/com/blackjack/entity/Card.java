package com.blackjack.entity;

public class Card {
	private Character rank;
	private Character suit;
	private int value;

	public Character getRank() {
		return rank;
	}

	public void setRank(Character rank) {
		this.rank = rank;
	}

	public Character getSuit() {
		return suit;
	}

	public void setSuit(Character suit) {
		this.suit = suit;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
