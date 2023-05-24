package com.blackjack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blackjack.service.BlackJackService;

@Controller
public class HomeController {
	@Autowired
	private BlackJackService blackJackService;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("home", blackJackService.getSingleDeck());
		return "home";
	}
}
