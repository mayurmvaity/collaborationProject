package com.niit.theBackendProject.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.theBackendProject.config.RootConfig;
import com.niit.theBackendProject.dao.UserDAO;
import com.niit.theBackendProject.dto.Usertable;

public class UsertableTestCase {
private static AnnotationConfigApplicationContext context;
	
	private static UserDAO userDAO;
	
	private Usertable user;
	
	@BeforeClass
	public static void init() {
		
		context = new AnnotationConfigApplicationContext(RootConfig.class);

		
		userDAO = (UserDAO) context.getBean("userDAO");
		
	}
	
	@Test
	public void testCRUDUsertable() {
		//add operation
		user =new Usertable();
		
		user.setFname("Carl");
		user.setLname("Johnson");
		user.setAdd1("LS1");
		user.setAdd2("LS2");
		user.setAdd3("LS3");
		user.setCity("LS");
		user.setState("USR");
		user.setPincode("400081");
		user.setEmail("cj@gta.com");
		user.setPw("carl1234");
		user.setPno("7788995522");
		user.setRole("User");
		
		
		assertEquals("Successfully added a user inside the table",true,userDAO.add(user));
		
		
		user =new Usertable();
		
		user.setFname("Bert");
		user.setLname("Allen");
		user.setAdd1("StarCity1");
		user.setAdd2("StarCity2");
		user.setAdd3("StarCity3");
		user.setCity("StarCity");
		user.setState("DCU");
		user.setPincode("400081");
		user.setEmail("impulse@dc.com");
		user.setPw("bert1234");
		user.setPno("7766995522");
		user.setRole("User");
		
		
		assertEquals("Successfully added a user inside the table",true,userDAO.add(user));
		
		//fetchaing and updating
		user = userDAO.get(13);
					
		user.setFname("Savitar");
				
		assertEquals("Successfully updated a user in the table",true,userDAO.update(user));
		
		//delete the category
		user = userDAO.get(13);
		assertEquals("Successfully deleted a user in the table",true,userDAO.delete(user));
		
		//fetching the list
		assertEquals("Successfully fetched the list of a users from the table",5,userDAO.list().size());
		
	}
}
