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

import com.niit.theBackendProject.dao.UserDAO;
import com.niit.theBackendProject.dto.Blog;
import com.niit.theBackendProject.dto.Usertable;

@RestController
public class UserController {
/*
	@Autowired
	HttpSession session;*/
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	Usertable user;
	
	@RequestMapping(value="/user/new",method = RequestMethod.POST)
	public ResponseEntity<Usertable> addNewBlog(@RequestBody Usertable user) {
		System.out.println("Adding new user");
			
			boolean b =userDAO.add(user);
			if(b) System.out.println("User registered Successfully");
			else System.out.println("User not registered");
			
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
						
						return new ResponseEntity<Usertable>(user1 , HttpStatus.OK);
					}
					
				} else {
					user = new Usertable();
					return new ResponseEntity<Usertable>(user, HttpStatus.NO_CONTENT);
				}
				
				
			}
		 
		 @RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
			public ResponseEntity<Void> toLogout(@RequestBody Usertable user) {
				
				//user.setOnline(false);
				//userDAO.updateUser(user);
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
		 
		 // Function to approve user
		 @RequestMapping(value="/user/approv/{id}",method = RequestMethod.POST)
			public ResponseEntity<Usertable> updateUser(@PathVariable("id") int id) {
				System.out.println("updating user");
					
					
					Usertable user =userDAO.get(id);
					
					user.setIsApproved('Y');
					boolean b = userDAO.update(user);
					if(b) System.out.println("User updated Successfully");
					else System.out.println("User NOT updated");
					
				return new ResponseEntity<Usertable>(user, HttpStatus.OK);
			}
}
