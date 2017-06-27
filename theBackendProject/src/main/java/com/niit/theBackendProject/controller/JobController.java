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
import com.niit.theBackendProject.dto.Adminevent;
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
	
			// edit job
			@RequestMapping(value="/edit/job",method = RequestMethod.POST)
			public ResponseEntity<Job> editJob(@RequestBody Job job) {
				System.out.println("edit job");
					// getting old data
				Job job1 = jobDAO.get(job.getJobid());
					// added new data to old job
					job1 = job;
					
					boolean b =jobDAO.update(job1);
					if(b) System.out.println("Job updated Successfully");
					else System.out.println("Job not updated");
					
				return new ResponseEntity<Job>(job1, HttpStatus.OK);
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
			 
			 	@RequestMapping(value="/job/delete/{jobid}",method = RequestMethod.POST)
				public ResponseEntity<Job> deleteAJob(@PathVariable("jobid") int jobid) {
					System.out.println("Deleting new job");
						
						Job job = jobDAO.get(jobid);
					
						boolean b =jobDAO.delete(job);
						if(b) System.out.println("job deleted Successfully");
						else System.out.println("job NOT deleted");
						
					return new ResponseEntity<Job>(job, HttpStatus.OK);
				}
			 
			 /************************/
}
