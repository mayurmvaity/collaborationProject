package com.niit.theBackendProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.theBackendProject.dao.EventpartDAO;
import com.niit.theBackendProject.dto.Eventpart;

@RestController
public class EventpartController {

	@Autowired
	EventpartDAO eventpartDAO;
	
	@Autowired
	Eventpart eventpart;
	
	// add new event participant
	@RequestMapping(value = "/eventpart/new", method = RequestMethod.POST)
	public ResponseEntity<Eventpart> addNewJob(@RequestBody Eventpart eventpart) {
		System.out.println("Adding new event participant");

		boolean b = eventpartDAO.add(eventpart);
		if (b) System.out.println("Event participant added Successfully");
		else System.out.println("Event participant NOT added");

		return new ResponseEntity<Eventpart>(eventpart, HttpStatus.OK);
	}
	
	// to retrieve list of event participants
	@RequestMapping(value = { "/eventpart/list" }, method = RequestMethod.GET)
	public ResponseEntity<List<Eventpart>> fetchJobs() {
		System.out.println("fetching list of event participants");
		List<Eventpart> eventpart = eventpartDAO.list();
		return new ResponseEntity<List<Eventpart>>(eventpart, HttpStatus.OK);
	}
	
	// to retrieve list of event participants by event number
	@RequestMapping(value = { "/eventpart/list/{evtid}" }, method = RequestMethod.GET)
	public ResponseEntity<List<Eventpart>> fetchParticipantsBy(@PathVariable("evtid") int evtid) {
		System.out.println("fetching list of event participants by event number");
		List<Eventpart> eventpart = eventpartDAO.listByEpNumber(evtid);
		return new ResponseEntity<List<Eventpart>>(eventpart, HttpStatus.OK);
	}
	
}
