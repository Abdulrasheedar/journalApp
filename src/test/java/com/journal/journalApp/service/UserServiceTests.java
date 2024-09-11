package com.journal.journalApp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.journal.journalApp.entity.User;
import com.journal.journalApp.repository.UserRepo;

@SpringBootTest
public class UserServiceTests {
	
	@Autowired
	private UserRepo userRepository;
	
	@Disabled
	@Test
	public void testFindByUserName() {
		
		User user = userRepository.findByUserName("Abdul");
		assertTrue(!user.getJournalEntries().isEmpty());
	}
	
	
	@ParameterizedTest
	@CsvSource({
		"1,1,2",
		"2,2,5"
	})
	public void test(int a , int b, int exp) {
		assertEquals(exp, a+b);
		
	}
	

}
