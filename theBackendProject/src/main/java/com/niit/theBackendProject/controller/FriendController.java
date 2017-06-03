package com.niit.theBackendProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.theBackendProject.dao.FriendDAO;
import com.niit.theBackendProject.dto.Friend;
import com.niit.theBackendProject.dto.Job;

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
	 
	 /*********************/
	
}
