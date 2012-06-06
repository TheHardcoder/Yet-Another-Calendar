package de.yetanothercalendar.test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import junit.framework.TestCase;

import org.junit.Test;

import de.yetanothercalendar.model.calendar.CalendarEntry;
import de.yetanothercalendar.model.database.Event;
import de.yetanothercalendar.model.impl.EventToCalendarEntryWrapper;

public class CalendarEventToEntryWrapperTest extends TestCase {
	@Test
	public void testWrapper() {
		// Mehrere events erstellen, die ueber mehrere Tage verlaufen
		// Event startet am 06.06.12 um 12:00 Uhr
		Calendar start = new GregorianCalendar(Locale.GERMANY);
		start.set(2012, 6, 6, 12, 00);
		// Event endet einen Tag Später am 06.06.12 um 12:00 Uhr
		Calendar end = new GregorianCalendar(Locale.GERMANY);
		end.set(2012, 6, 8, 12, 00);
		System.out.println("Event vom:\n" + start.getTime().toString()
				+ " bis \n" + end.getTime().toString());
		Event event = new Event(new Long(1), null, null, null, start.getTime(),
				null, null, null, null, null, null, null, null, end.getTime(),
				0, null, null, null, null, null);
		EventToCalendarEntryWrapper wrapper = new EventToCalendarEntryWrapper(
				Locale.GERMANY);
		List<CalendarEntry> wrapEventToCalendar = wrapper
				.wrapEventToCalendar(event);
		for (CalendarEntry calendarEntry : wrapEventToCalendar) {
			System.out.println(calendarEntry.getStartTime() + " bis "
					+ calendarEntry.getEndTime());
		}
		System.out.println(wrapEventToCalendar.size());
	}
}
