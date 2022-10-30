package com.example.loginVersion2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * 
 * @author Jon Rhea
 * Interface that extends methods from JpaRepository that find Users from different attributes
 */
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE u.username = ?1")
	User findByUsername(String username);
	User findById(long id);
	
}//end class