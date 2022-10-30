package com.example.loginVersion2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * @author Jon Rhea
 * This class handles all GET and POST requests that require back-end and database work
 */
@Controller
public class AppController {
	

	//declare repositories
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private CandidateRepository canRepo;
	
	@Autowired
	private TopicRepository topicRepo;
	
	/**
	 * Views index.html when to page is passed in the URL
	 * @return The index page
	 */
	@GetMapping("")
	public String viewHomePage() {	
		return "index";
	}//end viewHomePage
	
	/**
	 * Gets the user's username and adds it to the Model
	 * @param model The Model variables shared with the front-end
	 * @return The homepage 
	 */
	@GetMapping("/homepage")
	public String getUsernameHome(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		User user = repo.findByUsername(username);
		model.addAttribute("user", user);

		return "homepage";
	}//end getUsernameHome
	
	/**
	 * Creates a blank user object for users who are about to sign up
	 * @param model The Model variables shared with the front-end
	 * @return The signup page
	 */
	@GetMapping("/signup")
	public String showSignUp(Model model) {
		model.addAttribute("user", new User());

		return "signup";
	}//end showSignUp
	
	/**
	 * Redirects to the admin home page if the user is "admin"
	 * @param model The Model variables shared with the front-end
	 * @return The adminhome page is user is admin, regular homepage is user is not admin
	 */
	@GetMapping("/adminhome")
	public String showAdmin(Model model) {
		
		model.addAttribute("newTopic", new Topic());
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName().toString();
		//String realUsername = username.substring(1, username.length() - 1);
		String admin = "admin";
		
		if(!(username.equals("admin"))) {
			return getUsernameHome(model);
		}//end if

		return "adminhome";
	}//end showAdmin

	/**
	 * If topic is active, get's the candidates to the topic passed and sends it to the front-end
	 * @param name The name of the first candidate in a topic; this is what is sued to figure out the topic
	 * @param model The Model variables shared with the front-end
	 * @return The voting page is the topic is active, the inactive error page is topic is not active
	 */
	@GetMapping("/voting")
	public String showVoting(@RequestParam(name="q", required=false) String name, Model model) {
		Candidate candidate1 = null;
		Candidate candidate2 = null;
		
		Topic topicInfo = null;
		
		candidate1 = canRepo.findByName(name);
		candidate2 = canRepo.findById(candidate1.getId() + 1);
		
		topicInfo = topicRepo.findByCandidate1(name);
		
		
		if(topicInfo.getActive() == 0) {
			return "inactive";
		}//end if
		
		model.addAttribute("candidate1", candidate1);
		model.addAttribute("candidate2", candidate2);
		model.addAttribute("topicInfo", topicInfo);
		
		return "voting";
	}//end showVoting
	
	/**
	 * Calculates who one a topic and sends that info to the front-end
	 * @param name The name of the first candidate in a topic; this is what is sued to figure out the topic
	 * @param model The Model variables shared with the front-end
	 * @return The result page
	 */
	@GetMapping("/result")
	public String resultCalcualte(@RequestParam(name="q", required=false) String name, Model model) {
		Candidate candidate1 = null;
		Candidate candidate2 = null;
		Topic topicInfo = null;
	
		topicInfo = topicRepo.findByCandidate1(name);
		candidate1 = canRepo.findByName(name);
		candidate2 = canRepo.findById(candidate1.getId() + 1);
		
		Candidate wonCandidate = null;
		Candidate lostCandidate = null;
		
		if(candidate1.getVotes() > candidate2.getVotes()) {
			wonCandidate = candidate1;
			lostCandidate = candidate2;
		}//end if
		else if(candidate2.getVotes() > candidate1.getVotes()) {
			wonCandidate = candidate2;
			lostCandidate = candidate1;
		}//end if
		else {
			//doesn't matter here, JavaScript will take care of the results in the event of a tie
			wonCandidate = candidate2;
			lostCandidate = candidate1;
		}//end else
		
		model.addAttribute("wonCandidate", wonCandidate);
		model.addAttribute("lostCandidate", lostCandidate);
		model.addAttribute("topicInfo", topicInfo);
		
		return "result";
	}//end resultCalculate
	
	/**
	 * Adds a new user to the database
	 * @param user The user to be added to the database; info is retrieved from front-end user input
	 * @return The register_success page
	 */
	@PostMapping("/process_register")
	public String processRegestration(User user) {
		//encode the password before putting it in the database
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		repo.save(user);
		return "register_success";
	}//end processRegistration
	
	/**
	 * Adds a new topic to the database
	 * @param newTopic The new topic to be added to the database; info is retrieved from front-end admin input
	 * @return The topic_success page
	 */
	@PostMapping("/add_topic")
	public String processTopic(Topic newTopic) {
		
		String name1 = newTopic.getCandidate1();
		String name2 = newTopic.getCandidate2();
		
		Candidate candidate1 = new Candidate(name1);
		Candidate candidate2 = new Candidate(name2);
		newTopic.setActive(1);
		topicRepo.save(newTopic);
		canRepo.save(candidate1);
		canRepo.save(candidate2);
		
		return "topic_success";
	}//end processTopic
	
	/**
	 * Changes to active status of a topic
	 * @param topicName The topic to change the active status; info is retrieved from front-end admin input
	 * @return The topic_success page (may create its own page later)
	 */
	@PostMapping("/change_active")
	public String changeActive(@RequestParam String topicName) {
		
		Topic topic = topicRepo.findByName(topicName);
		int currentStatus = topic.getActive();
		
		if(currentStatus == 0) {
			topic.setActive(1);
			topicRepo.save(topic);
		}//end if
		else{
			topic.setActive(0);
			topicRepo.save(topic);
		}//end else
		
		return "topic_success";
	}//end changeActive
	
	/**
	 * Sets the voting0 variable to 0 for all users
	 * @return The topic_success page (may create its own page later)
	 */
	@PostMapping("reset_vote0")
	public String resetVote0() {
		
		User user = null;
		long totalEntries = repo.count();
		long looper = 0;
		long currentId = 0;
		
		while(looper < totalEntries) {
			user = repo.findById(currentId);
			currentId++;
			if(user != null) {
				user.setVoting0(0);
				looper++;
				repo.save(user);
				user = null;
			}//end if
		}//end while
		
		return "topic_success";
	}//end resetVote0
	
	/**
	 * Sets the voting1 variable to 0 for all users
	 * @return The topic_success page (may create its own page later)
	 */
	@PostMapping("reset_vote1")
	public String resetVote1() {
		
		User user = null;
		long totalEntries = repo.count();
		long looper = 0;
		long currentId = 0;
		
		while(looper < totalEntries) {
			user = repo.findById(currentId);
			currentId++;
			if(user != null) {
				user.setVoting1(0);
				repo.save(user);
				looper++;
				user = null;
			}//end if
		}//end while
		
		return "topic_success";
	}//end resetVote1
	
	/**
	 * Sets the voting2 variable to 0 for all users
	 * @return The topic_success page (may create its own page later)
	 */
	@PostMapping("reset_vote2")
	public String resetVote2() {
		
		User user = null;
		long totalEntries = repo.count();
		long looper = 0;
		long currentId = 0;
		
		while(looper < totalEntries) {
			user = repo.findById(currentId);
			currentId++;
			if(user != null) {
				user.setVoting2(0);
				repo.save(user);
				looper++;
				user = null;
			}//end if
		}//end while
		
		return "topic_success";
	}//end resetVote2
	
	/**
	 * Confirms that the vote is valid and adds it towards the candidate
	 * @param id The id of the candidate to vote for
	 * @return The submitted page is vote is successful, the inactive page if not successful
	 */
	@PostMapping("/confirm_vote")
	public String processVote(long id) {
		Topic topicInfo = null;
		Candidate candidateToVote = canRepo.findById(id);
		
		topicInfo = topicRepo.findByCandidate1(candidateToVote.getName());
		
		//if app cannot find candidate1 with that id, then it must be candidate2
		if(topicInfo == null) {
			topicInfo = topicRepo.findByCandidate2(candidateToVote.getName());
		}//end if
		
		//get user authentication to get for voting attributes
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName().toString();
		User user = repo.findByUsername(username);
		
		long voteSlot = topicInfo.getId() % 3;
		
		if(voteSlot == 0) {
			if(user.getVoting0() == 1) {
				return "inactive";
			}//end if
		}//end if
		else if(voteSlot == 1) {
			if(user.getVoting1() == 1) {
				return "inactive";
			}//end if
		}//end if
		else if(voteSlot == 2) {
			if(user.getVoting2() == 1) {
				return "inactive";
			}//end if
		}//end if
		
		//if code here is executed, the vote is valid
		int oldVote = candidateToVote.getVotes();
		candidateToVote.setVotes(oldVote + 1);
		
		canRepo.save(candidateToVote);
		
		if(voteSlot == 0) {
			user.setVoting0(1);
			repo.save(user);
		}//end if
		else if(voteSlot == 1) {
			user.setVoting1(1);
			repo.save(user);
		}//end if
		else if(voteSlot == 2) {
			user.setVoting2(1);
			repo.save(user);
		}//end if
		
		return "submitted";
	}//end processVote
	
}//end class
