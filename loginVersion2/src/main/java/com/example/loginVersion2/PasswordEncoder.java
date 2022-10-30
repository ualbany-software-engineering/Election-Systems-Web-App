package com.example.loginVersion2;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 
 * @author Jon Rhea
 * Test for the password encoder
 */
public class PasswordEncoder {

	/**
	 * Runs the password test
	 * @param args Main parameters
	 */
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword = "test";
		String encodedPassword = encoder.encode(rawPassword);
		
		System.out.println(encodedPassword);

	}

}
