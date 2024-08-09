package com.journal.journalApp.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

@Document(collection = "users")
public class User {
	
	@Id
	private ObjectId id;
	
	@Indexed(unique = true)
	@NonNull
	private String userName;
	@NonNull
	private String password;
	@DBRef
	private List<journalEntry> journalEntries = new ArrayList<journalEntry>();
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<journalEntry> getJournalEntries() {
		return journalEntries;
	}
	public void setJournalEntries(List<journalEntry> journalEntries) {
		this.journalEntries = journalEntries;
	}
	

}
 