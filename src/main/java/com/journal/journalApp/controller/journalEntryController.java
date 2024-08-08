//package com.journal.journalApp.controller;
//
//import java.util.*;
//
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.journal.journalApp.entity.journalEntry;
//
//@RestController
//@RequestMapping("/_journal")
//public class journalEntryController {
//	
//	
//	private Map<Long,journalEntry> journalEntries = new HashMap<>(); 
//
//	
//	@GetMapping
//	public List<journalEntry> getAll(){
//		return new ArrayList<>(journalEntries.values());
//	}
//	
//	@PostMapping
//	public boolean createEntry(@RequestBody journalEntry entry) {
//		journalEntries.put(entry.getId(), entry);
//		return true;
//	}
//	
//	@GetMapping("id/{myId}")
//	public journalEntry getJournalEntryById(@PathVariable Long myId) {
//		return journalEntries.get(myId);
//	}
//	
//	@DeleteMapping("id/{myId}")
//	public journalEntry deleteJournalEntryById(@PathVariable Long myId) {
//		return journalEntries.remove(myId);
//	}
//	
//	@PutMapping("id/{id}")
//	public journalEntry updateJournalEntryById(@PathVariable Long id,@RequestBody journalEntry entry) {
//		return journalEntries.put(id,entry);
//	}
//	
//}
