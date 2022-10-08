package com.example.appengine.demos.springboot.service;

import org.springframework.stereotype.Component;

import com.example.appengine.demos.springboot.Announcement;

@Component
public class MessageService {
	
	static Announcement announcement;
	
	static {
		announcement = new Announcement();
		announcement.setId("A1");
		announcement.setTitle("Alert");
		announcement.setMessage("New Announcement!");
	}
	
	public Announcement getAnnouncement() {
		return announcement;
	}
	
	public void setAnnouncement(String id, String title, String message) {
		announcement.setId(id);
		announcement.setTitle(title);
		announcement.setMessage(message);
	}

}
