package com.example.board.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.board.entity.User;
import com.example.board.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired UserRepository userRepository;

	@GetMapping("/signin")
	public String signin() {
		return "signin";
	}
	
	@PostMapping("/signin")
	public String signinPost(
		HttpSession session,
		@RequestParam String email,
		@RequestParam String pwd,
		Model model
	) {
		Optional<User> opt = userRepository.findByEmail(email);
		
		if(opt.isEmpty()) {
			model.addAttribute("error", "로그인 실패");
			return "signin";
		}
		
		// DB의 유저 정보 저장
		User user = opt.get();

		if(user.getPwd().equals(pwd)) {
			session.setAttribute("user_info", user);
		} else {
			model.addAttribute("error", "로그인 실패");
		}
		return "redirect:/";
	}

	@GetMapping("/signout")
	public String signout(
		HttpSession session
	) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/signup") 
	public String signup() {
		return "signup";
	}

	@PostMapping("/signup")
	public String signupPost(
		@ModelAttribute User user,
		RedirectAttributes model
	) {
		Optional<User> opt = userRepository.findByEmail(user.getEmail());
		if(opt.isEmpty()) {
			userRepository.save(user);
		} else {
			model.addFlashAttribute("error", "이미 사용중인 이메일입니다.");
			return "redirect:/signup";
		}
		return "redirect:/signin";
	}
}