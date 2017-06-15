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

import com.niit.theBackendProject.dao.FmemberDAO;
import com.niit.theBackendProject.dto.Fmember;
import com.niit.theBackendProject.dto.Usertable;

@RestController
public class FmemberController {

	@Autowired
	FmemberDAO fmemberDAO;
	
	@Autowired
	Fmember fmember;
	
	// new forum members' list
	@RequestMapping(value="/fmember/new",method = RequestMethod.POST)
	public ResponseEntity<Fmember> addNewFmember(@RequestBody Fmember fmember) {
		System.out.println("Adding new fmember");
			
			boolean b =fmemberDAO.add(fmember);
			if(b) System.out.println("fmember added Successfully");
			else System.out.println("fmember NOT added");
			
		return new ResponseEntity<Fmember>(fmember, HttpStatus.OK);
	}
	
	// list of not approved forum join requests
	 @RequestMapping(value = {"/fmember/nalist"}, method = RequestMethod.GET)
		public ResponseEntity<List<Fmember>> unapprovedfmembers() {
			System.out.println("fetching na list of fmembers");
			List<Fmember> fmember = fmemberDAO.nafmemberlist();
			return new ResponseEntity<List<Fmember>>(fmember, HttpStatus.OK);
		}
	 
	// list of approved forum join requests
		 @RequestMapping(value = {"/fmember/applist"}, method = RequestMethod.GET)
			public ResponseEntity<List<Fmember>> appfmembers() {
				System.out.println("fetching list of approved fmembers");
				List<Fmember> fmember = fmemberDAO.appfmemberlist();
				return new ResponseEntity<List<Fmember>>(fmember, HttpStatus.OK);
			}
		 
		// list of approved forum join requests
		@RequestMapping(value = {"/fmember/list"}, method = RequestMethod.GET)
		public ResponseEntity<List<Fmember>> fmemberslist() {
			System.out.println("fetching list of fmembers");
			List<Fmember> fmember = fmemberDAO.list();
			return new ResponseEntity<List<Fmember>>(fmember, HttpStatus.OK);
		}
		
		// list of approved forum join requests
		@RequestMapping(value = {"/fmember/tlist/{forumid}"}, method = RequestMethod.GET)
		public ResponseEntity<List<Fmember>> fmemberMlist(@PathVariable("forumid") int forumid) {
			System.out.println("fetching list of fmembers");
			List<Fmember> fmember = fmemberDAO.fmemberMlist(forumid);
			return new ResponseEntity<List<Fmember>>(fmember, HttpStatus.OK);
		}
		
		// Function to approve Fmember
		@RequestMapping(value="/fmember/approv/{fmemberid}",method = RequestMethod.POST)
		public ResponseEntity<Fmember> updateFmember(@PathVariable("fmemberid") int fmemberid) {
				System.out.println("updating fmember table");
							
				Fmember fmember = fmemberDAO.get(fmemberid);			
							
				fmember.setIsApproved('Y');
				boolean b = fmemberDAO.update(fmember);
				if(b) System.out.println("User updated Successfully");
				else System.out.println("User NOT updated");
							
				return new ResponseEntity<Fmember>(fmember, HttpStatus.OK);
		}
		
		// Function to disapprove Fmember
		@RequestMapping(value = "/fmember/disapprov/{fmemberid}", method = RequestMethod.POST)
		public ResponseEntity<Fmember> disapproveFmember(@PathVariable("fmemberid") int fmemberid) {
			System.out.println("disapproving fmember table");
	
			Fmember fmember = fmemberDAO.get(fmemberid);
	
			fmember.setIsApproved('R');
			fmember.setActive('N');
			boolean b = fmemberDAO.update(fmember);
			if (b)
				System.out.println("Fmember disapproved Successfully");
			else
				System.out.println("Fmember NOT disapproved");
	
			return new ResponseEntity<Fmember>(fmember, HttpStatus.OK);
		}
		
		// list of joined forums
		@RequestMapping(value = {"/fmember/mylist/{userid}"}, method = RequestMethod.GET)
		public ResponseEntity<List<Fmember>> myForumlist(@PathVariable("userid") int userid) {
			System.out.println("fetching list of my forums");
			List<Fmember> fmember = fmemberDAO.myForumlist(userid);
			return new ResponseEntity<List<Fmember>>(fmember, HttpStatus.OK);
		}
				
		
		/*********************/
}
