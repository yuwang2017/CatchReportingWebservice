/**
 * Copyright 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.appengine.demos.springboot;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.appengine.demos.springboot.bean.Person;
import com.example.appengine.demos.springboot.service.MessageService;



@RestController
public class HelloworldController {
	
	@Autowired
	MessageService aService;
	
  @GetMapping("/")
  public String hello() {
    return "Hello world - springboot-appengine-standard!";
  }
  
  @RequestMapping(value = "/json/getpersons", method = RequestMethod.GET)
	@ResponseBody
	public List<Person> uploadImage() throws Exception {
		
		List<Person> people = new ArrayList<Person>();
		
		people.add(new Person(1L, "Joe", "Smith"));
		people.add(new Person(2L, "Jane", "Doe"));
		people.add(new Person(3L, "Joe", "Wilson"));
		people.add(new Person(4L, "Paul", "Shiffer"));
		
		return people;

	}
  
  	
  	@RequestMapping(value = "/Permit-Mobile/rest/announce", method = RequestMethod.GET)
	@ResponseBody
	public Announcement getAnnouncement() throws Exception {	  
	  return aService.getAnnouncement();
	}
  	
  	
  	
  	
 	@RequestMapping(value = "/Permit-Mobile/rest/permit/{permitNumber}", method = RequestMethod.GET)
	@ResponseBody
	public Permit getPermit(@PathVariable String permitNumber ) throws Exception {
		
 		Permit ann = new Permit();
 		ann.setFirstName("Joe");
 		ann.setLastName("Doe");
 		ann.setVessel("Freedom");
 		ann.setVesselNumber("MD209AV");
	  
	  return ann;

	}
  	
  	
  	@RequestMapping(value = "/Permit-Mobile/rest/report", method = RequestMethod.POST)
	@ResponseBody
	public SubmitConfirmation report(HttpServletRequest request) throws Exception {
  		
  		String report = request.getParameter("report");
  		String email = request.getParameter("email");
  		String appVersion = request.getParameter("appVersion");
  		SubmitConfirmation ann = new SubmitConfirmation();
  		List<String> cons = new ArrayList<String>();
		if(report != null) {
	  		cons.add("CO-19271822");
	  		cons.add("CA-12981925");
	  		cons.add(email + " --- Version " + appVersion);
	  		ann.setConfirmNumberList(cons);
		} else {
			ann.setStatus("FAILED");
			cons.add("Input is empty");
			ann.setConfirmNumberList(cons);
		}
	
		return ann;

	}
  	
  	
  	
 	@RequestMapping(value = "/Permit-Mobile/rest/updateannouncement", method = RequestMethod.POST)
	@ResponseBody
	public void updateAnnoucement(HttpServletRequest request) throws Exception {
		
 		aService.setAnnouncement(request.getParameter("id"), request.getParameter("title"), request.getParameter("message"));
	
 		

	}
  
}
