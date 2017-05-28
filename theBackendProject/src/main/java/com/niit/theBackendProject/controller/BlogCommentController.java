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

import com.niit.theBackendProject.dao.BlogCommentDAO;
import com.niit.theBackendProject.dao.BlogDAO;
import com.niit.theBackendProject.dto.Blog;
import com.niit.theBackendProject.dto.BlogComment;

@RestController
public class BlogCommentController {

	@Autowired
	BlogCommentDAO blogcommentDAO;
	
	@Autowired
	BlogComment comm;
	
	@Autowired
	BlogDAO blogDAO;
	
	@Autowired
	Blog blog;
	
	@RequestMapping(value="/bcomm/new",method = RequestMethod.POST)
	public ResponseEntity<BlogComment> addNewBComment(@RequestBody BlogComment comm) {
		System.out.println("Adding new BlogComment comm");
			
			int bid = comm.getBlogid();
			blog = blogDAO.get(bid);
			int nc = blog.getBcomments();
			blog.setBcomments(++nc);
			boolean x = blogDAO.update(blog);
			if(x) System.out.println("Blog updated");
			else System.out.println("Blog NOT updated");
		
			boolean b =blogcommentDAO.add(comm);
			if(b) System.out.println("BlogComment added Successfully");
			else System.out.println("BlogComment NOT added");
			
		return new ResponseEntity<BlogComment>(comm, HttpStatus.OK);
	}
	
	// to retrieve list of all blog comments
	 @RequestMapping(value = {"/bcomm/list"}, method = RequestMethod.GET)
		public ResponseEntity<List<BlogComment>> fetchBComm() {
			System.out.println("fetching list of Blog comments");
			List<BlogComment> comm = blogcommentDAO.list();
			return new ResponseEntity<List<BlogComment>>(comm, HttpStatus.OK);
		}
	 // list by blogid
	 @RequestMapping(value = {"/bcomm/list/{blogid}"}, method = RequestMethod.GET)
		public ResponseEntity<List<BlogComment>> fetchBCommByBlogid(@PathVariable("blogid") int blogid) {
			System.out.println("fetching list of Blog comments by blogid");
			List<BlogComment> comm = blogcommentDAO.listByBlogid(blogid);
			return new ResponseEntity<List<BlogComment>>(comm, HttpStatus.OK);
		}
	 
	 /********************/
}
