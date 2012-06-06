package de.yetanothercalendar.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import net.fortuna.ical4j.model.Calendar;

import org.junit.Test;

import de.yetanothercalendar.model.database.Event;
import de.yetanothercalendar.model.database.User;
import de.yetanothercalendar.model.iCal.ICalendarImporter;

public class ICalendarImporterTest extends TestCase {

	 @Test
	 public void TesParseIcal4JToEventList(){
			File f = new File("resources/simple.ics");
			InputStream in = null;
			try {
				in = new FileInputStream(f);
				Calendar test = ICalendarImporter.importToIcal4J(in);
				List<Event> events = new ArrayList();
				User user = new User("test@test.de", "test", "test", "123456");
				events = ICalendarImporter.parseIcal4JToEventList(test, user);
				} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.fail("Error parsing sample .ics File");
			}
		}

	@Test
	public void testImportToIcal4J() {
		File f = new File("resources/simple.ics");
		InputStream in = null;
		try {
			in = new FileInputStream(f);
			Calendar test = ICalendarImporter.importToIcal4J(in);
			List<Event> events = new ArrayList();
			User user = new User("test@test.de", "test", "test", "123456");
			events = ICalendarImporter.parseIcal4JToEventList(test, user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.fail("Error parsing sample .ics File");
		}
	}
}
