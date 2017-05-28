package com.niit.theBackendProject.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.theBackendProject.config.RootConfig;
import com.niit.theBackendProject.dao.BlogCommentDAO;
import com.niit.theBackendProject.dto.BlogComment;

public class BlogCommentTestCase {

private static AnnotationConfigApplicationContext context;
	
	private static BlogCommentDAO blogcommentDAO;
	
	private BlogComment comm;
	
	@BeforeClass
	public static void init() {
		
		context = new AnnotationConfigApplicationContext(RootConfig.class);

		
		blogcommentDAO = (BlogCommentDAO) context.getBean("blogcommentDAO");
		
	}
	
	@Test
	public void testCRUDBlogComment() {
		//add operation
		comm =new BlogComment();
		
		comm.setBlogid(22);
		comm.setCommdata("this is blog test data 1");
		
		assertEquals("Successfully added a blogcomment inside the table",true,blogcommentDAO.add(comm));
		
		comm =new BlogComment();
		
		comm.setBlogid(25);
		comm.setCommdata("this is blog test data 2");
		
		assertEquals("Successfully added a blogcomment inside the table",true,blogcommentDAO.add(comm));
		
		
		//fetchaing and updating
		comm = blogcommentDAO.get(2);
		
		comm.setCommdata("this is blog test data 3xxxxxxxxxxxxxxxxx");
	
		assertEquals("Successfully updated a blog comment in the table",true,blogcommentDAO.update(comm));
		
		//delete the blog comment
		comm = blogcommentDAO.get(2);
		assertEquals("Successfully deleted a blog comment in the table",true,blogcommentDAO.delete(comm));
		
		//fetching the list
		assertEquals("Successfully fetched the list of a blog comment from the table",1,blogcommentDAO.list().size());
		
	}
}
