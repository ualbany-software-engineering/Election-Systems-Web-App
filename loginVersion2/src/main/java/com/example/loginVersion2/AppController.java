package com.example.loginVersion2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {

	@Autowired
	private UserRepository repo;
	
	@GetMapping("")
	public String viewHomePage() {	
		return "index";
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
	}//end user
	
}
