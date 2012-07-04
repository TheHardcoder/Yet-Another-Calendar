package de.yetanothercalendar.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import de.yetanothercalendar.model.calendar.CalendarEntry;
import de.yetanothercalendar.model.impl.CalendarViewCalculation;
import edu.emory.mathcs.backport.java.util.Collections;

public class SortCalenderEntryListTest extends TestCase {

	@Test
	public void testSortCalendarEntryList() throws InterruptedException {
		CalendarViewCalculation viewCalculation = new CalendarViewCalculation();
		List<CalendarEntry> lCalendarEntries = new ArrayList<CalendarEntry>();
		List<CalendarEntry> lCalendarEntriesSorted = new ArrayList<CalendarEntry>();
		Date dFirst = new Date();
		Thread.sleep(1000);
		for (int i = 0; i < 30; i++) {
			Date dt = new Date();
			lCalendarEntries.add(new CalendarEntry(i, "Test", "Test", "Test",
					dt, dt, dt, "Test", "Test", dt, dt, "Test", null));
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IllegalMonitorStateException e) {
				System.out.println("Ignore");
			}
		}
		Collections.shuffle(lCalendarEntries);
		System.out
				.println("Gemsicht------------------------------------------");
		for (int i = 0; i < lCalendarEntries.size(); i++) {
			System.out.println(lCalendarEntries.get(i).getId() + "  ---  "
					+ lCalendarEntries.get(i).getStartTime());
		}
		lCalendarEntriesSorted = viewCalculation
				.sortByStartTime(lCalendarEntries);
		System.out
				.println("Sortiert------------------------------------------");
		for (int i = 0; i < lCalendarEntries.size(); i++) {
			System.out.println(lCalendarEntriesSorted.get(i).getId()
					+ "  ---  " + lCalendarEntriesSorted.get(i).getStartTime());
		}
		CalendarEntry previousCalendarEntry = new CalendarEntry(42, "Test",
				"Test", "Test", dFirst, dFirst, dFirst, "Test", "Test", dFirst,
				dFirst, "Test", null);
		for (CalendarEntry calendarEntry : lCalendarEntriesSorted) {
			System.out.println(previousCalendarEntry.getStartTime().before(
					calendarEntry.getStartTime()));
			previousCalendarEntry = calendarEntry;
		}

	}
}
