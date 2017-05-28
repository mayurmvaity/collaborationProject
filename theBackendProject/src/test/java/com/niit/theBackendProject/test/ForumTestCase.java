package com.niit.theBackendProject.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.theBackendProject.config.RootConfig;
import com.niit.theBackendProject.dao.ForumDAO;
import com.niit.theBackendProject.dto.Forum;

public class ForumTestCase {
private static AnnotationConfigApplicationContext context;
	
	private static ForumDAO forumDAO;
	
	private Forum forum;
	
	@BeforeClass
	public static void init() {
		
		context = new AnnotationConfigApplicationContext(RootConfig.class);

		
		forumDAO = (ForumDAO) context.getBean("forumDAO");
		
	}
	
	@Test
	public void testCRUDForum() {
		//add operation
		forum =new Forum();
		
		forum.setFtitle("Materialize CSS");
		forum.setFdata("This is Materialize CSS forum");
		
		assertEquals("Successfully added a forum inside the table",true,forumDAO.add(forum));
		
		
		forum =new Forum();
		
		forum.setFtitle("Bower");
		forum.setFdata("This is Bower forum");
		
		assertEquals("Successfully added a forum inside the table",true,forumDAO.add(forum));
		
		//fetchaing and updating
		forum = forumDAO.get(2);
		
		forum.setFtitle("NPM");
	
		assertEquals("Successfully updated a forum in the table",true,forumDAO.update(forum));
		
		//delete the category
		forum = forumDAO.get(2);
		assertEquals("Successfully deleted a forum in the table",true,forumDAO.delete(forum));
		
		//fetching the list
		assertEquals("Successfully fetched the list of a forums from the table",3,forumDAO.list().size());
		
	}
}
