package com.blackjack.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.blackjack.entity.Demo;

@SpringBootTest
class BlackJackApplicationTests {
	private Demo d;
	
	@BeforeEach
	void setup() {
		d = new Demo();
	}
	
	@AfterEach
	void tearDown() {
		d = null;
	}
	
	@Test
	void contextLoads() {
		assertEquals(d.getNum(), 4);
	}

}
