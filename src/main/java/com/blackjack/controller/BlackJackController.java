package com.blackjack.controller;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.blackjack.service.CardService;
import com.blackjack.service.HandService;

import com.blackjack.entity.Card;
import com.blackjack.entity.Hand;
import com.blackjack.entity.ScoreCard;

@Controller
public class BlackJackController {
	private final CardService cardService;
	private final HandService handService;
	private Hand dealer;
	private Hand player;
	private List<Card> dealerHand = new ArrayList<>();
	private List<Card> playerHand = new ArrayList<>();
	private List<Card> deck;
	private static int drawTurn;
	private final Logger logger = LoggerFactory.getLogger(BlackJackController.class);

	static {
		drawTurn = 0;
	}

	@Autowired
	public BlackJackController(CardService cardService, HandService handService) {
		this.cardService = cardService;
		this.handService = handService;
	}

	private List<Card> getCurrentDeck() {
		deck = cardService.getAllCards();

		if (deck.isEmpty()) {
			deck = cardService.generateDeck();
			cardService.saveDeck(deck);
		}

		return deck;
	}

	private List<Card> getCurrentHand(List<Integer> hand) {
		logger.info("Converting hand into cards.");
		List<Card> convertedHand = new ArrayList<>();

		for (int c : hand) {
			convertedHand.add(deck.get(c));
		}

		return convertedHand;
	}

	private Hand getHand(int player) {
		return handService.getHand(player);
	}
	
	private void startGame() {
		logger.info("Starting game.");
		handService.deleteAll();
		
		deck = this.getCurrentDeck();
		
		dealer = this.getHand(1);
		player = this.getHand(2);
		
		dealer.drawCard(drawTurn++);

		player.drawCard(drawTurn++);

		this.dealerHand = getCurrentHand(dealer.getHand());
		this.playerHand = getCurrentHand(player.getHand());

		handService.saveHand(player);
		handService.saveHand(dealer);
	}

	@GetMapping("/")
	public String start(Model model) {

		if (drawTurn == 0) {
			startGame();
		}

		int dealerScore = 0;
		int playerScore = 0;

		for (Card card : dealerHand) {
			if (ScoreCard.isBust(card, dealerScore))
				dealer.setBust(true);
			dealerScore += ScoreCard.score(card, dealerScore);
		}

		for (Card card : playerHand) {
			if (ScoreCard.isBust(card, playerScore))
				player.setBust(true);
			playerScore += ScoreCard.score(card, playerScore);
		}

		boolean playerWins = false;
		boolean dealerWins = false;

		if (dealer.isBust()) {
			playerWins = true;
		} else if (!dealer.isBust() && player.isBust()) {
			dealerWins = true;
		} else {
			playerWins = ScoreCard.isBlackJack(playerScore);
			if (!playerWins)
				dealerWins = ScoreCard.isBlackJack(dealerScore);
		}

		model.addAttribute("currentCard", drawTurn);
		model.addAttribute("deck", deck);

		model.addAttribute("dealerHand", dealerHand);
		model.addAttribute("dealerScore", dealerScore);

		model.addAttribute("playerHand", playerHand);
		model.addAttribute("playerScore", playerScore);

		// Win conditions
		model.addAttribute("dealerWins", dealerWins);
		model.addAttribute("playerWins", playerWins);

		return "index";
	}

	@GetMapping("/hit")
	public RedirectView hit(Model model) {
		logger.info("Player chooses hit");
		player.drawCard(drawTurn++);
		handService.saveHand(player);
		playerHand = getCurrentHand(player.getHand());
		return new RedirectView("/");
	}

	@GetMapping("/newHand")
	public RedirectView newHand(Model model) {
		logger.info("Player requests new hand");
		dealerHand.clear();
		playerHand.clear();
		dealer.setBust(false);
		player.setBust(false);
		dealer.getHand().clear();
		player.getHand().clear();
		
		if(drawTurn >= deck.size()) {
			drawTurn = 0;
			
			deck = cardService.generateDeck();
			cardService.saveDeck(deck);
		}
		
		dealer.drawCard(drawTurn++);
		player.drawCard(drawTurn++);
		
		this.dealerHand = getCurrentHand(dealer.getHand());
		this.playerHand = getCurrentHand(player.getHand());
		
		System.out.println(dealerHand);
		
		handService.saveHand(player);
		handService.saveHand(dealer);
		
		return new RedirectView("/");
	}
}
