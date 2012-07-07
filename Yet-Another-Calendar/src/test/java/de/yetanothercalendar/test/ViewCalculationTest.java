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
		List<CalendarEntry> lCalendarEntries = createEntryList1();
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
	public void testColumnInitialization() {
		// First Test
		System.out.println("30 Spalten (Diagonal versetzt)");
		List<CalendarEntry> lCalendarEntries = createEntryList1();
		Day day = new Day(lCalendarEntries, "TestTag", 0);
		day = viewCalculation.initializeColumns(day);
		lCalendarEntries = day.getCalendarEntries();
		for (CalendarEntry calendarEntry : lCalendarEntries) {
			System.out.println(calendarEntry.getStartTime().toString() + " "
					+ calendarEntry.getEndTime() + " "
					+ calendarEntry.getColumn());
			assertEquals(30, day.getColumnCount());
		}
		// Second Test
		System.out.println("Nur eine Spalte (Hintereinander)");
		lCalendarEntries = createEntryList2();
		day = new Day(lCalendarEntries, "TestTag", 0);
		day = viewCalculation.initializeColumns(day);
		lCalendarEntries = day.getCalendarEntries();
		for (CalendarEntry calendarEntry : lCalendarEntries) {
			System.out.println(calendarEntry.getStartTime().toString() + " "
					+ calendarEntry.getEndTime() + " "
					+ calendarEntry.getColumn());
			assertEquals(1, day.getColumnCount());
		}
		// Third Test
		System.out.println("30 Spalten (Spitz)");
		lCalendarEntries = createEntryList3();
		day = new Day(lCalendarEntries, "TestTag", 0);
		day = viewCalculation.initializeColumns(day);
		lCalendarEntries = day.getCalendarEntries();
		for (CalendarEntry calendarEntry : lCalendarEntries) {
			System.out.println(calendarEntry.getStartTime().toString() + " "
					+ calendarEntry.getEndTime() + " "
					+ calendarEntry.getColumn());
			assertEquals(30, day.getColumnCount());
		}
	}

	@Test
	public void testDateList() {
		System.out.println("Date Checking");
		List<CalendarEntry> lCalendarEntries = createEntryList1();
		for (CalendarEntry calendarEntry : lCalendarEntries) {
			System.out.println(calendarEntry.getStartTime().toString() + " "
					+ calendarEntry.getEndTime() + " "
					+ calendarEntry.getColumn());
		}
	}

	// Diagonal verschoben, mximale Spaltenanzahl
	public List<CalendarEntry> createEntryList1() {
		List<CalendarEntry> rlCalendarEntries = new ArrayList<CalendarEntry>();
		Date dt = new Date();
		Date[] dStart = new Date[30];
		Date[] dEnd = new Date[30];
		// Versetzte Liste
		for (int i = 0; i < dStart.length; i++) {
			dStart[i] = new Date();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IllegalMonitorStateException e) {
				System.out.println("Ignore");
			}
		}
		for (int i = 0; i < dEnd.length; i++) {
			dEnd[i] = new Date();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IllegalMonitorStateException e) {
				System.out.println("Ignore");
			}
		}

		for (int i = 0; i < 30; i++) {
			rlCalendarEntries.add(new CalendarEntry(i, "Test", "Test", "Test",
					dStart[i], dEnd[i], dt, "Test", "Test", dt, dt, "Test",
					null, "", dt, dt));
		}
		return rlCalendarEntries;
	}

	// Ein Eintrag nach dem Anderen
	public List<CalendarEntry> createEntryList2() {
		List<CalendarEntry> rlCalendarEntries = new ArrayList<CalendarEntry>();
		Date dt = new Date();
		Date[] dStart = new Date[30];
		Date[] dEnd = new Date[30];
		// Versetzte Liste
		for (int i = 0; i < dStart.length; i++) {
			try {
				dStart[i] = new Date();
				Thread.sleep(100);
				dEnd[i] = new Date();
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IllegalMonitorStateException e) {
				System.out.println("Ignore");
			}
		}

		for (int i = 0; i < 30; i++) {
			rlCalendarEntries.add(new CalendarEntry(i, "Test", "Test", "Test",
					dStart[i], dEnd[i], dt, "Test", "Test", dt, dt, "Test",
					null, "", dt, dt));
		}
		return rlCalendarEntries;
	}

	// Stack in spitz zulaufend ( Pyramide )
	public List<CalendarEntry> createEntryList3() {
		List<CalendarEntry> rlCalendarEntries = new ArrayList<CalendarEntry>();
		Date dt = new Date();
		Date[] dStart = new Date[30];
		// Versetzte Liste
		for (int i = 0; i < dStart.length; i++) {
			try {
				dStart[i] = new Date();
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IllegalMonitorStateException e) {
				System.out.println("Ignore");
			}
		}
		Date dEnd = new Date();

		for (int i = 0; i < 30; i++) {
			rlCalendarEntries.add(new CalendarEntry(i, "Test", "Test", "Test",
					dStart[i], dEnd, dt, "Test", "Test", dt, dt, "Test", null,
					"", dt, dt));
		}
		return rlCalendarEntries;
	}
}
