package com.niit.theBackendProject.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.theBackendProject.config.RootConfig;
import com.niit.theBackendProject.dao.FmemberDAO;
import com.niit.theBackendProject.dao.ForumDAO;
import com.niit.theBackendProject.dao.UserDAO;
import com.niit.theBackendProject.dto.Fmember;

public class FmemberTestCase {
	private static AnnotationConfigApplicationContext context;
	
	private static FmemberDAO fmemberDAO;
	
	private static UserDAO userDAO;
	
	private static ForumDAO forumDAO;
	
	private Fmember fmember;

	@BeforeClass
	public static void init() {
		
		context = new AnnotationConfigApplicationContext(RootConfig.class);

		
		fmemberDAO = (FmemberDAO) context.getBean("fmemberDAO");
		
	}
	
	@Test
	public void testCRUDFmember() {
		//add operation
		fmember =new Fmember();
		
		/*Usertable user = userDAO.get(21);
		fmember.setUser(user);
		Forum forum = forumDAO.get(20);
		fmember.setForum(forum);*/
		
		assertEquals("Successfully added a fmember inside the table",true,fmemberDAO.add(fmember));
		
		
		fmember =new Fmember();
		
		/*Usertable user1 = userDAO.get(21);
		fmember.setUser(user1);
		Forum forum1 = forumDAO.get(20);
		fmember.setForum(forum1);*/
		
		assertEquals("Successfully added a fmember inside the table",true,fmemberDAO.add(fmember));
		
		
		//fetchaing and updating
		fmember = fmemberDAO.get(15);
		
		fmember.setIsApproved('Y');
	
		assertEquals("Successfully updated a fmember in the table",true,fmemberDAO.update(fmember));
		
		//delete the fmember
		fmember = fmemberDAO.get(15);
		assertEquals("Successfully deleted a fmember in the table",true,fmemberDAO.delete(fmember));
		
		//fetching the list
		assertEquals("Successfully fetched the list of fmembers from the table",3,fmemberDAO.list().size());
		
	}
}
