package com.example.loginwithspring.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ch.qos.logback.core.model.Model;

@Controller
public class UserController {
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
//	custom logouts made to get out of session instead default logout
	
//	@GetMapping("/logouts")
//	public String handleLogoutRequest(HttpServletRequest requests) {
//		
//		requests.getSession().invalidate();
//		return "redirect:/login";
//	}
	
	@GetMapping("/login")
	public String showLoginForm(Model model) {
		
		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		if(authentication==null || authentication instanceof AnonymousAuthenticationToken) {
			return "login";
		}
		return "redirect:/";
		
	}
	
}
