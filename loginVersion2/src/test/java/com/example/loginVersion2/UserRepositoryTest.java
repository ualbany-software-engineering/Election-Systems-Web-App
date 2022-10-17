package com.example.loginVersion2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

		@Autowired
		private UserRepository repo;
		
		@Autowired
		private TestEntityManager entityManager;
		
		@Test
		public void testCreateUser() {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			//change info to run different test
			User user = new User();
			user.setName("Jonathan Rhea");
			user.setPassword(encoder.encode("jontest"));
			user.setUsername("jonrhea");
			
			User savedUser = repo.save(user);
			
			User existUser = entityManager.find(User.class, savedUser.getId());
			
			assertThat(existUser.getName()).isEqualTo(savedUser.getName());
		}//end testCreateUser
		
		
		@Test
		public void testFindUserbyUsername() {
			
			//change name to run different test
			String username = "jonrhea";
			
			User user = repo.findByUsername(username);
			
			assertThat(user).isNotNull();
		}//end testFindUserByUsername
	
	
}//end test class
