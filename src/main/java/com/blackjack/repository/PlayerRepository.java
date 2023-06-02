package com.blackjack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blackjack.models.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
