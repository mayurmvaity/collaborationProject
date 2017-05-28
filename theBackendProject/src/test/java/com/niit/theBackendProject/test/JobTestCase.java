package com.niit.theBackendProject.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.theBackendProject.config.RootConfig;
import com.niit.theBackendProject.dao.JobDAO;
import com.niit.theBackendProject.dto.Job;

public class JobTestCase {
private static AnnotationConfigApplicationContext context;
	
	private static JobDAO jobDAO;
	
	private Job job;
	
	@BeforeClass
	public static void init() {
		
		context = new AnnotationConfigApplicationContext(RootConfig.class);

		
		jobDAO = (JobDAO) context.getBean("jobDAO");
		
	}
	
	@Test
	public void testCRUDJob() {
		//add operation
		job =new Job();
		
		job.setJtitle("Wipro");
		job.setJdata("This is Wipro data");
		
		assertEquals("Successfully added a job inside the table",true,jobDAO.add(job));
		
		job =new Job();
		
		job.setJtitle("IBM");
		job.setJdata("This is IBM data");
		
		assertEquals("Successfully added a job inside the table",true,jobDAO.add(job));
		
		
		//fetchaing and updating
		job = jobDAO.get(5);
		
		job.setJtitle("Apple Inc.");
	
		assertEquals("Successfully updated a job in the table",true,jobDAO.update(job));
		
		//delete the category
		job = jobDAO.get(5);
		assertEquals("Successfully deleted a job in the table",true,jobDAO.delete(job));
		
		//fetching the list
		assertEquals("Successfully fetched the list of a jobs from the table",4,jobDAO.list().size());
		
	}
}
