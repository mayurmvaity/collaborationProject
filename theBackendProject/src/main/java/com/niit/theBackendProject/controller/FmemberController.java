package com.niit.theBackendProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.theBackendProject.dao.FmemberDAO;
import com.niit.theBackendProject.dto.Fmember;

@RestController
public class FmemberController {

	@Autowired
	FmemberDAO fmemberDAO;
	
	@Autowired
	Fmember fmember;
	
	@RequestMapping(value="/fmember/new",method = RequestMethod.POST)
	public ResponseEntity<Fmember> addNewFmember(@RequestBody Fmember fmember) {
		System.out.println("Adding new fmember");
			
			boolean b =fmemberDAO.add(fmember);
			if(b) System.out.println("fmember added Successfully");
			else System.out.println("fmember NOT added");
			
		return new ResponseEntity<Fmember>(fmember, HttpStatus.OK);
	}
	
}
