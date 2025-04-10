package com.ai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ai.service.ChatbotService;

import retrofit2.http.GET;
@Controller
public class ChatbotController {
      @Autowired
	  private ChatbotService chatService;

	    public ChatbotController(ChatbotService chatService) {
	        this.chatService = chatService;
	    }
        
	     @GetMapping("/")
	    public String home(Model model) {
	        model.addAttribute("message", "Ask me anything!");
	        return "chat";
	    }

	    @PostMapping("/ask")
	    public String askQuestion(@RequestParam String userInput, Model model) {
	        String response = chatService.chat(userInput);
	        model.addAttribute("userInput", userInput);
	        model.addAttribute("response", response);
	        return "chat";
	    }

	    @PostMapping("/name")
	    public String setName(@RequestParam String name, Model model) {
	        chatService.rememberName(name);
	        model.addAttribute("message", "Got it, " + name + "!");
	        return "chat";
	    }
}
