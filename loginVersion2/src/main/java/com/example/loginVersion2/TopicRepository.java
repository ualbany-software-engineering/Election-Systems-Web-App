package com.example.loginVersion2;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * @author Jon Rhea
 * Interface that extends methods from JpaRepository that find Topics from different attributes
 */
public interface TopicRepository extends JpaRepository<Topic, Integer>{
	
	public Topic findById(long id);
	public Topic findByName(String name);
	public Topic findByCandidate1(String candidate1);
	public Topic findByCandidate2(String candidate2);

}//end interface
