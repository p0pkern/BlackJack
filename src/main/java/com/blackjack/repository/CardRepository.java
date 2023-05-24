package com.blackjack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blackjack.entity.Card;

public interface CardRepository extends JpaRepository<Card, Long>{
	
}
