package com.example.loginVersion2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Jon Rhea
 * All the information about a candidate and the database attributes
 */
@Entity
@Table(name = "candidates")

public class Candidate {
	
	//eclare all database attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	
	@Column(nullable = false, unique = true, length = 20)
	private String name;

	@Column(nullable = false, unique = true, length = 20, updatable = true)
	private int votes;
	
	//getters and setters for all attributes are listed below
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getVotes() {
		return votes;
	}
	
	public void setVotes(Integer votes) {
		this.votes = votes;
	}
	
	public Candidate(String name) {
		super();
		this.name = name;
		votes = 0;
	}
	
	/**
	 * Constructs a blank Candidate object
	 */
	public Candidate() {
		super();
	}//end constructor
	
}//end class
