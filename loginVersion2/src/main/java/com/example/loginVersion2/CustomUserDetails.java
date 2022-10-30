package com.example.loginVersion2;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 
 * @author Jon Rhea
 * This class implements the methods os UserDetails
 */
public class CustomUserDetails implements UserDetails {

	private User user;
	
	/**
	 * Constructs a CustomUserDetails object
	 * @param user The user to add to this object
	 */
	public CustomUserDetails(User user) {
		super();
		this.user = user;
	}//end constructor

	//Must be implemented, but not used in this app
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	/**
	 * Gets user password
	 */
	@Override
	public String getPassword() {
		return user.getPassword();
	}//end getPasswotd

	/**
	 * Gets username
	 */
	@Override
	public String getUsername() {
		return user.getUsername();
	}//end getUsername

	//the following four methods are not used in this app
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	/**
	 * Gets the user's real name
	 * @return The user's real name
	 */
	public String getRealName() {
		return user.getName();
	}//end getRealName

}//end class
