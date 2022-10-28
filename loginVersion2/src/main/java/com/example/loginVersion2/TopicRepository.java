package com.example.loginVersion2;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Integer>{
	
	public Topic findById(long id);
	public Topic findByName(String name);
	public Topic findByCandidate1(String candidate1);

}
