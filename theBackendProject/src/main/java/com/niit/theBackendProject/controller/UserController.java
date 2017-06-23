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

import com.niit.theBackendProject.dao.UserDAO;
import com.niit.theBackendProject.dto.Usertable;
import com.niit.theBackendProject.service.EmailService;

@RestController
public class UserController {
/*
	@Autowired
	HttpSession session;*/
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	Usertable user;
	
	@Autowired
	EmailService emailService;
	
	@RequestMapping(value="/user/new",method = RequestMethod.POST)
	public ResponseEntity<Usertable> addNewUser(@RequestBody Usertable user) {
		System.out.println("Adding new user");
			
			boolean b =userDAO.add(user);
			if(b) System.out.println("User registered Successfully");
			else System.out.println("User not registered");
			
		return new ResponseEntity<Usertable>(user, HttpStatus.OK);
	}
	
	// edit user profile 
	@RequestMapping(value="/edit/profile",method = RequestMethod.POST)
	public ResponseEntity<Usertable> editUserProfile(@RequestBody Usertable user) {
		System.out.println("edit user profile user");
			// getting old data
			Usertable user1 = userDAO.get(user.getUserid());
			// added new data to old profile
			user1 = user;
			
			boolean b =userDAO.update(user1);
			if(b) System.out.println("User updated Successfully");
			else System.out.println("User not updated");
			
		return new ResponseEntity<Usertable>(user, HttpStatus.OK);
	}
	
		// to retrieve list of users
		 @RequestMapping(value = {"/user/list"}, method = RequestMethod.GET)
			public ResponseEntity<List<Usertable>> fetchUsers() {
				System.out.println("fetching list of users");
				List<Usertable> user = userDAO.list();
				return new ResponseEntity<List<Usertable>>(user, HttpStatus.OK);
			}
		 
		// to retrieve list of unapproved users
		@RequestMapping(value = {"/user/nalist"}, method = RequestMethod.GET)
		public ResponseEntity<List<Usertable>> notApprovedList() {
				System.out.println("fetching na list of users");
				List<Usertable> user = userDAO.nalist();
				return new ResponseEntity<List<Usertable>>(user, HttpStatus.OK);
			}
		 
		// to get single user
		 @RequestMapping(value = {"/user/{id}"}, method = RequestMethod.GET)
			public ResponseEntity<Usertable> viewUser(@PathVariable("id") int id) {
				System.out.println("Calling single user method");
				Usertable user = null;
				user = userDAO.get(id);
				
				
				/*blogDAO.updateBlog(blog);*/
				if(user == null) {
					user = new Usertable();
					user.setErrCode("404");
					user.setErrMessage("User not found!");
				}
				else {
					user.setErrCode("200");
					user.setErrMessage("User found!");
				}
				System.out.println("End of single user method");
				return new ResponseEntity<Usertable>(user, HttpStatus.OK);
				
			}
		 
		 @RequestMapping(value="/add/{a}/{b}",method = RequestMethod.GET)
			public int additionTest(@PathVariable("a") int a, @PathVariable("b") int b) {
				System.out.println("Addition method");
					
				return (a+b);
			}
		 
		 @RequestMapping(value="/multiply/{a}/{b}",method = RequestMethod.GET)
			public int multiplicationTest(@PathVariable("a") int a, @PathVariable("b") int b) {
				System.out.println("Multiplication method");
					
				return (a*b);
			}
		 
		 @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
			public ResponseEntity<Usertable> validateUser(@RequestBody Usertable user) {
			
				if(user.getEmail() != null && user.getPw() != null) {
					if(userDAO.validateUser(user) == null) {
						user = new Usertable();
						user.setErrCode("400");
						user.setErrMessage("Invalid Credentials");
						return new ResponseEntity<Usertable>(user, HttpStatus.OK);
					} else {
						Usertable user1 = userDAO.getUserByEmail(user.getEmail());
						user1.setErrCode("200");
						user1.setErrMessage("Login Successful!");
						user1.setIsOnline('Y');
						boolean b = userDAO.update(user1);
						if(b) System.out.println("User set online");
						else System.out.println("User couldnt set online");
						return new ResponseEntity<Usertable>(user1 , HttpStatus.OK);
					}
					
				} else {
					user = new Usertable();
					return new ResponseEntity<Usertable>(user, HttpStatus.NO_CONTENT);
				}
				
				
			}
		 
		 @RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
			public ResponseEntity<Void> toLogout(@RequestBody Usertable user) {
				
				user.setIsOnline('N');
				boolean b = userDAO.update(user);
				if(b) System.out.println("User set Offline");
				else System.out.println("User couldn't set offline");
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
		 
		 // Function to approve user
		 @RequestMapping(value="/user/approv/{id}",method = RequestMethod.POST)
			public ResponseEntity<Usertable> updateUser(@PathVariable("id") int id) {
				System.out.println("updating user");
					
					
					Usertable user =userDAO.get(id);
					
					user.setIsApproved('Y');
					boolean b = userDAO.update(user);
					emailService.approvedUserMessage(user);
					if(b) System.out.println("User updated Successfully");
					else System.out.println("User NOT updated");
					
				return new ResponseEntity<Usertable>(user, HttpStatus.OK);
			}
		 
		 
			// Function to disapprove user
			@RequestMapping(value = "/user/disapprov/{id}", method = RequestMethod.POST)
			public ResponseEntity<Usertable> disapproveUser(@PathVariable("id") int id) {
				System.out.println("disapproving user");
		
				Usertable user = userDAO.get(id);
		
				user.setIsApproved('R');
				boolean b = userDAO.update(user);
				
				if (b)
					System.out.println("User disapproved Successfully");
				else
					System.out.println("User NOT disapproved");
		
				return new ResponseEntity<Usertable>(user, HttpStatus.OK);
			}
			
			// Function to make role User
			@RequestMapping(value = "/user/roleUser/{id}", method = RequestMethod.POST)
			public ResponseEntity<Usertable> makeRoleUser(@PathVariable("id") int id) {
				System.out.println("Making role user");
		
				Usertable user = userDAO.get(id);
		
				user.setRole("User");
				boolean b = userDAO.update(user);
				
				if (b)
					System.out.println("Made role user Successfully");
				else
					System.out.println("Made role NOT user");
		
				return new ResponseEntity<Usertable>(user, HttpStatus.OK);
			}
		
				// Function to make role Admin
				@RequestMapping(value = "/user/roleAdmin/{id}", method = RequestMethod.POST)
				public ResponseEntity<Usertable> makeRoleAdmin(@PathVariable("id") int id) {
					System.out.println("Making role admin");
					
					Usertable user = userDAO.get(id);
					
					user.setRole("Admin");
					boolean b = userDAO.update(user);
					
					if (b)
						System.out.println("Made role admin Successfully");
					else
						System.out.println("Made role NOT admin");
					
					return new ResponseEntity<Usertable>(user, HttpStatus.OK);
				}
		 
		// to retrieve list of approved users
		@RequestMapping(value = {"/user/aplist"}, method = RequestMethod.GET)
		public ResponseEntity<List<Usertable>> fetchApprvUsers() {
			System.out.println("fetching list of approved users");
			List<Usertable> user = userDAO.approvlist();
			return new ResponseEntity<List<Usertable>>(user, HttpStatus.OK);
		}
		 
			// to retrieve list of online friends
			@RequestMapping(value = { "/frnds/online/{userid}" }, method = RequestMethod.GET)
			public ResponseEntity<List<Usertable>> getOnlineFrnds(@PathVariable("userid") int userid) {
				System.out.println("Getting list of my online friends");
				List<Usertable> user = userDAO.getOnlineFrnds(userid);
				return new ResponseEntity<List<Usertable>>(user, HttpStatus.OK);
			}
		
			// checking existence of user method
			@RequestMapping(value = {"/checkuser"}, method = RequestMethod.POST)
			public ResponseEntity<Void> checkUsername(@RequestBody String email) {
				
				Usertable user = userDAO.getUserByEmail(email);
				if(user == null) {
					return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
				} else {
					return new ResponseEntity<Void>(HttpStatus.FOUND);
				}
			}
			
		 /*************/
}
