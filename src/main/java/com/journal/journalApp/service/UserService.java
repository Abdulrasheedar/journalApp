package com.journal.journalApp.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.journal.journalApp.entity.User;
import com.journal.journalApp.repository.UserRepo;


@Component
public class UserService {
	
	
	@Autowired 
	private UserRepo userRepository;
	
	public void saveEntry(User user) {
		userRepository.save(user);
	}
	
	public List<User> getAll(){
		return userRepository.findAll();
	}
	
	public Optional<User> findById(ObjectId id) {
		return userRepository.findById(id);
	}
	
	public void deleteById(ObjectId id) {
		userRepository.deleteById(id);
	}
	
	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}
}
