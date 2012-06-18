package de.yetanothercalendar.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import de.yetanothercalendar.model.dao.impl.EventDAOImpl;
import de.yetanothercalendar.model.dao.impl.UserDAOImpl;
import de.yetanothercalendar.model.database.Event;
import de.yetanothercalendar.model.database.User;
import de.yetanothercalendar.model.database.helper.DatabaseConnectionManager;

public class EventDAOImpTest extends TestCase {

	@Test
	public void testCreateEventTable() {
		EventDAOImpl event = new EventDAOImpl(new DatabaseConnectionManager(
				"admin", "admin", "localhost", 3306, "yetanothercalendar"));
		assertNotNull("Anlegen des UserDAOs fehlgeschlagen!", event);
		event.createEventTable();
	}

	@Test
	public void testCreateEvents() throws ParseException {
		EventDAOImpl event = new EventDAOImpl(new DatabaseConnectionManager(
				"admin", "admin", "localhost", 3306, "yetanothercalendar"));
		UserDAOImpl user = new UserDAOImpl(new DatabaseConnectionManager(
				"admin", "admin", "localhost", 3306, "yetanothercalendar"));
		if (user.checkUser("zeller@yahoo.de", "test")) {
			System.out.println("Email-Adresse und Passwort stimmen");
			User testUser = user.returnUser("zeller@yahoo.de");
			SimpleDateFormat sdf = new SimpleDateFormat();
			sdf.applyPattern("yyyy-MM-dd HH:mm");
			Date dtstamp = sdf.parse("2012-02-02 12:30");
			System.out.println("Datum: " + dtstamp);
			System.out.println(dtstamp);
			Date dtstart = sdf.parse("2012-02-07 15:30");
			Date created = sdf.parse("2012-02-02 12:35");
			Date lastmod = sdf.parse("2012-01-03 10:45");
			Date dtend = sdf.parse("2012-04-06 13:15");
			long duration = 135;
			List<String> categories = new ArrayList<String>();
			categories.add("fhlefl�ajf�");
			Date exdate = sdf.parse("2012-01-06 10:45");
			Date rdate = sdf.parse("2012-01-06 14:30");
			if (event.createEvents(new Event(testUser, dtstamp, "12340",
					dtstart, created, "blatest", lastmod, "at Home", "wichtig",
					"fdsf", "fadf561", "fn�lfvn�ae56156", dtend, duration,
					"ffffff", categories, "Hallo das ist ein comment", exdate,
					rdate))) {
				System.out.println("Event wurde Erforgreich erstellt");
			} else {
				fail("Event wurde nicht erstellt ein Fehler ist aufgetretten");
			}

		} else {
			fail("E-Mailadresse oder Passwort sind falsch");
		}
	}
}
