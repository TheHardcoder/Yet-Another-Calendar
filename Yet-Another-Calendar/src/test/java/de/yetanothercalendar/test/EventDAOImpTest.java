package de.yetanothercalendar.test;

import org.junit.Test;

import junit.framework.TestCase;
import de.yetanothercalendar.model.dao.impl.EventDAOImpl;
import de.yetanothercalendar.model.dao.impl.UserDAOImpl;
import de.yetanothercalendar.model.database.Event;
import de.yetanothercalendar.model.database.helper.DatabaseConnectionManager;

public class EventDAOImpTest extends TestCase {

	@Test
	public void testCreateUserTable() {

		EventDAOImpl user = new EventDAOImpl(new DatabaseConnectionManager(
				"admin", "admin", "localhost", 3306, "yetanothercalendar"));
		assertNotNull("Anlegen des UserDAOs fehlgeschlagen!", user);
		user.createEventTable();
	}

}
