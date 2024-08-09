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

import com.journal.journalApp.entity.journalEntry;
import com.journal.journalApp.service.JournalEntryService;

@RestController
@RequestMapping("/journal")
public class journalController {
	

	@Autowired
	private JournalEntryService journalEntryService;
	
	@GetMapping
	public ResponseEntity<?> getAll(){
		List<journalEntry> all = journalEntryService.getAll();
		if(all!=null&& !all.isEmpty()) {
		return new ResponseEntity<>(all, HttpStatus.OK);
		}
		return new ResponseEntity<>(all, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<journalEntry> createEntry(@RequestBody journalEntry entry) {
		
		try {
			entry.setDate(LocalDateTime.now());
			journalEntryService.saveEntry(entry);
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
	
	@DeleteMapping("id/{myId}")
	public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId) {
		journalEntryService.deleteById(myId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("id/{id}")
	public ResponseEntity<?> updateJournalEntryById(@PathVariable ObjectId id,@RequestBody journalEntry updateEntry) {
		
		
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
