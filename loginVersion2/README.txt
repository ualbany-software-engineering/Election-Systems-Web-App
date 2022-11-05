Step 1) Clone this repository to your local machine

Step 2) Import as a Maven Java project into your IDE of choice
	- You should be able to see the IDE find the pom.xml file

Step 3) For the Database, install MySQL Community	
	- Create an instance on localhost with username "root" (should be the default settings)
	- Set the password as "jonrheamysql" OR set your own password, copy it, 
	and replace the old password in line 4 of application.properties

Step 4) Configure the Database by creating three tables
	- First table is "users" with each coloum configured as follows (each line is a new column):
	id,       BIGINT,       Primary Key (PK),    Non-Null (NN),    Auto-Increment (AI)
	name,     VARCHAR(20),  Non-Null (NN),  
	password, VARCHAR(64),  Non-Null (NN),
	username, VARCHAR(20),  Non-Null (NN), Unique (UQ)
	
	- Second table is "candidates" with each coloum configured as follows (each line is a new column):
	id,       BIGINT,       Primary Key (PK), Non-Null (NN), Auto-Increment (AI)
	name,     VARCHAR(20),  Non-Null (NN)
	votes,    INT,		Non-Null (NN)

	- Third table is "topics" with each column configured as follows (each line is a new column):
	id,	     BIGINT,	   Primary Key (PK), Non-Null (NN), Auto-Increment (AI)
	name,	     VARCHAR(60),  Non-Null (NN)	
	candidate1,  VARCHAR(20),  Non-Null (NN)
	candidate2,  VARCHAR(20),  Non-Null (NN)
	description, VARCHAR(400), Non-Null (NN)
	active,	     INT,	   Non-Null (NN)
	
Step 5) Verify Database is working by running Unit Tests
	- testCreateUser() and testFindUserByUsername() in UserRepositoryTest.java
	- testCreateCandidate(), testFindCandidatebyId(), and testVote() in CandidateRepoTest.java
	- testCreateTopic(), testFindTopicById(), testFindTopic(), testFindTopicCandidate1, testChangeActive() in TopicRepoTest.java
	
Step 6) Run A_Start.java to launch application
	- See the application on localhost:8080
	