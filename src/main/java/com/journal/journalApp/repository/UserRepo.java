package com.journal.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.journal.journalApp.entity.User;


public interface UserRepo extends MongoRepository<User, ObjectId > {
	
	User findByUserName(String userName);
	
	void deleteByUserName(String userName);

}
