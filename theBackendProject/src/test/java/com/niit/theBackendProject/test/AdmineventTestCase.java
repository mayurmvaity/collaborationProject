package com.niit.theBackendProject.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.theBackendProject.config.RootConfig;
import com.niit.theBackendProject.dao.EventDAO;
import com.niit.theBackendProject.dto.Adminevent;

public class AdmineventTestCase {

private static AnnotationConfigApplicationContext context;
	
	private static EventDAO eventDAO;
	
	private Adminevent evt;
	
	@BeforeClass
	public static void init() {
		
		context = new AnnotationConfigApplicationContext(RootConfig.class);

		
		eventDAO = (EventDAO) context.getBean("eventDAO");
		
	}
	
	@Test
	public void testCRUDEvent() {
		//add operation
		evt =new Adminevent();
		
		evt.setEtitle("Rose day");
		evt.setEdata("This is Rose day event data");
		
		assertEquals("Successfully added a event inside the table",true,eventDAO.add(evt));
		
		
		evt =new Adminevent();
		
		evt.setEtitle("Sports day");
		evt.setEdata("This is Sports day event data");
		
		assertEquals("Successfully added a event inside the table",true,eventDAO.add(evt));
		
		//fetchaing and updating
		evt = eventDAO.get(2);
		
		evt.setEtitle("Other day");
	
		assertEquals("Successfully updated a event in the table",true,eventDAO.update(evt));
		
		//delete the category
		evt = eventDAO.get(2);
		assertEquals("Successfully deleted a event in the table",true,eventDAO.delete(evt));
		
		//fetching the list
		assertEquals("Successfully fetched the list of a events from the table",6,eventDAO.list().size());
		
	}
}
