package com.blackjack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blackjack.entity.Hand;

public interface HandRepository extends JpaRepository<Hand, Integer>{

}
