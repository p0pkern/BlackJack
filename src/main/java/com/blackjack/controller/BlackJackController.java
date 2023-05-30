package com.blackjack.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	private final Hand dealer;
	private final Hand player;
	private List<Card> dealerHand = new ArrayList<>();
	private List<Card> playerHand = new ArrayList<>();
	private static int drawTurn;

	static {
		drawTurn = 0;
	}

	@Autowired
	public BlackJackController(CardService cardService, HandService handService) {
		this.cardService = cardService;
		this.handService = handService;
		this.dealer = getHand(1);
		this.player = getHand(2);
	}

	private List<Card> getCurrentDeck() {
		List<Card> deck = cardService.getAllCards();

		if (deck.isEmpty()) {
			deck = cardService.generateDeck();
			cardService.saveDeck(deck);
		}

		return deck;
	}

	private List<Card> getCurrentHand(List<Integer> hand, List<Card> deck) {
		List<Card> convertedHand = new ArrayList<>();

		for (int c : hand) {
			convertedHand.add(deck.get(c));
		}

		return convertedHand;
	}

	private Hand getHand(int player) {
		return handService.getHand(player);
	}

	@GetMapping("/")
	public String start(Model model) {
		List<Card> deck = getCurrentDeck();

		if (drawTurn == 0) {

			dealer.drawCard(drawTurn++);
			dealer.drawCard(drawTurn++);

			player.drawCard(drawTurn++);
			player.drawCard(drawTurn++);

			this.dealerHand = getCurrentHand(dealer.getHand(), deck);
			this.playerHand = getCurrentHand(player.getHand(), deck);

			handService.saveHand(player);
			handService.saveHand(dealer);
		}

		int dealerScore = 0;
		int playerScore = 0;

		for (Card card : dealerHand) {
			dealerScore += ScoreCard.score(card, dealerScore);
		}

		for (Card card : playerHand) {
			if(ScoreCard.isBust(card, playerScore))
				player.setBust(true);
			playerScore += ScoreCard.score(card, playerScore);
		}

		model.addAttribute("currentCard", drawTurn);
		model.addAttribute("deck", deck);
		
		model.addAttribute("dealerBust", dealer.isBust());
		model.addAttribute("dealerHand", dealerHand);
		model.addAttribute("dealerScore", dealerScore);
		
		model.addAttribute("playerBust", player.isBust());
		model.addAttribute("playerHand", playerHand);
		model.addAttribute("playerScore", playerScore);

		return "index";
	}

	@PostMapping("/hit")
	public RedirectView hit(Model model) {
		player.drawCard(drawTurn++);
		handService.saveHand(player);
		List<Card> deck = getCurrentDeck();
		playerHand = getCurrentHand(player.getHand(), deck);
		return new RedirectView("/");
	}
}
