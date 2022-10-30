package com.example.loginVersion2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * @author Jon Rhea
 * Starts the Spring.io application
 */
@SpringBootApplication
@ComponentScan({"com.example.loginVersion2"})
public class A_Start {

	/**
	 * Runs the application
	 * @param args Main parameters
	 */
	public static void main(String[] args) {
		
		SpringApplication.run(A_Start.class, args);
	}//end main
}//end class
