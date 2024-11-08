package com.example.demo.controller;

import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Greeting;
import com.example.demo.model.Count;

@Controller 
public class HomeController { 

	@GetMapping("/") 
	public String home() { 
		return "home";  // for home.html 
	} 

	@GetMapping("/greeting")
		public String greetingForm(Model model) {
			Count count = new Count();
			Count.count = Count.count + 1;
			model.addAttribute("greeting", new Greeting());
			model.addAttribute("count", count);
			
			return "greeting";
	}

	  @PostMapping("/greeting")
 		 public String greetingSubmit(@ModelAttribute Greeting greeting, @ModelAttribute Count count, Model model) {
    		model.addAttribute("greeting", greeting);
			model.addAttribute("count", count);
    	return "result";
  	}
  
}
