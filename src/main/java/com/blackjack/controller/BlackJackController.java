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
	private boolean stand;
	private static int drawTurn;
	private static int currentTurn;
	private final Logger logger = LoggerFactory.getLogger(BlackJackController.class);

	static {
		drawTurn = 0;
	}

	@Autowired
	public BlackJackController(CardService cardService, HandService handService) {
		this.cardService = cardService;
		this.handService = handService;
	}
	
	private void deleteOldDeck() {
		drawTurn = 0;
		cardService.deleteAll();
	}

	private List<Card> getCurrentDeck() {
		deck = cardService.getAllCards();

		if (deck.isEmpty()) {
			deck = cardService.generateDeck();
			cardService.saveDeck(deck);
		}

		return deck;
	}

	private List<Card> convertHandToCardHand(List<Integer> hand) {
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
	
	private void drawACard(Hand currPlayer) {
		currPlayer.drawCard(drawTurn++);
		currentTurn++;
		handService.saveHand(currPlayer);
	}
	
	private void startGame() {
		logger.info("Starting game.");
		handService.deleteAll();
		
		deck = this.getCurrentDeck();
		
		dealer = this.getHand(1);
		player = this.getHand(2);
		
		drawACard(dealer);

		drawACard(player);

		this.dealerHand = convertHandToCardHand(dealer.getHand());
		this.playerHand = convertHandToCardHand(player.getHand());

		handService.saveHand(player);
		handService.saveHand(dealer);
	}
	
	private int scoreHand(List<Card> currHand, Hand currPlayer) {
		int score = 0;
		for (Card card : currHand) {
			currPlayer.setBust(ScoreCard.isBust(card, score));
			score += ScoreCard.score(card, score);
		}
		return score;
	}

	@GetMapping("/")
	public String start(Model model) {

		if (drawTurn == 0) {
			startGame();
		}

		int dealerScore = scoreHand(dealerHand, dealer);
		int playerScore = scoreHand(playerHand, player);

		boolean playerWins = false;
		boolean dealerWins = false;
		
		// Check for bust
		if (dealer.isBust())
			playerWins = true;
		else if (!dealer.isBust() && player.isBust())
			dealerWins = true;
		else if(ScoreCard.isBlackJack(playerScore))
			playerWins = true;
		else if(ScoreCard.isBlackJack(dealerScore))
			dealerWins = true;
		else if(stand && dealerScore > playerScore)
			dealerWins = true;
		else if(stand && dealerScore <= playerScore)
			playerWins = true;

		model.addAttribute("currentCard", drawTurn);
		model.addAttribute("currentTurn", currentTurn);
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
		if(drawTurn > deck.size() - 1) {
			deleteOldDeck();
			deck = getCurrentDeck();
		}
		drawACard(player);
		playerHand = convertHandToCardHand(player.getHand());
		return new RedirectView("/");
	}

	@GetMapping("/newHand")
	public RedirectView newHand(Model model) {
		logger.info("Player requests new hand");
		dealerHand.clear();
		playerHand.clear();
		dealer.setBust(false);
		player.setBust(false);
		dealer.setScore(0);
		player.setScore(0);
		dealer.getHand().clear();
		player.getHand().clear();
		
		if(drawTurn > deck.size() - 1) {
			deleteOldDeck();
			deck = getCurrentDeck();
		}
		
		drawACard(player);
		drawACard(dealer);
		
		this.dealerHand = convertHandToCardHand(dealer.getHand());
		this.playerHand = convertHandToCardHand(player.getHand());
		
		return new RedirectView("/");
	}
	
}
