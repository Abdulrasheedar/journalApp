package com.journal.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.journal.journalApp.entity.User;
import com.journal.journalApp.service.UserService;

@RestController
@RequestMapping("/public")
public class publicController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/create-user")
	public void createUser(@RequestBody User user) {
		userService.saveEntry(user);
	}

}
