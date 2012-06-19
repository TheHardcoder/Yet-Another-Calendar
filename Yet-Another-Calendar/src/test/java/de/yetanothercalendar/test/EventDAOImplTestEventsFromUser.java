package de.yetanothercalendar.test;
import org.junit.Test;
import java.text.SimpleDateFormat;
import java.util.List;

import junit.framework.TestCase;

import de.yetanothercalendar.model.dao.impl.EventDAOImpl;
import de.yetanothercalendar.model.database.Event;
import de.yetanothercalendar.model.database.User;
import de.yetanothercalendar.model.database.helper.DatabaseConnectionManager;


public class EventDAOImplTestEventsFromUser extends TestCase {
	
	
	@Test
	 public void testgetEventsFromUser(){
		 try {
			 
				
				EventDAOImpl event = new EventDAOImpl(
						new DatabaseConnectionManager("admin", "admin",
								"localhost", 3306, "yetanothercalendar"));
				User testUser = new User("zeller@yahoo.de", "Paull", "Sulzer",
						"test");

				SimpleDateFormat sdf = new SimpleDateFormat();
				sdf.applyPattern("yyyy-MM-dd HH:mm");

				List<Event> events = event.getEventsFromUser(testUser);
				System.out.println("Unkonvertierte Ausgabe:");
				for (int i = 0; i < events.size(); i++) {

					System.out.println(events.get(i).getDtstart() + " bis: "
							+ events.get(i).getDtend());
				}

				System.out.println("Jetzt konvertierte Ausgabe:");
				for (int i = 0; i < events.size(); i++) {

					System.out.println(sdf.format(events.get(i).getDtstart())
							+ " bis: " + sdf.format(events.get(i).getDtend()));
				}

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				fail("Ein Fehler ist aufgetreten ");
			}
	 }

}
