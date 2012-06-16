package de.yetanothercalendar.test;

import org.junit.Test;

import de.yetanothercalendar.model.dao.impl.EventDAOImpl;
import de.yetanothercalendar.model.database.Event;
import de.yetanothercalendar.model.database.helper.DatabaseConnectionManager;

import junit.framework.TestCase;

public class EventDAOImpTest extends TestCase {

	@Test
	public void testCreateEventTable() {
		try{
			
		
		EventDAOImpl event = new EventDAOImpl(new DatabaseConnectionManager(
				"admin", "admin", "localhost", 3306, "yetanothercalendar"));
		assertNotNull("Anlegen des UserDAOs fehlgeschlagen!", event);
		event.createEventTable();
		}catch(Exception e){
			e.getMessage();
		}
	}
	
	@Test
	public void testCreateEvents(){
		try{
			
		
		EventDAOImpl user = new EventDAOImpl(new DatabaseConnectionManager(
				"admin", "admin", "localhost", 3306, "yetanothercalendar"));
		user.createEvents( new Event() );
		
		}catch(Exception e){
			e.getMessage();
		}
	}

}
