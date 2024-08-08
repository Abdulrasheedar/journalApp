package com.journal.journalApp.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.journal.journalApp.entity.journalEntry;
import com.journal.journalApp.repository.journalEntryRepo;

@Component
public class JournalEntryService {
	
	
	@Autowired 
	private journalEntryRepo journalEntryRepository;
	
	public void saveEntry(journalEntry entry) {
		journalEntryRepository.save(entry);
	}
	
	public List<journalEntry> getAll(){
		return journalEntryRepository.findAll();
	}
	
	public Optional<journalEntry> findById(ObjectId id) {
		return journalEntryRepository.findById(id);
	}
	
	public void deleteById(ObjectId id) {
		journalEntryRepository.deleteById(id);
	}
}
