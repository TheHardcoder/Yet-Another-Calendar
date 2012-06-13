package de.yetanothercalendar.test;

import java.util.ArrayList;
import java.util.List;

import de.yetanothercalendar.model.calendar.Day;
import de.yetanothercalendar.model.calendar.Month;
import de.yetanothercalendar.model.calendar.Week;
import de.yetanothercalendar.model.calendar.Year;
import de.yetanothercalender.model.view.YearView;
import junit.framework.TestCase;

public class XmlTestCase extends TestCase {
	public void testXML() {
		Year year = new Year(2012);
		List<Month> months = new ArrayList<Month>();
		Month m1 = new Month("Januar", 1);
		months.add(m1);
		List<Week> weeks = new ArrayList<Week>();
		m1.setWeeks(weeks);
		Day day = new Day("Mittwoch", 1);
		List<Day> days = new ArrayList<Day>();
		days.add(day);
		Week week = new Week(1);
		week.getDays().add(day);
		weeks.add(week);
		YearView view = new YearView(year);
	}
}
