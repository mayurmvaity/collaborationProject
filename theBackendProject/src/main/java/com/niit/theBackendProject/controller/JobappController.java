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

import com.niit.theBackendProject.dao.JobappDAO;
import com.niit.theBackendProject.dto.Jobapp;

@RestController
public class JobappController {

	@Autowired
	JobappDAO jobappDAO;
	
	@Autowired
	Jobapp jobapp;
	
	// applying for a job
	@RequestMapping(value="/jobapp/new",method = RequestMethod.POST)
	public ResponseEntity<Jobapp> addNewJobapp(@RequestBody Jobapp jobapp) {
		System.out.println("Adding new job application");
			
			boolean b =jobappDAO.add(jobapp);
			if(b) System.out.println("job application added Successfully");
			else System.out.println("job application NOT added");
			
		return new ResponseEntity<Jobapp>(jobapp, HttpStatus.OK);
	}
	
	// to retrieve list of jobs
	@RequestMapping(value = { "/jobapp/list" }, method = RequestMethod.GET)
	public ResponseEntity<List<Jobapp>> fetchJobapps() {
		System.out.println("fetching list of job applications");
		List<Jobapp> jobapp = jobappDAO.list();
		return new ResponseEntity<List<Jobapp>>(jobapp, HttpStatus.OK);
	}
	
	// to retrieve list of job applicants by jobid
	@RequestMapping(value = { "/jobapp/list/{jobid}" }, method = RequestMethod.GET)
	public ResponseEntity<List<Jobapp>> fetchJobappsByJobid(@PathVariable("jobid") int jobid) {
		System.out.println("fetching list of job applications by jobid");
		List<Jobapp> jobapp = jobappDAO.listByJobid(jobid);
		return new ResponseEntity<List<Jobapp>>(jobapp, HttpStatus.OK);
	}
	
}
