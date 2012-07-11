package de.yetanothercalendar.test;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import de.yetanothercalendar.model.dao.impl.EventDAOImpl;
import de.yetanothercalendar.model.dao.impl.UserDAOImpl;
import de.yetanothercalendar.model.database.Event;
import de.yetanothercalendar.model.database.User;
import de.yetanothercalendar.model.database.helper.DatabaseConnectionManager;


public class UserDAOImpEventDAOImpFullTest {

	static DatabaseConnectionManager manager = new DatabaseConnectionManager(
			"admin", "admin", "localhost", 3306, "testDB");
	static String createDB = "CREATE DATABASE IF NOT EXISTS testDB;";
	static String dropDB = "DROP DATABASE IF EXISTS testDB;";

	static private UserDAOImpl user = new UserDAOImpl(
			new DatabaseConnectionManager("admin", "admin", "localhost", 3306,
					"testDB"));;

	static private EventDAOImpl event = new EventDAOImpl(
			new DatabaseConnectionManager("admin", "admin", "localhost", 3306,
					"testDB"));

	static private User testUser = null;

	static private void createEvents() throws ParseException {
		if (user.checkUser("zeller@yahoo.de", "test")) {

			User testUser = user.returnUser("zeller@yahoo.de");
			SimpleDateFormat sdf = new SimpleDateFormat();
			sdf.applyPattern("yyyy-MM-dd HH:mm");

			Date dtstamp = sdf.parse("2012-02-02 11:29");
			Date dtstart = sdf.parse("2012-01-02 14:30");

			Date created = sdf.parse("2012-01-01 12:35");
			Date lastmod = sdf.parse("2012-01-03 10:45");
			Date dtend = sdf.parse("2012-01-06 13:15");
			long duration = 135;
			List<String> categories = new ArrayList<String>();
			categories.add("adsfasd");
			categories.add("fdfa");
			Date exdate = sdf.parse("2012-01-06 10:45");
			String rdate = ("Test 2012-01-06 10:45");
			// Erstellen Event 1 tstart auserhalb Auswahl Datum
			if (event.createEvents(new Event(testUser, dtstamp, "12345",
					dtstart, created, "blatest", lastmod, "at Home", "wichtig",
					"fdsf", null, "fndfdsfds5113", dtend, duration,
					"ffffff", categories, "Hallo das ist ein comment", exdate,
					rdate))) {

			} else {
				fail("Event wurde nicht erstellt ein Fehler ist aufgetretten");
			}
			// Erstellen Event 2 Beider inherhalb Auswahl Datum
			dtstart = sdf.parse("2012-01-06 14:30");
			created = sdf.parse("2012-01-04 12:35");
			dtend = sdf.parse("2012-01-08 13:15");
			if (event.createEvents(new Event(testUser, dtstamp, "56789",
					dtstart, created, "blatest", lastmod, "at Home", "wichtig",
					"fdsf", "fadf561", "fndfdsfds5113", dtend, duration,
					"ffffff", categories, "Hallo das ist ein comment", exdate,
					rdate))) {

			} else {
				fail("Event wurde nicht erstellt ein Fehler ist aufgetretten");
			}

			// Erstellen Event 3 tdend auserhalb vom Auswhal Datum
			dtstart = sdf.parse("2012-01-08 14:30");
			created = sdf.parse("2012-01-06 12:35");
			dtend = sdf.parse("2012-02-08 13:15");
			if (event.createEvents(new Event(testUser, dtstamp, "98765",
					dtstart, created, "blatest", lastmod, "at Home", "wichtig",
					"fdsf", "fadf561", "fndfdsfds5113", dtend, duration,
					"ffffff", categories, "Hallo das ist ein comment", exdate,
					rdate))) {

			} else {
				fail("Event wurde nicht erstellt ein Fehler ist aufgetretten");
			}
			// Erstellen Event 4 tstart und tend Auserhalb vom auswahl Datum
			dtstart = sdf.parse("2012-02-08 14:30");
			created = sdf.parse("2012-02-06 12:35");
			dtend = sdf.parse("2012-02-12 13:15");
			if (event.createEvents(new Event(testUser, dtstamp, "54321",
					dtstart, created, "blatest", lastmod, "at Home", "wichtig",
					"fdsf", "fadf561", "fndfdsfds5113", dtend, duration,
					"ffffff", categories, "Hallo das ist ein comment", exdate,
					rdate))) {

			} else {
				fail("Event wurde nicht erstellt ein Fehler ist aufgetretten");
			}

		} else {
			fail("E-Mailadresse oder Passwort sind falsch");
		}
	}

	@BeforeClass
	public static void prepairClass() {

		// TestDB loeschen und erstellen
		try {
			manager.executeQuery(dropDB);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			manager.executeQuery(createDB);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// User und Event Tabelle erstellen
		user.createUserTable();
		event.createEventTable();
		// TestUser in DB anlegen
		testUser = user.createUser(new User("zeller@yahoo.de", "Paull",
				"Sulzer", "test"));
		// Events in DB anlegen
		try {
			createEvents();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public static void cleanUpClass() {

		try {
			manager.executeQuery(dropDB);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testReturnUser() {

		if (user.checkUser("zeller@yahoo.de", "test")) {
			User testUser = user.returnUser("zeller@yahoo.de");
			assertNotNull(testUser);
		} else {
			fail("Email-Adresse oder Passwort sind falsch! "
					+ "Bitte ueberpruefen");
		}

	}

	public void testGetEventBetweenDates() throws ParseException {

		User testUser = user.returnUser("zeller@yahoo.de");
		if (null != testUser) {
			SimpleDateFormat sdf = new SimpleDateFormat();
			sdf.applyPattern("yyyy-MM-dd HH:mm");

			Date from = sdf.parse("2012-01-05 15:31");
			Date til = sdf.parse("2012-02-01 14:30");

			List<Event> eventList = event.getEventBetweenDates(testUser, from,
					til);
			assertNotNull(eventList);

		} else {
			fail("Die Emailadresse ist nicht vorhanden");
		}
	}

	@Test
	public void testUpdateUser() throws ParseException {

		User testUser = user.returnUser("zeller@yahoo.de");
		assertNotNull("User existiert nicht", testUser);

		List<Event> eventList = event.getEventsFromUser(testUser);
		Event testEvent = eventList.get(0);
		assertEquals(eventList.get(0), testEvent);
		assertNotNull("Es ist kein Event vorhanden", testEvent);

		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern("yyyy-MM-dd HH:mm");

		Date dtstart = sdf.parse("2012-07-11 15:30");
		Date dtend = sdf.parse("2012-08-15 14:00");
		testEvent.setDtstart(dtstart);
		testEvent.setDtend(dtend);

		event.updateEvent(testEvent);

		List<Event> eventListNeu = event.getEventsFromUser(testUser);
		assertNotSame(eventList.get(0), eventListNeu.get(0));

	}

	@Test
	public void testCheckEvent() {
		User testUser = user.returnUser("zeller@yahoo.de");
		assertNotNull("User existiert nicht", testUser);

		List<Event> eventList = event.getEventsFromUser(testUser);
		Event testEvent = eventList.get(0);
		Event newEvent = null;
		assertEquals(eventList.get(0), testEvent);
		assertNotNull("Es ist kein Event vorhanden", testEvent);

		testEvent.setUid("56789");
		testEvent.setColor("blue");
		newEvent = event.checkEvent(testEvent);

		assertEquals(newEvent.getUid(), testEvent.getUid());
		testEvent.setUid("56972");
		testEvent.setColor("red");
		newEvent = event.checkEvent(testEvent);

		assertEquals(newEvent.getUid(), testEvent.getUid());

		try {
			manager.executeQuery(dropDB);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
