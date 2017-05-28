package com.niit.theBackendProject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.theBackendProject.dao.EventDAO;
import com.niit.theBackendProject.dto.Adminevent;
import com.niit.theBackendProject.dto.Blog;

@RestController
public class AdminEventController {
/*
	@Autowired
	HttpSession session;
*/
	@Autowired
	EventDAO eventDAO;
	
	@Autowired
	Adminevent evt;
	
	@RequestMapping(value="/adminevent/new",method = RequestMethod.POST)
	public ResponseEntity<Adminevent> addNewAdminEvent(@RequestBody Adminevent evt) {
		System.out.println("Adding new event");
			
			boolean b =eventDAO.add(evt);
			if(b) System.out.println("Event added Successfully");
			else System.out.println("Event NOT added");
			
		return new ResponseEntity<Adminevent>(evt, HttpStatus.OK);
	}
	
	 // to retrieve list of events
	 @RequestMapping(value = {"/adminevent/list"}, method = RequestMethod.GET)
		public ResponseEntity<List<Adminevent>> fetchAdminEvents() {
			System.out.println("fetching list of Adminevent");
			List<Adminevent> evt = eventDAO.list();
			return new ResponseEntity<List<Adminevent>>(evt, HttpStatus.OK);
		}
	 
	 // to get single event 
	 @RequestMapping(value = {"/adminevent/{id}"}, method = RequestMethod.GET)
		public ResponseEntity<Adminevent> viewSingleEvent(@PathVariable("id") int id) {
			System.out.println("Calling single event method");
			Adminevent evt = null;
			evt = eventDAO.get(id);
			
			
			/*blogDAO.updateBlog(blog);*/
			if(evt == null) {
				evt = new Adminevent();
				evt.setErrCode("404");
				evt.setErrMessage("event not found!");
			}
			else {
				evt.setErrCode("200");
				evt.setErrMessage("event found!");
			}
			System.out.println("End of single evt method");
			return new ResponseEntity<Adminevent>(evt, HttpStatus.OK);
			
		}
	 
}
