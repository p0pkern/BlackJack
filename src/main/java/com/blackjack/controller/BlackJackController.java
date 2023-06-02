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
	private final CardService cardService;
	private Hand dealer;
	private Hand player;
	private List<Card> deck;
	private boolean stand = false;
	private static Long drawTurn;
	private int numberOfPlayerWins;
	private int numberOfDealerWins;
	private final Logger logger = LoggerFactory.getLogger(BlackJackController.class);

	static {
		drawTurn = 0L;
	}

	@Autowired
	public BlackJackController(HandService handService,
							   DeckService deckService,
							   CardService cardService) {
		this.handService = handService;
		this.deckService = deckService;
		this.cardService = cardService;
		this.numberOfDealerWins = 0;
		this.numberOfPlayerWins = 0;
	}

//	private Hand getHand(int player) {
//		return handService.getHand(player);
//	}
//	
//	private void drawACard(Hand currPlayer) {
//		Card card = cardService.getCard(drawTurn++);
//		currPlayer.drawCard(card);
//		handService.saveHand(currPlayer);
//	}
	
	private void startGame() {
		logger.info("Starting game.");

		deck = deckService.getDeck();
		
		dealer = handService.getHand(1L);
		player = handService.getHand(2L);
//		
//		drawACard(dealer);
//
//		drawACard(player);
//
//		handService.saveHand(player);
//		handService.saveHand(dealer);
	}
	
//	private void scoreHand(List<Card> currHand, Hand currPlayer) {
//		List<Card> aces = new ArrayList<>();
//		for(Card card: currHand) {
//			if(card.getRank() == Rank.ACE)
//				aces.add(card);
//		}
//		
//		int score = 0;
//		
//		for (Card card : currHand) {
//			if(card.getRank() != Rank.ACE)
//				score += ScoreCard.score(card, score);
//		}
//		
//		for(Card card: aces) {
//			score += ScoreCard.score(card, score);
//		}
//		
//		currPlayer.setBust(ScoreCard.isBust(score));
//		currPlayer.setScore(score);
//	}
//	
//	private void checkForWinner() {
//		// Check for bust
//		if (dealer.isBust()) {
//			player.setHandWins(true);
//		} else if (!dealer.isBust() && player.isBust()) {
//			dealer.setHandWins(true);
//		} else if(stand && dealer.getScore() > player.getScore()) {
//			dealer.setHandWins(true);
//		} else if(stand && dealer.getScore() <= player.getScore()) {
//			player.setHandWins(true);
//		}
//		
//		if(ScoreCard.isBlackJack(player.getScore())) {
//			player.setHandWins(true);
//			dealer.setHandWins(false);
//		} else if(ScoreCard.isBlackJack(dealer.getScore())) {
//			dealer.setHandWins(true);
//			player.setHandWins(false);
//		}
//		
//		if(player.isHandWins()) {
//			numberOfPlayerWins += 1;
//		}
//			
//		else if(dealer.isHandWins()) {
//			numberOfDealerWins += 1;
//		}
//	}

	@GetMapping("/")
	public String start(Model model) {

		if (drawTurn == 0)
			startGame();

//		scoreHand(dealer.getHand(), dealer);
//		scoreHand(player.getHand(), player);
//
//		boolean playerWins = player.isHandWins();
//		boolean dealerWins = dealer.isHandWins();
//		
//		logger.info("Dealer Wins {}", dealerWins );
//		
//		model.addAttribute("currentCard", drawTurn);
//		model.addAttribute("deck", deck);
//
//		model.addAttribute("dealerHand", dealer.getHand());
//		model.addAttribute("dealerScore", dealer.getScore());
//
//		model.addAttribute("playerHand", player.getHand());
//		model.addAttribute("playerScore", player.getScore());
//
//		// Win conditions
//		model.addAttribute("dealerWins", dealerWins);
//		model.addAttribute("playerWins", playerWins);
//		model.addAttribute("dealerBust", dealer.isBust());
//		model.addAttribute("playerBust", player.isBust());
//		
//		// Score tracker
//		model.addAttribute("numberOfPlayerWins", numberOfPlayerWins);
//		model.addAttribute("numberOfDealerWins", numberOfDealerWins);

		return "index";
	}
	
//	private void refreshDeck() {
//		deck = deckService.refreshDeck();
//		drawTurn = 0L;
//	}
//
//	@GetMapping("/hit")
//	public RedirectView hit(Model model) {
//		logger.info("Player chooses hit");
//		if(drawTurn > deck.size() - 1) {
//			refreshDeck();
//		}
//		drawACard(player);
//		
//		if(dealer.getScore() < 17 && dealer.getScore() < player.getScore()) {
//			drawACard(dealer);
//		}
//		
//		scoreHand(dealer.getHand(), dealer);
//		scoreHand(dealer.getHand(), player);
//		
//		checkForWinner();
//		
//		return new RedirectView("/");
//	}
//
//	@GetMapping("/newHand")
//	public RedirectView newHand(Model model) {
//		logger.info("Player requests new hand");
//		dealer.getHand().clear();
//		player.getHand().clear();
//		dealer.setBust(false);
//		player.setBust(false);
//		dealer.setScore(0);
//		player.setScore(0);
//		dealer.getHand().clear();
//		player.getHand().clear();
//		player.setHandWins(false);
//		dealer.setHandWins(false);
//		this.stand = false;
//		
//		if(drawTurn > deck.size() - 1) {
//			refreshDeck();
//		}
//		
//		drawACard(player);
//		drawACard(dealer);
//		
//		return new RedirectView("/");
//	}
//	
//	@GetMapping("/stand")
//	public RedirectView stand(Model model) {
//		logger.info("Player elects to stand");
//		this.stand = true;
//		
//		while(dealer.getScore() <= player.getScore() && !ScoreCard.isBlackJack(dealer.getScore())) {
//			drawACard(dealer);
//			scoreHand(dealer.getHand(), dealer);
//		}
//		
//		scoreHand(dealer.getHand(), dealer);
//		scoreHand(player.getHand(), player);
//		
//		checkForWinner();
//		return new RedirectView("/");
//	}
}
