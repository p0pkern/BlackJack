package com.blackjack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blackjack.models.Hand;

/**
 * The HandRepository interface provides CRUD operations for managing Hand entities.
 * It extends the JpaRepository interface, providing additional methods for working with hands.
 * A Hand represents the collection of cards held by a player.
 */
public interface HandRepository extends JpaRepository<Hand, Long>{

}
