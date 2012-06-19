package de.yetanothercalendar.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import de.yetanothercalendar.model.dao.impl.EventDAOImpl;
import de.yetanothercalendar.model.dao.impl.UserDAOImpl;
import de.yetanothercalendar.model.database.Event;
import de.yetanothercalendar.model.database.User;
import de.yetanothercalendar.model.database.helper.DatabaseConnectionManager;

import junit.framework.TestCase;



public class EventDAOImplTestBetweenDates extends TestCase {
	
		
	
	@Test 
	 public void testGetEventsBetweenD(){
	  
		 try{
				
				EventDAOImpl event = new EventDAOImpl(
						new DatabaseConnectionManager("admin", "admin",
								"localhost", 3306, "yetanothercalendar"));
				SimpleDateFormat sdf = new SimpleDateFormat();
			    sdf.applyPattern( "yyyy-MM-dd HH:mm" );
			    
				Date from = sdf.parse("2012-01-02 15:30");
				
				Date til = sdf.parse("2012-01-08 15:00");
								
				
				User testUser = new User("zeller@yahoo.de", "Paull", "Sulzer",
								"test");
				
				List<Event> events = event.getEventBetweenDates(testUser, from, til);
				
				for(int i=0; i<events.size(); i++){
					
					System.out.println(events.get(i).getDtstart() + "bis: " + events.get(i).getDtend());
			}
				
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
	 }
	
	 

}
