package com.blackjack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blackjack.models.Card;

/**
 * The CardRepository interface provides CRUD operations for managing Card entities.
 * It extends the JpaRepository interface, providing additional methods for working with cards.
 */
public interface CardRepository extends JpaRepository<Card, Long>{
	boolean existsAny();
}
