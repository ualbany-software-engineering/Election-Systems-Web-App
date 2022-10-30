package com.example.loginVersion2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Jon Rhea
 * Interface that extends methods from JpaRepository that find Candidates from different attributes
 */
public interface CandidateRepository extends JpaRepository<Candidate, Integer>{
	
	public Candidate findById(long id);
	public Candidate findByName(String name);
}//end interface
