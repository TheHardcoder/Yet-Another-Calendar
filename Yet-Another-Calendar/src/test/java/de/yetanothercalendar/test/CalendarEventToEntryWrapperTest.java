package de.yetanothercalendar.test;

import java.util.ArrayList;
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
		EventToCalendarEntryWrapper wrapper = new EventToCalendarEntryWrapper(
				Locale.GERMANY);
		// Event startet am 06.06.12 um 12:00 Uhr
		Calendar start = new GregorianCalendar(Locale.GERMANY);
		start.set(2011, 11, 30, 12, 00);
		// Event endet einen Tag Später am 06.06.12 um 12:00 Uhr
		Calendar end = new GregorianCalendar(Locale.GERMANY);
		end.set(2012, 0, 1, 12, 00);
		Event event = new Event(new Long(1), null, null, null, start.getTime(),
				null, null, null, null, null, null, null, null, end.getTime(),
				0, null, null, null, null, null);
		List<CalendarEntry> wrapEventToCalendar = wrapper
				.wrapEventToCalendar(event);
		for (CalendarEntry calendarEntry : wrapEventToCalendar) {
			System.out.println(calendarEntry.getStartTime() + " bis "
					+ calendarEntry.getEndTime());
		}
		System.out.println(wrapEventToCalendar.size());
//		assertEquals(368, wrapEventToCalendar.size());
//		assertEquals(wrapEventToCalendar.get(0), start);
//		assertEquals(wrapEventToCalendar.get(wrapEventToCalendar.size() - 1),
//				end);
	}

	@Test
	public void testWrapperSpeedTest() {
		final int entryNumber = 10000;
		List<Event> eventList = new ArrayList<Event>();
		for (int i = 0; i < entryNumber; i++) {
			eventList.add(createDummyEventShort());
		}
		EventToCalendarEntryWrapper wrapper = new EventToCalendarEntryWrapper(
				Locale.GERMANY);
		int fullWrapping = 0;
		for (Event event : eventList) {
			long start = System.currentTimeMillis();
			wrapper.wrapEventToCalendar(event);
			long stop = System.currentTimeMillis();
			fullWrapping += stop - start;
		}
		System.out.println("Das umwandeln von " + entryNumber + " events hat "
				+ fullWrapping + " ms gedauert.");
	}

	private Event createDummyEventShort() {
		// Event startet am 06.06.12 um 12:00 Uhr
		Calendar start = new GregorianCalendar(Locale.GERMANY);
		start.set(2012, 6, 6, 12, 00);
		// Event endet einen Tag Später am 06.06.12 um 12:00 Uhr
		Calendar end = new GregorianCalendar(Locale.GERMANY);
		end.set(2012, 6, 15, 12, 00);
		return new Event(new Long(1), null, null, null, start.getTime(), null,
				null, null, null, null, null, null, null, end.getTime(), 0,
				null, null, null, null, null);
	}
}
