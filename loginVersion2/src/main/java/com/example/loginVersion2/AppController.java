package com.example.loginVersion2;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {
	
	//ScriptEngineManager manager = new ScriptEngineManager();
	//ScriptEngine engine = manager.getEngineByName("JavaScript");

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private CandidateRepository canRepo;
	
	@GetMapping("")
	public String viewHomePage() {	
	

			 
		return "index";
	}
	
	@GetMapping("/homepage")
	public String getUsernameHome(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		User user = repo.findByUsername(username);
		model.addAttribute("user", user);

		return "homepage";
	}
	
	@GetMapping("/signup")
	public String showSignUp(Model model) {
		model.addAttribute("user", new User());

		return "signup";
	}
	

	@PostMapping("/process_register")
	public String processRegestration(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		repo.save(user);
		return "register_success";
	}
	
	@PostMapping("/confirm_vote")
	public String processVote(long id) {
		
		Candidate candidateToVote = canRepo.findById(id);
		int oldVote = candidateToVote.getVotes();
		candidateToVote.setVotes(oldVote + 1);
		
		canRepo.save(candidateToVote);
		
		return "submitted";
	}
	
}
