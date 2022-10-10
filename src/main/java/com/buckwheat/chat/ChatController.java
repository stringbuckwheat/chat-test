package com.buckwheat.chat;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ChatController {
	@GetMapping("/login")
	public String login() {
		log.debug("로그인");
		
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam String name, HttpSession session) {
		log.debug("로그인");
		session.setAttribute("login", name);
		
		return "redirect:/chat";
	}
	
	@GetMapping("/chat")
	public String chat(HttpSession session) {
		log.debug("채팅 시작");
		return "chat";
	}
}
