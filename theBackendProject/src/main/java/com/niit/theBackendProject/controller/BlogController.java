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

import com.niit.theBackendProject.dao.BlogDAO;
import com.niit.theBackendProject.dto.Blog;
import com.niit.theBackendProject.dto.Usertable;


@RestController
public class BlogController {

	/*@Autowired
	HttpSession session;
	*/
	@Autowired
	BlogDAO blogDAO;
	
	@Autowired
	Blog blog;
	
//	@PostMapping(value = {"/blog/new"})
	 @RequestMapping(value="/blog/new",method = RequestMethod.POST)
	public ResponseEntity<Blog> addNewBlog(@RequestBody Blog blog) {
		System.out.println("Adding new blog");
			
			blog.setBlikes(0);
			blog.setBcomments(0);
		
			boolean b =blogDAO.add(blog);
			if(b) System.out.println("Blog added Successfully");
			else System.out.println("Blog NOT added");
			
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
	
	 // to retrieve list of blogs
	 @RequestMapping(value = {"/blog/list"}, method = RequestMethod.GET)
		public ResponseEntity<List<Blog>> fetchBlogs() {
			System.out.println("fetching list of blogs");
			List<Blog> blog = blogDAO.list();
			return new ResponseEntity<List<Blog>>(blog, HttpStatus.OK);
		}
	 
	 // to get single blog 
	 @RequestMapping(value = {"/blog/{id}"}, method = RequestMethod.GET)
		public ResponseEntity<Blog> viewBlog(@PathVariable("id") int id) {
			System.out.println("Calling single blog method");
			Blog blog = null;
			blog = blogDAO.get(id);
			
			
			/*blogDAO.updateBlog(blog);*/
			if(blog == null) {
				blog = new Blog();
				blog.setErrCode("404");
				blog.setErrMessage("Blog not found!");
			}
			else {
				blog.setErrCode("200");
				blog.setErrMessage("Blog found!");
			}
			System.out.println("End of single blog method");
			return new ResponseEntity<Blog>(blog, HttpStatus.OK);
			
		}
	 
	 @RequestMapping(value="/blog/update/{id}",method = RequestMethod.POST)
		public ResponseEntity<Blog> updateblog(@PathVariable("id") int id) {
			System.out.println("updating blog");
				
				
				Blog blog =blogDAO.get(id);
				int likes = blog.getBlikes();
				blog.setBlikes(++likes);
				boolean b = blogDAO.update(blog);
				if(b) System.out.println("Blog updated Successfully");
				else System.out.println("Blog NOT updated");
				
			return new ResponseEntity<Blog>(blog, HttpStatus.OK);
		}
	 
	 // list of unapproved blogs
	 @RequestMapping(value = {"/blog/nalist"}, method = RequestMethod.GET)
		public ResponseEntity<List<Blog>> fetchNaBlogs() {
			System.out.println("fetching list of bot approved blogs");
			List<Blog> blog = blogDAO.nabloglist();
			return new ResponseEntity<List<Blog>>(blog, HttpStatus.OK);
		}
	 
	 	// Function to approve blog
	 	@RequestMapping(value="/blog/approv/{id}",method = RequestMethod.POST)
		public ResponseEntity<Blog> approvBlog(@PathVariable("id") int id) {
			System.out.println("approving blog...");
						
				Blog blog =blogDAO.get(id);
						
				blog.setIsApproved('Y');
				boolean b = blogDAO.update(blog);
				if(b) System.out.println("Blog updated Successfully");
				else System.out.println("Blog NOT updated");
						
			return new ResponseEntity<Blog>(blog, HttpStatus.OK);
		}
	 
}
