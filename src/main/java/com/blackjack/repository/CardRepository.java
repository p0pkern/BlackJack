package com.blackjack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blackjack.models.Card;

/**
 * The CardRepository interface provides CRUD operations for managing Card entities.
 * It extends the JpaRepository interface, providing additional methods for working with cards.
 */
public interface CardRepository extends JpaRepository<Card, Long>{
	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Card c")
	boolean existsAny();
	
	@Query("SELECT COUNT(c) FROM Card c")
	long countCards();
}
