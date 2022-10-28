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
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@GetMapping("/adminhome")
	public String showAdmin(Model model) {
		
		model.addAttribute("user", new User());

		return "adminhome";
	}
	
	@GetMapping("/voting")
	public String showVoting(@RequestParam(name="q", required=false) String name, Model model) {
		Candidate candidate1 = null;
		Candidate candidate2 = null;
		
		candidate1 = canRepo.findByName(name);
		candidate2 = canRepo.findById(candidate1.getId() + 1);
		
		model.addAttribute("candidate1", candidate1);
		model.addAttribute("candidate2", candidate2);
		
		return "voting";
	}
	
	@GetMapping("/result")
	public String resultCalcualte(@RequestParam(name="q", required=false, defaultValue="Ford") String name, Model model) {
		Candidate candidate1 = null;
		Candidate candidate2 = null;
		
		
		candidate1 = canRepo.findByName(name);
		candidate2 = canRepo.findById(candidate1.getId() + 1);
		
		Candidate wonCandidate = null;
		Candidate lostCandidate = null;
		boolean tie = false;
		
		if(candidate1.getVotes() > candidate2.getVotes()) {
			wonCandidate = candidate1;
			lostCandidate = candidate2;
		}//end if
		else if(candidate2.getVotes() > candidate1.getVotes()) {
			wonCandidate = candidate2;
			lostCandidate = candidate1;
		}//end if
		else {
			tie = true;
		}//end else
		
		
		if(tie == false) {
			
		}//end if
		//System.out.printf("Candidate won id = %d\n", wonCandidate.id);
		//System.out.printf("Candidate lost id = %d\n", lostCandidate.id);
		model.addAttribute("wonCandidate", wonCandidate);
		model.addAttribute("lostCandidate", lostCandidate);
		
		return "result";
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
