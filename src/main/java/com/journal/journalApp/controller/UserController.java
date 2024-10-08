package com.journal.journalApp.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.journal.journalApp.entity.User;
import com.journal.journalApp.repository.UserRepo;
import com.journal.journalApp.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepo userRepository;
	
	
	
	@PutMapping
	public ResponseEntity<?> updateUser(@RequestBody User user){
		Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		User userInDb = userService.findByUserName(userName); 
		userInDb.setUserName(user.getUserName());
		userInDb.setPassword(user.getPassword());
		userService.saveNewUser(userInDb);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteUserByID(){
		Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
		userRepository.deleteByUserName(authentication.getName());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
