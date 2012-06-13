package de.yetanothercalendar.test;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import de.yetanothercalendar.model.calendar.CalendarEntry;
import de.yetanothercalendar.model.calendar.Day;
import de.yetanothercalendar.model.calendar.Month;
import de.yetanothercalendar.model.calendar.Week;
import de.yetanothercalendar.model.calendar.Year;
import de.yetanothercalendar.model.dao.EventDAO;
import de.yetanothercalendar.model.database.User;

public class CalendarFullTest extends TestCase {

	@Test
	public void testFullCalendarYear() {
		// TODO gesamtes jahr mit gewrappten Entrys testen
		CalendarImplMock calendar = new CalendarImplMock(new User(
				"test@iteabag.org", "test", "lastnametest", "i am awesome"));
		EventDAO eventDAO = new EventDAOMockFull();
		calendar.setEventDAO(eventDAO);
		Year entriesByYear = calendar.getEntriesByYear(2012);
		printYear(entriesByYear);
	}

	private void printYear(Year year) {
		List<Month> months = year.getMonths();
		for (Month month : months) {
			System.out.println("Month: " + month.getName() + " - "
					+ month.getNumber());
			List<Week> weeks = month.getWeeks();
			for (Week week : weeks) {
				System.out.println("\tWeek: " + week.getNumber());
				List<Day> days = week.getDays();
				for (Day day : days) {
					System.out.println("\t\tDay: " + day.getName() + " - "
							+ day.getNumber());
					List<CalendarEntry> calendarEntries = day
							.getCalendarEntries();
					for (CalendarEntry calendarEntry : calendarEntries) {
						System.out
								.println("\t\t\tCalendarEntry: "
										+ calendarEntry.getId()
										+ " - "
										+ calendarEntry.getStartTime()
												.toString() + " - "
										+ calendarEntry.getEndTime().toString());
					}
				}
			}

		}
	}
}
