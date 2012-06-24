package de.yetanothercalendar.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.junit.Test;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.DateTime;
import de.yetanothercalendar.model.calendar.CalendarEntry;
import de.yetanothercalendar.model.database.Event;
import de.yetanothercalendar.model.database.User;
import de.yetanothercalendar.model.iCal.ICalendarImporter;
import de.yetanothercalendar.model.impl.RecurrentEventToCalendarEntryWrapper;
import junit.framework.TestCase;

public class RecurrentCalendarEventToEntryWrapperTest extends TestCase {

	@Test
	public void testWrapper() {
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
			RecurrentEventToCalendarEntryWrapper wrapper = new RecurrentEventToCalendarEntryWrapper(
					Locale.GERMANY);

			GregorianCalendar startCal = new GregorianCalendar(1990, 05, 04,
					13, 00, 00);
			GregorianCalendar endCal = new GregorianCalendar(2013, 06, 04, 13,
					00, 00);

			DateTime start = new DateTime(startCal.getTime());
			DateTime end = new DateTime(endCal.getTime());

			List<CalendarEntry> calendarEntries = new ArrayList<CalendarEntry>();

			for (Event event : events) {
				calendarEntries.addAll(wrapper.wrapEventToCalendar(event,
						start, end));
			}

			System.out.println("==============================");
			System.out.println("RecurrentCalendarEventToEntryWrapperTest");
			System.out.println("==============================");
			for (Iterator<CalendarEntry> iterator = calendarEntries.iterator(); iterator
					.hasNext();) {
				CalendarEntry calendarEntry = (CalendarEntry) iterator.next();
				System.out.println("Start: "
						+ calendarEntry.getStartTime().toString() + " Ende: "
						+ calendarEntry.getEndTime().toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.fail("Error parsing sample .ics File");
		}
	}
}
