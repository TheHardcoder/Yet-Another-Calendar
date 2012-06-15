package de.yetanothercalendar.test;

import org.junit.Test;

import junit.framework.TestCase;
import de.yetanothercalendar.model.dao.impl.EventDAOImpl;
import de.yetanothercalendar.model.database.helper.DatabaseConnectionManager;

public class EventDAOTest extends TestCase {

	@Test
	public void testCreateEventTable(){
		
		EventDAOImpl user = new EventDAOImpl(new DatabaseConnectionManager("admin", "admin", "localhost", 3306, "yetanothercalendar"));
		assertNotNull("Anlegen des EventDAOs fehlgeschlagen!", user);
		user.createEventTable();
		
	}
	
}
