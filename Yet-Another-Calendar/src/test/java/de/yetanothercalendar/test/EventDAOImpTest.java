package de.yetanothercalendar.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.fortuna.ical4j.model.property.DtStamp;

import org.junit.Test;

import de.yetanothercalendar.model.dao.impl.EventDAOImpl;
import de.yetanothercalendar.model.dao.impl.UserDAOImpl;
import de.yetanothercalendar.model.database.Event;
import de.yetanothercalendar.model.database.User;
import de.yetanothercalendar.model.database.helper.DatabaseConnectionManager;

import junit.framework.TestCase;

public class EventDAOImpTest extends TestCase {

	@Test
	public void testCreateEventTable() {
		try {

			EventDAOImpl event = new EventDAOImpl(
					new DatabaseConnectionManager("admin", "admin",
							"localhost", 3306, "yetanothercalendar"));
			assertNotNull("Anlegen des UserDAOs fehlgeschlagen!", event);
			event.createEventTable();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@Test
	public void testCreateEvents() {
		try {

			EventDAOImpl event = new EventDAOImpl(
					new DatabaseConnectionManager("admin", "admin",
							"localhost", 3306, "yetanothercalendar"));
			UserDAOImpl user = new UserDAOImpl(new DatabaseConnectionManager(
					"admin", "admin", "localhost", 3306, "yetanothercalendar"));
			User testUser = user.returnUser("zeller@yahoo.de");

			SimpleDateFormat sdf = new SimpleDateFormat();
		    sdf.applyPattern( "yyyy-MM-DD hh:mm" );
		    
			Date dtstamp = sdf.parse("2012-01-02 12:30");
			
			System.out.println(dtstamp);
			Date dtstart = sdf.parse("2012-01-04 10:30");
			Date created = sdf.parse("2012-01-02 12:35");
			Date lastmod = sdf.parse("2012-01-03 10:45");
			Date dtend = sdf.parse("2012-01-06 18:00");
			long duration = 135;
			List<String> categories = new ArrayList<String>();
			categories.add("fhleflöajfö");
			Date exdate = sdf.parse("2012-01-06 10:45");
			Date rdate = sdf.parse("2012-01-06 14:30");

			event.createEvents(new Event(testUser, dtstamp, "12340", dtstart,
					created, "blatest", lastmod, "at Home", "wichtig", "fdsf",
					"fadf561", "fnölfvnöae56156", dtend, duration, "ffffff",
					categories, "Hallo das ist ein comment", exdate, rdate));

		} catch (Exception e) {
			e.getMessage();
		}
	}

}
