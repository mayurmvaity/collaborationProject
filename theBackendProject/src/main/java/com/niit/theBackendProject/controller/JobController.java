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

import com.niit.theBackendProject.dao.JobDAO;
import com.niit.theBackendProject.dto.Forum;
import com.niit.theBackendProject.dto.Job;
import com.niit.theBackendProject.dto.Usertable;

@RestController
public class JobController {

/*	@Autowired
	HttpSession session;*/

	@Autowired
	JobDAO jobDAO;
	
	@Autowired
	Job job;
	
	@RequestMapping(value="/job/new",method = RequestMethod.POST)
	public ResponseEntity<Job> addNewJob(@RequestBody Job job) {
		System.out.println("Adding new job");
			
			boolean b =jobDAO.add(job);
			if(b) System.out.println("job added Successfully");
			else System.out.println("job NOT added");
			
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}
	
			// to retrieve list of jobs
			 @RequestMapping(value = {"/job/list"}, method = RequestMethod.GET)
				public ResponseEntity<List<Job>> fetchJobs() {
					System.out.println("fetching list of jobs");
					List<Job> job = jobDAO.list();
					return new ResponseEntity<List<Job>>(job, HttpStatus.OK);
				}
			 
			// to get single job
			 @RequestMapping(value = {"/job/{id}"}, method = RequestMethod.GET)
				public ResponseEntity<Job> viewUser(@PathVariable("id") int id) {
					System.out.println("Calling single job method");
					Job job = null;
					job = jobDAO.get(id);
					
					
					/*blogDAO.updateBlog(blog);*/
					if(job == null) {
						job = new Job();
						job.setErrCode("404");
						job.setErrMessage("User not found!");
					}
					else {
						job.setErrCode("200");
						job.setErrMessage("User found!");
					}
					System.out.println("End of single job method");
					return new ResponseEntity<Job>(job, HttpStatus.OK);
					
				}
}
