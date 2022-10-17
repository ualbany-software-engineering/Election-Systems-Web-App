package com.example.loginVersion2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CandidateRepoTest {

	@Autowired
	private CandidateRepository canRepo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateCandidate() {
		
		//change name to run different test
		Candidate candidate = new Candidate("Chevy");
		
		Candidate savedCandidate = canRepo.save(candidate);
		
		Candidate existCandidate = entityManager.find(Candidate.class, savedCandidate.getId());
		
		assertThat(existCandidate.getName()).isEqualTo(savedCandidate.getName());
		
	}//end testCreateCandidate
	

	
	@Test
	public void testFindCandidatebyId() {
		
		//change id to run different test
		long Id = 5;
		
		Candidate candidate = canRepo.findById(Id);
		
		assertThat(candidate).isNotNull();
	}//end testFindUserByUsername
	
	@Test
	public void testVote() {
		Candidate candidateToVote = canRepo.findById(1);
		int oldVote = candidateToVote.getVotes();
		candidateToVote.setVotes(oldVote + 1);
		
		int newVote = candidateToVote.getVotes();
		
		assertThat(oldVote == (newVote - 1));
		
	}//end testVote
}
