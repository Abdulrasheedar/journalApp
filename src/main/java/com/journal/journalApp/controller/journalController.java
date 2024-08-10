package com.journal.journalApp.controller;

import java.time.LocalDateTime;
import java.util.*;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.journal.journalApp.entity.User;
import com.journal.journalApp.entity.journalEntry;
import com.journal.journalApp.service.JournalEntryService;
import com.journal.journalApp.service.UserService;

@RestController
@RequestMapping("/journal")
public class journalController {
	

	@Autowired
	private JournalEntryService journalEntryService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("{userName}")
	public ResponseEntity<?> getAlljournalEntriesOfUser(@PathVariable String userName){
		User user = userService.findByUserName(userName);
		List<journalEntry> all = user.getJournalEntries();
		if(all!=null&& !all.isEmpty()) {
		return new ResponseEntity<>(all, HttpStatus.OK);
		}
		return new ResponseEntity<>(all, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("{userName}") 
	public ResponseEntity<journalEntry> createEntry(@RequestBody journalEntry entry,@PathVariable String userName) {
		
		try {
			
			entry.setDate(LocalDateTime.now());
			journalEntryService.saveEntry(entry,userName);
			return new ResponseEntity<>(entry,HttpStatus.CREATED);
		}
		catch(Exception e){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("id/{myId}")
	public ResponseEntity<journalEntry> getJournalEntryById(@PathVariable ObjectId myId) {
		Optional<journalEntry> jE=journalEntryService.findById(myId);
		
		if(jE.isPresent()) {
			return new ResponseEntity<>(jE.get(),HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("id/{userName}/{myId}")
	public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId,@PathVariable String userName) {
		journalEntryService.deleteById(myId,userName);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("id/{userName}/{id}")
	public ResponseEntity<?> updateJournalEntryById(@PathVariable ObjectId id,@RequestBody journalEntry updateEntry,@PathVariable String userName) {
		
		
		journalEntry old = journalEntryService.findById(id).orElse(null);
		if(old!=null) {
			old.setTitle(updateEntry.getTitle()!=null && !updateEntry.getTitle().equals("")? updateEntry.getTitle():old.getTitle());
			old.setContent(updateEntry.getContent()!=null && !updateEntry.getContent().equals("")? updateEntry.getContent():old.getContent());
			journalEntryService.saveEntry(old);
			return new ResponseEntity<>(old,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		
	}
	
}
