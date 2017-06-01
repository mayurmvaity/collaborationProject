package com.niit.theBackendProject.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.theBackendProject.config.RootConfig;
import com.niit.theBackendProject.dao.FpostDAO;
import com.niit.theBackendProject.dto.Fpost;

public class FpostTestCase {
private static AnnotationConfigApplicationContext context;
	
	private static FpostDAO fpostDAO;
	
	private Fpost fpost;
	
	@BeforeClass
	public static void init() {
		
		context = new AnnotationConfigApplicationContext(RootConfig.class);

		
		fpostDAO = (FpostDAO) context.getBean("fpostDAO");
		
	}
	
	@Test
	public void testCRUDFpost() {
		//add operation
		fpost =new Fpost();

		fpost.setFpdata("this is some forum test post");
		
		assertEquals("Successfully added a fpost inside the table",true,fpostDAO.add(fpost));
		
		fpost =new Fpost();

		fpost.setFpdata("this is some forum test post 2");
		
		assertEquals("Successfully added 2nd fpost inside the table",true,fpostDAO.add(fpost));
		
		
		//fetchaing and updating
		fpost = fpostDAO.get(2);
		
		fpost.setFpdata("xxxxxxxxxx changed data");
	
		assertEquals("Successfully updated an fpost in the table",true,fpostDAO.update(fpost));
		
		//delete the fpost
		fpost = fpostDAO.get(2);
		assertEquals("Successfully deleted an fpost in the table",true,fpostDAO.delete(fpost));
		
		//fetching the list
		assertEquals("Successfully fetched the list of fpost from the table",1,fpostDAO.list().size());
		
	}
}
