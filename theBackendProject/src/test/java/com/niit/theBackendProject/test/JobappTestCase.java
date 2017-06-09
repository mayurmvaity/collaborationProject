package com.niit.theBackendProject.test;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.theBackendProject.config.RootConfig;
import com.niit.theBackendProject.dao.JobDAO;
import com.niit.theBackendProject.dao.JobappDAO;
import com.niit.theBackendProject.dao.UserDAO;
import com.niit.theBackendProject.dto.Job;
import com.niit.theBackendProject.dto.Jobapp;
import com.niit.theBackendProject.dto.Usertable;

public class JobappTestCase {
	
	private static AnnotationConfigApplicationContext context;
	
	private static JobappDAO jobappDAO;
	
	private static JobDAO jobDAO;
	
	private static UserDAO userDAO;
	
	private Jobapp jobapp;
	
	@BeforeClass
	public static void init() {
		
		context = new AnnotationConfigApplicationContext(RootConfig.class);

		
		jobappDAO = (JobappDAO) context.getBean("jobappDAO");
		
	}
	
	@Test
	public void testCRUDJobapp() {
		//add operation
		jobapp =new Jobapp();
		Job job = new Job();
		job.setJtitle("xxx");
		job.setJdata("11111111111111111111111");
		jobapp.setJob(job);
		Usertable user = new Usertable();
		user.setFname("Gustov");
		user.setLname("Mota");
		jobapp.setUser(user);
		
		Date date = new Date();
		jobapp.setAppdate(date);
		
		assertEquals("Successfully added a job application in the table",true,jobappDAO.add(jobapp));
		
		jobapp =new Jobapp();
		job.setJtitle("zzzzzzzzzzzzz");
		job.setJdata("11111111999999999999999111111111111111");
		jobapp.setJob(job);
		user.setFname("Gustov");
		user.setLname("Mota");
		jobapp.setUser(user);
		
		date = new Date();
		jobapp.setAppdate(date);
		
		assertEquals("Successfully added a job application in the table",true,jobappDAO.add(jobapp));
		
		
		//fetchaing and updating
		jobapp = jobappDAO.get(2);
		
		job.setJtitle("nnnnnnnnn");
		job.setJdata("666666666666600");
		jobapp.setJob(job);
	
		assertEquals("Successfully updated a job application in the table",true,jobappDAO.update(jobapp));
		
		//delete the category
		jobapp = jobappDAO.get(2);
		assertEquals("Successfully deleted a job application in the table",true,jobappDAO.delete(jobapp));
		
		//fetching the list
		assertEquals("Successfully fetched the list of a job applications from the table",1,jobappDAO.list().size());
		
	}
	
}
