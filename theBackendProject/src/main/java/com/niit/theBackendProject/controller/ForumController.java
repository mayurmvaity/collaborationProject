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

import com.niit.theBackendProject.dao.ForumDAO;
import com.niit.theBackendProject.dto.Forum;
import com.niit.theBackendProject.dto.Job;

@RestController
public class ForumController {
	

	@Autowired
	ForumDAO forumDAO;
	
	@Autowired
	Forum forum;
	
	 @RequestMapping(value="/forum/new",method = RequestMethod.POST)
		public ResponseEntity<Forum> addNewBlog(@RequestBody Forum forum) {
			System.out.println("Adding new forum");
				
				boolean b =forumDAO.add(forum);
				if(b) System.out.println("Forum added Successfully");
				else System.out.println("Forum NOT added");
				
			return new ResponseEntity<Forum>(forum, HttpStatus.OK);
		}
	 
	// edit forum
	@RequestMapping(value="/edit/forum",method = RequestMethod.POST)
	public ResponseEntity<Forum> editForum(@RequestBody Forum forum) {
		System.out.println("edit forum");
		// getting old data
		Forum forum1 = forumDAO.get(forum.getForumid());
			// added new data to old forum
			forum1 = forum;
						
			boolean b =forumDAO.update(forum1);
			if(b) System.out.println("Forum updated Successfully");
			else System.out.println("Forum not updated");
						
		return new ResponseEntity<Forum>(forum1, HttpStatus.OK);
	}
	 
	// to retrieve list of forums
	 @RequestMapping(value = {"/forum/list"}, method = RequestMethod.GET)
		public ResponseEntity<List<Forum>> fetchForums() {
			System.out.println("fetching list of forums");
			List<Forum> forum = forumDAO.list();
			return new ResponseEntity<List<Forum>>(forum, HttpStatus.OK);
		}
	 
	// to get single forum
	 @RequestMapping(value = {"/forum/{id}"}, method = RequestMethod.GET)
		public ResponseEntity<Forum> viewForum(@PathVariable("id") int id) {
			System.out.println("Calling single Forum method");
			Forum forum = null;
			forum = forumDAO.get(id);
			
			if(forum == null) {
				forum = new Forum();
				forum.setErrCode("404");
				forum.setErrMessage("User not found!");
			}
			else {
				forum.setErrCode("200");
				forum.setErrMessage("User found!");
			}
			System.out.println("End of single Forum method");
			return new ResponseEntity<Forum>(forum, HttpStatus.OK);
			
		}
	 
	// to retrieve list of forums by userid creater id
		@RequestMapping(value = {"/forum/mylist/{userid}"}, method = RequestMethod.GET)
		public ResponseEntity<List<Forum>> fetchJoinedForums(@PathVariable("userid") int userid) {
			System.out.println("fetching list of created forums");
			List<Forum> forum = forumDAO.listByUserid(userid);
			return new ResponseEntity<List<Forum>>(forum, HttpStatus.OK);
		}
	 
	 /*******************/
}
