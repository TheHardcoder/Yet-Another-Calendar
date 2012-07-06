package de.yetanothercalendar.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import de.yetanothercalendar.model.calendar.CalendarEntry;
import de.yetanothercalendar.model.calendar.Day;
import de.yetanothercalendar.model.impl.CalendarViewCalculation;
import edu.emory.mathcs.backport.java.util.Collections;

public class ViewCalculationTest extends TestCase {

	public CalendarViewCalculation viewCalculation = new CalendarViewCalculation();

	@Test
	public void testSortCalendarEntryList() throws InterruptedException {
		Date dFirst = new Date();
		Thread.sleep(1000);
		List<CalendarEntry> lCalendarEntries = createEntryList();
		List<CalendarEntry> lCalendarEntriesSorted;
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
				dFirst, "Test", null, "", dFirst, dFirst);
		for (CalendarEntry calendarEntry : lCalendarEntriesSorted) {
			System.out.println(previousCalendarEntry.getStartTime().before(
					calendarEntry.getStartTime()));
			previousCalendarEntry = calendarEntry;
		}

	}

	@Test
	public void testRecursChecking() {
		System.out.println("Recurse Checking");
		List<CalendarEntry> lCalendarEntries = createEntryList();
		Day day = new Day(lCalendarEntries, "TestTag", 1);
		day = viewCalculation.analyseColumns(day);
		for (CalendarEntry calendarEntry : day.getCalendarEntries()) {
			assertNotNull(calendarEntry.getColumn());
			System.out.println(calendarEntry.getStartTime().toString()
					+ calendarEntry.getColumn());

		}
		assertNotNull(day.getColumnCount());
		// System.out.println(day.getColumnCount());
	}

	public List<CalendarEntry> createEntryList() {
		List<CalendarEntry> rlCalendarEntries = new ArrayList<CalendarEntry>();
		for (int i = 0; i < 30; i++) {
			Date dt = new Date();
			rlCalendarEntries.add(new CalendarEntry(i, "Test", "Test", "Test",
					dt, dt, dt, "Test", "Test", dt, dt, "Test", null, "", dt,
					dt));
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IllegalMonitorStateException e) {
				System.out.println("Ignore");
			}
		}
		return rlCalendarEntries;
	}
}
