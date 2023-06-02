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

import com.blackjack.service.DeckService;
import com.blackjack.service.HandService;
import com.blackjack.enums.Rank;
import com.blackjack.models.Card;
import com.blackjack.models.Hand;
import com.blackjack.models.ScoreCard;

@Controller
public class BlackJackController {
	private final HandService handService;
	private final DeckService deckService;
	private Hand dealer;
	private Hand player;
	private List<Card> dealerHand = new ArrayList<>();
	private List<Card> playerHand = new ArrayList<>();
	private List<Card> deck;
	private boolean stand = false;
	private static int drawTurn;
	private int numberOfPlayerWins;
	private int numberOfDealerWins;
	private final Logger logger = LoggerFactory.getLogger(BlackJackController.class);

	static {
		drawTurn = 0;
	}

	@Autowired
	public BlackJackController(HandService handService,
							   DeckService deckService) {
		this.handService = handService;
		this.deckService = deckService;
		this.numberOfDealerWins = 0;
		this.numberOfPlayerWins = 0;
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
		handService.saveHand(currPlayer);
	}
	
	private void startGame() {
		logger.info("Starting game.");
		handService.deleteAll();
		
		deck = deckService.getDeck();
		
		dealer = this.getHand(1);
		player = this.getHand(2);
		
		drawACard(dealer);

		drawACard(player);

		this.dealerHand = convertHandToCardHand(dealer.getHand());
		this.playerHand = convertHandToCardHand(player.getHand());

		handService.saveHand(player);
		handService.saveHand(dealer);
	}
	
	private void scoreHand(List<Card> currHand, Hand currPlayer) {
		List<Card> aces = new ArrayList<>();
		for(Card card: currHand) {
			if(card.getRank() == Rank.ACE)
				aces.add(card);
		}
		
		int score = 0;
		
		for (Card card : currHand) {
			if(card.getRank() != Rank.ACE)
				score += ScoreCard.score(card, score);
		}
		
		for(Card card: aces) {
			score += ScoreCard.score(card, score);
		}
		
		currPlayer.setBust(ScoreCard.isBust(score));
		currPlayer.setScore(score);
	}
	
	private void checkForWinner() {
		// Check for bust
		if (dealer.isBust()) {
			player.setHandWins(true);
		} else if (!dealer.isBust() && player.isBust()) {
			dealer.setHandWins(true);
		} else if(stand && dealer.getScore() > player.getScore()) {
			dealer.setHandWins(true);
		} else if(stand && dealer.getScore() <= player.getScore()) {
			player.setHandWins(true);
		}
		
		if(ScoreCard.isBlackJack(player.getScore())) {
			player.setHandWins(true);
			dealer.setHandWins(false);
		} else if(ScoreCard.isBlackJack(dealer.getScore())) {
			dealer.setHandWins(true);
			player.setHandWins(false);
		}
		
		if(player.isHandWins()) {
			numberOfPlayerWins += 1;
		}
			
		else if(dealer.isHandWins()) {
			numberOfDealerWins += 1;
		}
	}

	@GetMapping("/")
	public String start(Model model) {

		if (drawTurn == 0)
			startGame();

		scoreHand(dealerHand, dealer);
		scoreHand(playerHand, player);

		boolean playerWins = player.isHandWins();
		boolean dealerWins = dealer.isHandWins();
		
		logger.info("Dealer Wins {}", dealerWins );
		
		model.addAttribute("currentCard", drawTurn);
		model.addAttribute("deck", deck);

		model.addAttribute("dealerHand", dealerHand);
		model.addAttribute("dealerScore", dealer.getScore());

		model.addAttribute("playerHand", playerHand);
		model.addAttribute("playerScore", player.getScore());

		// Win conditions
		model.addAttribute("dealerWins", dealerWins);
		model.addAttribute("playerWins", playerWins);
		model.addAttribute("dealerBust", dealer.isBust());
		model.addAttribute("playerBust", player.isBust());
		
		// Score tracker
		model.addAttribute("numberOfPlayerWins", numberOfPlayerWins);
		model.addAttribute("numberOfDealerWins", numberOfDealerWins);

		return "index";
	}
	
	private void refreshDeck() {
		deckService.deleteAll();
		deck = deckService.getDeck();
		drawTurn = 0;
	}

	@GetMapping("/hit")
	public RedirectView hit(Model model) {
		logger.info("Player chooses hit");
		if(drawTurn > deck.size() - 1) {
			refreshDeck();
		}
		drawACard(player);
		playerHand = convertHandToCardHand(player.getHand());
		
		if(dealer.getScore() < 17 && dealer.getScore() < player.getScore()) {
			drawACard(dealer);
			dealerHand = convertHandToCardHand(dealer.getHand());
		}
		
		scoreHand(dealerHand, dealer);
		scoreHand(playerHand, player);
		
		checkForWinner();
		
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
		player.setHandWins(false);
		dealer.setHandWins(false);
		this.stand = false;
		
		if(drawTurn > deck.size() - 1) {
			refreshDeck();
		}
		
		drawACard(player);
		drawACard(dealer);
		
		this.dealerHand = convertHandToCardHand(dealer.getHand());
		this.playerHand = convertHandToCardHand(player.getHand());
		
		return new RedirectView("/");
	}
	
	@GetMapping("/stand")
	public RedirectView stand(Model model) {
		logger.info("Player elects to stand");
		this.stand = true;
		
		while(dealer.getScore() <= player.getScore() && !ScoreCard.isBlackJack(dealer.getScore())) {
			drawACard(dealer);
			dealerHand = convertHandToCardHand(dealer.getHand());
			scoreHand(dealerHand, dealer);
		}
		
		scoreHand(dealerHand, dealer);
		scoreHand(playerHand, player);
		
		checkForWinner();
		return new RedirectView("/");
	}
}
