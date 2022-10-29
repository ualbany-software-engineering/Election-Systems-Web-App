package com.example.loginVersion2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, unique = true, length = 20)
	private String username;
	@Column(nullable = false, length = 64)
	private String password;
	@Column(nullable = false, length = 20)
	private String name;
	
	@Column(nullable = false, unique = false)
	private int voting0;
	@Column(nullable = false, unique = false)
	private int voting1;
	@Column(nullable = false, unique = false)
	private int voting2;
	
	public int getVoting0() {
		return voting0;
	}
	public void setVoting0(int voting0) {
		this.voting0 = voting0;
	}
	public int getVoting1() {
		return voting1;
	}
	public void setVoting1(int voting1) {
		this.voting1 = voting1;
	}
	public int getVoting2() {
		return voting2;
	}
	public void setVoting2(int voting2) {
		this.voting2 = voting2;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	
}//end class
