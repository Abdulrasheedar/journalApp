package com.journal.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.journal.journalApp.entity.journalEntry;

public interface journalEntryRepo extends MongoRepository<journalEntry, ObjectId > {

}
