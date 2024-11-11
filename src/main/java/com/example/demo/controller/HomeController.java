package com.example.demo.controller;

import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Greeting;
import com.example.demo.model.Count;

@Controller 
public class HomeController { 

	@GetMapping("/") 
	public String home() { 
		return "home";  // for home.html 
	} 

	@GetMapping("/greeting") // THIS IS THE ORIGINAL
	public String greetingForm(Model model) {
		Count count = new Count();
		Count.count = Count.count + 1;
		model.addAttribute("greeting", new Greeting());
		model.addAttribute("count", count);
		
		return "greeting";
	}

	@PostMapping("/submit")
	public String greetingSubmit(@RequestParam("question1") String q1, @RequestParam("question2") String q2, @RequestParam("question3") String q3, @RequestParam("question4") String q4, @RequestParam("question5") String q5, @ModelAttribute Greeting greeting, @ModelAttribute Count count, Model model) {
		model.addAttribute("greeting", greeting);
		model.addAttribute("count", count);

		int grade = 0;
		if ("true".equals(q1)) grade++;
		if ("false".equals(q2)) grade++;
		if ("true".equals(q3)) grade++;
		if ("false".equals(q4)) grade++;
		if ("false".equals(q5)) grade++;

		model.addAttribute("grade", grade);

		if (grade >= 3) {
			return "goodgrade";
		}
		else {
			return "badgrade";
		}
		// return "result";
	}

	@GetMapping("/goodgrade")
	public String goodResult(@RequestParam int grade, Model model) {
    	model.addAttribute("grade", grade);
    	return "redirect:/goodgrade";
	}

	@GetMapping("/badgrade")
	public String badResult(@RequestParam int grade, Model model) {
    	model.addAttribute("grade", grade);
    	return "redirect:/badgrade";
	}

	@GetMapping("/login")
	public String showLogin() {
		return "/greeting";
	}

	@PostMapping("/login")
	public String loginLogic(@RequestParam String user, @RequestParam String password, Model model) {
		if (password.length() < 10) {
            return "/badpassword";
        }
		else {
			return "/greeting";
		}
	}

	@GetMapping("/badpassword")
	public String userBadPass(Model model) {
		return "badpassword";
	}

  
}
