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

import com.niit.theBackendProject.dao.FriendDAO;
import com.niit.theBackendProject.dto.Friend;
import com.niit.theBackendProject.dto.Usertable;

@RestController
public class FriendController {

	@Autowired
	FriendDAO friendDAO;
	
	@Autowired
	Friend friend;
	
	
	@RequestMapping(value="/friend/new",method = RequestMethod.POST)
	public ResponseEntity<Friend> addNewFriend(@RequestBody Friend friend) {
		System.out.println("Adding new friend");
			
			boolean b =friendDAO.add(friend);
			if(b) System.out.println("friend added Successfully");
			else System.out.println("friend NOT added");
			
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
	}
	
	// to retrieve list of friends
	 @RequestMapping(value = {"/friend/list"}, method = RequestMethod.GET)
		public ResponseEntity<List<Friend>> fetchFriends() {
			System.out.println("fetching list of friends");
			List<Friend> friends = friendDAO.list();
			return new ResponseEntity<List<Friend>>(friends, HttpStatus.OK);
		}
	 
	 	// to retrieve list of friend reqs sent
		 @RequestMapping(value = {"/friend/myList/{id}"}, method = RequestMethod.GET)
			public ResponseEntity<List<Usertable>> myFriends(@PathVariable("id") int id) {
				System.out.println("fetching list of friend reqs sent");
				List<Usertable> friends = friendDAO.frlist(id);
				return new ResponseEntity<List<Usertable>>(friends, HttpStatus.OK);
			}
		 
		// to retrieve list of friend reqs received
		@RequestMapping(value = { "/friend/req/rcvd/{id}" }, method = RequestMethod.GET)
		public ResponseEntity<List<Usertable>> myrcvreqFriends(@PathVariable("id") int id) {
			System.out.println("fetching list of friend reqs recieved");
			List<Usertable> friends = friendDAO.frReqrcvlist(id);
			return new ResponseEntity<List<Usertable>>(friends, HttpStatus.OK);
		}

		// to retrieve list of NOT friends by userid
		@RequestMapping(value = { "/friend/notfrList/{id}" }, method = RequestMethod.GET)
		public ResponseEntity<List<Usertable>> myNotFriends(@PathVariable("id") int id) {
			System.out.println("fetching list of friends");
			List<Usertable> friends = friendDAO.notfrlist(id);
			return new ResponseEntity<List<Usertable>>(friends, HttpStatus.OK);
		}
	 
		// to retrieve list of my friends by userid
		@RequestMapping(value = { "/friend/frndslist/{id}" }, method = RequestMethod.GET)
		public ResponseEntity<List<Usertable>> myAccFriends(@PathVariable("id") int id) {
			System.out.println("fetching list of friends");
			List<Usertable> friends = friendDAO.myfrlist(id);
			return new ResponseEntity<List<Usertable>>(friends, HttpStatus.OK);
		}
		
		// Function to approve frnd req
		@RequestMapping(value = "/frnd/req/accept/{userid1}/{userid2}", method = RequestMethod.POST)
		public ResponseEntity<Friend> acceptreq(@PathVariable("userid1") int userid1, @PathVariable("userid2") int userid2) {
			System.out.println("accepting req");
	
			Friend frnd = friendDAO.getByUsers(userid1, userid2);
	
			frnd.setIsFriend('Y');
			boolean b = friendDAO.update(frnd);
			
			if (b)
				System.out.println("req accepted Successfully");
			else
				System.out.println("req NOT accepted");
	
			return new ResponseEntity<Friend>(frnd, HttpStatus.OK);
		}
		
		// Function to reject frnd req
		@RequestMapping(value = "/frnd/req/reject/{userid1}/{userid2}", method = RequestMethod.POST)
		public ResponseEntity<Friend> rejectreq(@PathVariable("userid1") int userid1,
				@PathVariable("userid2") int userid2) {
			System.out.println("rejecting req");
	
			Friend frnd = friendDAO.getByUsers(userid1, userid2);
	
			frnd.setIsFriend('R');
			frnd.setActive('N');
			boolean b = friendDAO.update(frnd);
	
			if (b)
				System.out.println("req rejected Successfully");
			else
				System.out.println("req NOT rejected");
	
			return new ResponseEntity<Friend>(frnd, HttpStatus.OK);
		}
		
		
		
		
	 /*********************/
	
}
