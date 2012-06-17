package de.yetanothercalendar.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
	public void TestgetEventBetweenDates() {
		try{
			
			EventDAOImpl event = new EventDAOImpl(
					new DatabaseConnectionManager("admin", "admin",
							"localhost", 3306, "yetanothercalendar"));
			SimpleDateFormat sdf = new SimpleDateFormat();
		    sdf.applyPattern( "yyyy-MM-DD hh:mm" );
		    
			Date from = sdf.parse("2012-01-01 00:00");
			Date til = sdf.parse("2012-02-01 01:00");
			
			User testUser = new User("zeller@yahoo.de", "Paull", "Sulzer",
							"test");
			
			List<Event> events = event.getEventBetweenDates(testUser, from, til);
			
/*			for(int i=0; i<events.size(); i++){
				
				System.out.println(events.get(i).getDtstart() + "bis: " + events.get(i).getDtend());
		}*/
			
			
		}catch(Exception e){
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
			
			if(user.checkUser("zeller@yahoo.de", "test")){
				
			
			System.out.println("Email-Adresse und Passwort sind stimmen");
			User testUser = user.returnUser("zeller@yahoo.de");

			SimpleDateFormat sdf = new SimpleDateFormat();
		    sdf.applyPattern( "yyyy-MM-DD hh:mm:ss" );
		   // DateFormat df = DateFormat.getDateTimeInstance();
		    
			Date dtstamp =  Calendar.getInstance().getTime();
			System.out.println("Datum: " + dtstamp);		
			//sdf.parse("2012-02-02 12:30:00");
			
			
			System.out.println(dtstamp);
			Date dtstart = sdf.parse("2012-01-04 10:30:00");
			Date created = sdf.parse("2012-01-02 12:35:00");
			Date lastmod = sdf.parse("2012-01-03 10:45:00");
			Date dtend = sdf.parse("2012-04-03 10:00:00");
			long duration = 135;
			List<String> categories = new ArrayList<String>();
			categories.add("fhleflöajfö");
			Date exdate = sdf.parse("2012-01-06 10:45:00");
			Date rdate = sdf.parse("2012-01-06 14:30:00");

				if(event.createEvents(new Event(testUser, dtstamp, "12340", dtstart,
					created, "blatest", lastmod, "at Home", "wichtig", "fdsf",
					"fadf561", "fnölfvnöae56156", dtend, duration, "ffffff",
					categories, "Hallo das ist ein comment", exdate, rdate))){
					System.out.println("Event wurde Erforgreich erstellt");
				
				}
				else{
					System.out.println("Event wurde nicht erstellt ein Fehler ist aufgetretten");
				}
			
			}
			else{
				System.out.println("E-Mailadresse oder Passwort sind falsch");
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	


}
