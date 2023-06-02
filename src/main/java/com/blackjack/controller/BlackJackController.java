package com.blackjack.controller;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.blackjack.service.DeckService;
import com.blackjack.service.HandService;
import com.blackjack.exceptions.DeckFailedToSaveException;
import com.blackjack.exceptions.NoCardExistsException;
import com.blackjack.models.Hand;
import com.blackjack.models.ScoreCard;

@Controller
public class BlackJackController {
	private final HandService handService;
	private final DeckService deckService;
	private Hand dealer;
	private Hand player;
	private boolean stand = false;
	private static Long drawTurn;
	private int numberOfPlayerWins;
	private int numberOfDealerWins;
	private final Logger logger = LoggerFactory.getLogger(BlackJackController.class);

	static {
		drawTurn = 1L;
	}

	@Autowired
	public BlackJackController(HandService handService,
							   DeckService deckService) {
		this.handService = handService;
		this.deckService = deckService;
		this.numberOfDealerWins = 0;
		this.numberOfPlayerWins = 0;
	}

	
	
	private void startGame() throws NoCardExistsException, DeckFailedToSaveException, InterruptedException {
		logger.info("Starting game.");

		deckService.generateDeck();
		
		dealer = handService.getHand(1L);
		player = handService.getHand(2L);
		
		handService.drawACard(dealer, drawTurn++);
		handService.drawACard(player, drawTurn++);

		handService.saveHand(player);
		handService.saveHand(dealer);
	}
	
	
	private void updatePlayerWins() {
		if (player.isHandWins()) {
			numberOfPlayerWins += 1;
		}

		else if (dealer.isHandWins()) {
			numberOfDealerWins += 1;
		}
	}

	@GetMapping("/")
	public String start(Model model) throws NoCardExistsException, DeckFailedToSaveException, InterruptedException {

		if (drawTurn == 1)
			startGame();

		handService.scoreHand(player);
		handService.scoreHand(dealer);
		
		model.addAttribute("currentCard", drawTurn);

		model.addAttribute("dealerHand", dealer.getHand());
		model.addAttribute("dealerScore", dealer.getScore());

		model.addAttribute("playerHand", player.getHand());
		model.addAttribute("playerScore", player.getScore());

		// Win conditions
		model.addAttribute("dealerWins", dealer.isHandWins());
		model.addAttribute("playerWins", player.isHandWins());
		model.addAttribute("dealerBust", dealer.isBust());
		model.addAttribute("playerBust", player.isBust());
		
		// Score tracker
		model.addAttribute("numberOfPlayerWins", numberOfPlayerWins);
		model.addAttribute("numberOfDealerWins", numberOfDealerWins);

		return "index";
	}
	
	@GetMapping("/hit")
	public RedirectView hit(Model model) throws NoCardExistsException, DeckFailedToSaveException {
		logger.info("Player chooses hit");
		if(drawTurn >  deckService.countCards()) {
			deckService.generateDeck();
		}
		handService.drawACard(player, drawTurn++);
		
		if(dealer.getScore() < 17 && dealer.getScore() < player.getScore()) {
			handService.drawACard(dealer, drawTurn++);
		}
		
		handService.scoreHand(dealer);
		handService.scoreHand(player);
		
		ScoreCard.checkForWinner(dealer, player, stand);
		updatePlayerWins();
		
		return new RedirectView("/");
	}

	@GetMapping("/newHand")
	public RedirectView newHand(Model model) throws DeckFailedToSaveException, NoCardExistsException {
		logger.info("Player requests new hand");
		dealer.getHand().clear();
		player.getHand().clear();
		dealer.setBust(false);
		player.setBust(false);
		dealer.setScore(0);
		player.setScore(0);
		dealer.getHand().clear();
		player.getHand().clear();
		player.setHandWins(false);
		dealer.setHandWins(false);
		this.stand = false;
		
		if(drawTurn >  deckService.countCards()) {
			deckService.generateDeck();
		}
		
		handService.drawACard(player, drawTurn++);
		handService.drawACard(dealer, drawTurn++);
		
		ScoreCard.checkForWinner(dealer, player, stand);
		
		return new RedirectView("/");
	}
	
	@GetMapping("/stand")
	public RedirectView stand(Model model) throws NoCardExistsException {
		logger.info("Player elects to stand");
		this.stand = true;
		
		while(dealer.getScore() <= player.getScore() && !ScoreCard.isBlackJack(dealer.getScore())) {
			handService.drawACard(dealer, drawTurn++);
			handService.scoreHand(dealer);
		}
		
		handService.scoreHand(dealer);
		handService.scoreHand(player);
		
		ScoreCard.checkForWinner(dealer, player, stand);
		updatePlayerWins();
		
		return new RedirectView("/");
	}
}
