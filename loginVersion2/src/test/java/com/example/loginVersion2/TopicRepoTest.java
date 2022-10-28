package com.example.loginVersion2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class TopicRepoTest {
	
	@Autowired
	private TopicRepository topicRepo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateTopic() {
		
		//change name to run different test
		Topic testTopic = new Topic();
		
		testTopic.setCandidate1("Pyra");
		testTopic.setCandidate2("Mythra");
		testTopic.setName("Pyra vs Mythra");
		testTopic.setDescription("Who is the better character from Xenoblade Chronicles 2?");
		
		Topic savedTopic = topicRepo.save(testTopic);
		
		Topic existTopic = entityManager.find(Topic.class, savedTopic.getId());
		
		assertThat(existTopic.getName()).isEqualTo(savedTopic.getName());
		
	}//end testCreateTopic

	@Test
	public void testFindTopicById() {
		
		//change id to run different test
		long Id = 1;
		
		Topic topic = topicRepo.findById(Id);
		
		assertThat(topic).isNotNull();
		
	}//end testFindTopicById
	
	@Test
	public void testFindTopic() {
		
		//change name to run different test
		String topicString = "Pyra vs Mythra";
		
		Topic topic = topicRepo.findByName(topicString);
		
		assertThat(topic).isNotNull();
		
		
	}//end testFindTopic
	
	@Test
	public void testFindTopicCandidate1() {
		
		//change name to run different test
		String candidate1 = "Pyra";
		
		Topic topic = topicRepo.findByCandidate1(candidate1);
		
		assertThat(topic).isNotNull();
		
		
	}//end testFindTopicCandidate1
	
	@Test
	public void testChangeActive() {
		
		String topicString = "Pyra vs Mythra";
		
		Topic topic = topicRepo.findByName(topicString);
		
		int active = 1;
		
		topic.setActive(active);
		
		
		assertThat(topic.getActive()).isEqualTo(active);
		
		
	}//end testFindTopicCandidate1

}
