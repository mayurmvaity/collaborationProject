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

import com.niit.theBackendProject.dao.FpostDAO;
import com.niit.theBackendProject.dto.Fpost;

@RestController
public class FpostController {

	@Autowired
	FpostDAO fpostDAO;
	
	@Autowired
	Fpost fpost;
	
	// adding new forum post
	@RequestMapping(value="/fpost/new",method = RequestMethod.POST)
	public ResponseEntity<Fpost> addNewFpost(@RequestBody Fpost fpost) {
		System.out.println("Adding new fpost");
			
			boolean b =fpostDAO.add(fpost);
			if(b) System.out.println("fpost added Successfully");
			else System.out.println("fpost NOT added");
			
		return new ResponseEntity<Fpost>(fpost, HttpStatus.OK);
	}
	
	// to retrieve list of all forum posts
	@RequestMapping(value = {"/fpost/list"}, method = RequestMethod.GET)
	public ResponseEntity<List<Fpost>> fetchAllFposts() {
		System.out.println("fetching list of all fposts");
		List<Fpost> fpost = fpostDAO.list();
		return new ResponseEntity<List<Fpost>>(fpost, HttpStatus.OK);
	}
	
	// to retrieve list of forum posts by forum id
	@RequestMapping(value = {"/fpost/list/{forumid}"}, method = RequestMethod.GET)
	public ResponseEntity<List<Fpost>> fetchForumsFposts(@PathVariable("forumid") int forumid) {
		System.out.println("fetching list of fposts by forumid");
		List<Fpost> fpost = fpostDAO.fplistByforumid(forumid);
		return new ResponseEntity<List<Fpost>>(fpost, HttpStatus.OK);
	}
}
