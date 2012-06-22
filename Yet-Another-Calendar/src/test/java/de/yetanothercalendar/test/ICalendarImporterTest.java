package de.yetanothercalendar.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;
import net.fortuna.ical4j.model.Calendar;

import org.junit.Test;

import de.yetanothercalendar.model.database.Event;
import de.yetanothercalendar.model.database.User;
import de.yetanothercalendar.model.iCal.ICalendarImporter;

public class ICalendarImporterTest extends TestCase {

	@Test
	public void testImportToIcal4J() {
		// TODO: more complex Test with _all_ properties set
		File f = new File("resources/recurrentEvents.ics");
		InputStream in = null;
		try {
			in = new FileInputStream(f);
			ICalendarImporter importer = new ICalendarImporter();
			Calendar test = importer.importToIcal4J(in);
			List<Event> events = new ArrayList<Event>();
			User user = new User("test@test.de", "test", "test", "123456");
			events = importer.parseIcal4JToEventList(test, user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail("Error parsing sample .ics File");
		}
	}
}
