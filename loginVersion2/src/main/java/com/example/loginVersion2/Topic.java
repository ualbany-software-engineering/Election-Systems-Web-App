package com.example.loginVersion2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "topics")

public class Topic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	
	@Column(nullable = false, unique = true, length = 60)
	private String name;
	
	//NOTE: These two candidates are the NAMES of the candidates, not the candidate objects themselves
	@Column(nullable = false, unique = true, length = 20)
	private String candidate1;
	
	@Column(nullable = false, unique = true, length = 20)
	private String candidate2;
	
	@Column(nullable = false, unique = true, length = 400)
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

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

	public String getCandidate1() {
		return candidate1;
	}

	public void setCandidate1(String candidate1) {
		this.candidate1 = candidate1;
	}

	public String getCandidate2() {
		return candidate2;
	}

	public void setCandidate2(String candidate2) {
		this.candidate2 = candidate2;
	}
	
	public Topic() {
		super();
	}
}//end class
