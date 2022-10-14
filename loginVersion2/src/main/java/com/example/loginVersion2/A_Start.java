package com.example.loginVersion2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.example.loginVersion2"})
public class A_Start {

	public static void main(String[] args) {
		
		SpringApplication.run(A_Start.class, args);
	}
	
	

}
