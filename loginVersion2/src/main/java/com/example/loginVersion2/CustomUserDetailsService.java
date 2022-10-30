package com.example.loginVersion2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 
 * @author Jon Rhea
 *	This class allows users to be loaded by the app
 */
public class CustomUserDetailsService implements UserDetailsService {

	//declare repository
	@Autowired
	private UserRepository repo;
	
	/**
	 * Loads a user by their username
	 * @return the new CustomUserDetails object with the user
	 * @throws UserNameNotFoundException If the user cannot be found 
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repo.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("User not found");
		}//end if
		return new CustomUserDetails(user);
	}//end loadByUserName

}//end class
