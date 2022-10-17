package com.example.loginVersion2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CandidateRepository extends JpaRepository<Candidate, Integer>{
	
	public Candidate findById(long id);
}//end class
