package com.blackjack.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "player_id")
	private List<Hand> pastHands;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "current_hand_id")
	private Hand currentHand;
	
	public Player() {}

	public Player(Long id, List<Hand> pastHands, Hand currentHand) {
		this.id = id;
		this.pastHands = pastHands;
		this.currentHand = currentHand;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Hand> getPastHands() {
		return pastHands;
	}

	public void setPastHands(List<Hand> pastHands) {
		this.pastHands = pastHands;
	}

	public Hand getCurrentHand() {
		return currentHand;
	}

	public void setCurrentHand(Hand currentHand) {
		this.currentHand = currentHand;
	}

}
