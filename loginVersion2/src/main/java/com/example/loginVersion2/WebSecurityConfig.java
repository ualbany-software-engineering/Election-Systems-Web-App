package com.example.loginVersion2;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 
 * @author Jon Rhea
 * This class handles all authentication methods
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	//declare datasource
	@Autowired
	private DataSource dataSource;
	
	/**
	 * Bean to create a new CustomUserDetailsService
	 * @return The new CustomUserDetailsService object
	 */
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}//end userDetailsService
	
	/**
	 * Bean to create a new BCryptPasswordEncoder
	 * @return The new BCryptPasswordEncoder object
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}//end passwordEncoder
	
	/**
	 * Sets up the authentication information
	 * @return The new authentication provider 
	 * Note that the password encoder is the BCryptPasswordEncoder method from above
	 */
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}//end authenticatoinProvider

	/**
	 * Actually configures the authentication provider
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}//end configure

	/**
	 * Checks to see if user is authenticated before visitng any of the pages in the app
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/homepage").authenticated()
			.antMatchers("/voting").authenticated()
			.antMatchers("/results").authenticated()
			.antMatchers("/sumbitted").authenticated()
			.antMatchers("/result").authenticated()
			.antMatchers("/adminhome").authenticated()
			.antMatchers("/inactive").authenticated()
			.antMatchers("/topic_success").authenticated()
			.anyRequest().permitAll()
			.and()
			.formLogin()
			.usernameParameter("username")
			.defaultSuccessUrl("/homepage")
			.permitAll()
			.and()
			.logout().logoutSuccessUrl("/").permitAll();
	}//end configure
}//end class
