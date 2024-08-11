package com.journal.journalApp.controller;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	
	@GetMapping
	public ResponseEntity<?> getAlljournalEntriesOfUser(){
		Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		User user = userService.findByUserName(userName);
		List<journalEntry> all = user.getJournalEntries();
		if(all!=null&& !all.isEmpty()) {
		return new ResponseEntity<>(all, HttpStatus.OK);
		}
		return new ResponseEntity<>(all, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<journalEntry> createEntry(@RequestBody journalEntry entry) {
		
		try {
			Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
			String userName = authentication.getName();
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
		
		
		Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		User user = userService.findByUserName(userName);
		List<journalEntry> collect=user.getJournalEntries().stream().filter(x->x.getId().equals(myId)).collect(Collectors.toList());
		if(!collect.isEmpty()) {
			Optional<journalEntry> jE=journalEntryService.findById(myId);
			if(jE.isPresent()) {
				return new ResponseEntity<>(jE.get(),HttpStatus.OK);
			}
		}
		
		
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("id/{myId}")
	public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId) {
		Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		boolean removed = journalEntryService.deleteById(myId,userName);
		if(removed) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("id/{id}")
	public ResponseEntity<?> updateJournalEntryById(@PathVariable ObjectId id,@RequestBody journalEntry updateEntry) {
		Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		User user = userService.findByUserName(userName);
		List<journalEntry> collect=user.getJournalEntries().stream().filter(x->x.getId().equals(id)).collect(Collectors.toList());
		
		if(!collect.isEmpty()) {
			Optional<journalEntry> jE=journalEntryService.findById(id);
			if(jE.isPresent()) {
				journalEntry old = jE.get();
				old.setTitle(updateEntry.getTitle()!=null && !updateEntry.getTitle().equals("")? updateEntry.getTitle():old.getTitle());
				old.setContent(updateEntry.getContent()!=null && !updateEntry.getContent().equals("")? updateEntry.getContent():old.getContent());
				journalEntryService.saveEntry(old);
				return new ResponseEntity<>(old,HttpStatus.OK);
			}
		}
		
		
		
			
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		
	}
	
}
