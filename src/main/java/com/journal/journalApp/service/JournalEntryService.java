package com.journal.journalApp.service;

import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.journal.journalApp.entity.User;
import com.journal.journalApp.entity.journalEntry;
import com.journal.journalApp.repository.journalEntryRepo;

@Component
public class JournalEntryService {
	
	
	@Autowired 
	private journalEntryRepo journalEntryRepository;
	
	@Autowired
	private UserService userService;
	
	@Transactional
	public void saveEntry(journalEntry entry, String userName) {
		try {
			User user = userService.findByUserName(userName);
			journalEntry saved= journalEntryRepository.save(entry);
			user.getJournalEntries().add(saved);
			userService.saveEntry(user);
		}
		catch(Exception e) {
			System.out.println(e);
			throw new RuntimeException(e);
		}
		
	}
	public void saveEntry(journalEntry entry) {
		
		journalEntryRepository.save(entry);
		
	}
	
	public List<journalEntry> getAll(){
		return journalEntryRepository.findAll();
	}
	
	public Optional<journalEntry> findById(ObjectId id) {
		return journalEntryRepository.findById(id);
	}
	
	public void deleteById(ObjectId id, String userName) {
		User user = userService.findByUserName(userName);
		user.getJournalEntries().removeIf(x->x.getId().equals(id));
		userService.saveEntry(user);
		journalEntryRepository.deleteById(id);
	}
}
