package com.niit.theBackendProject.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.theBackendProject.config.RootConfig;
import com.niit.theBackendProject.dao.BlogDAO;
import com.niit.theBackendProject.dto.Blog;

public class BlogTestCase {
	
private static AnnotationConfigApplicationContext context;
	
	private static BlogDAO blogDAO;
	
	private Blog blog;
	
	@BeforeClass
	public static void init() {
		
		context = new AnnotationConfigApplicationContext(RootConfig.class);

		
		blogDAO = (BlogDAO) context.getBean("blogDAO");
		
	}
	
	@Test
	public void testCRUDBlog() {
		//add operation
		blog =new Blog();
		//user.setUserid(1);
		blog.setBtitle("Java");
		blog.setBdata("This is java blog");
		
		System.out.println("before add query");
		assertEquals("Successfully added a user inside the table",true,blogDAO.add(blog));
		System.out.println("After add query");
		
		blog =new Blog();
		//user.setUserid(1);
		blog.setBtitle("SQL");
		blog.setBdata("This is SQL blog");
		
		System.out.println("before add query");
		assertEquals("Successfully added a user inside the table",true,blogDAO.add(blog));
		System.out.println("After add query");
		
		//fetchaing and updating
		blog = blogDAO.get(28);
		
		blog.setBtitle("MySQL");
	
		assertEquals("Successfully updated a user in the table",true,blogDAO.update(blog));
		
		//delete the category
		blog = blogDAO.get(28);
		assertEquals("Successfully deleted a user in the table",true,blogDAO.delete(blog));
		
		//fetching the list
		assertEquals("Successfully fetched the list of a categories from the table",11,blogDAO.list().size());
		
	}
}
