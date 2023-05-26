package com.blackjack.entity;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Player {
	@Id
	private int playerId;
	
	private int currentScore;
	private int currentTurn;
	private Hand currentHand;
	private List<Hand> previousHands;
	private List<Integer> previousScores;
	
	public Player() {
		this.previousHands = new ArrayList<>();
		this.previousScores = new ArrayList<>();
	}
	
	public Player(int playerId, int currentScore, Hand currentHand, List<Hand> previousHands, List<Integer> previousScores) {
		this.playerId = playerId;
		this.currentScore = currentScore;
		this.currentHand = currentHand;
		this.previousHands = previousHands;
		this.previousScores = previousScores;
	}

	public int getCurrentScore() {
		return currentScore;
	}

	public void setCurrentScore(int currentScore) {
		this.currentScore = currentScore;
	}

	public Hand getCurrentHand() {
		return currentHand;
	}

	public void setCurrentHand(Hand currentHand) {
		this.currentHand = currentHand;
	}

	public List<Hand> getPreviousHands() {
		return previousHands;
	}

	public void setPreviousHands(List<Hand> previousHands) {
		this.previousHands = previousHands;
	}

	public List<Integer> getPreviousScores() {
		return previousScores;
	}

	public void setPreviousScores(List<Integer> previousScores) {
		this.previousScores = previousScores;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public int getCurrentTurn() {
		return currentTurn;
	}

	public void setCurrentTurn(int currentTurn) {
		this.currentTurn = currentTurn;
	}
	
}
