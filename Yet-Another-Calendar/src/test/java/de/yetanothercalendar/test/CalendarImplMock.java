package de.yetanothercalendar.test;

import java.util.HashMap;
import java.util.List;

import de.yetanothercalendar.model.calendar.CalendarEntry;
import de.yetanothercalendar.model.calendar.Month;
import de.yetanothercalendar.model.calendar.Week;
import de.yetanothercalendar.model.database.User;
import de.yetanothercalendar.model.impl.CalendarImpl;

public class CalendarImplMock extends CalendarImpl {

	protected CalendarImplMock(User user) {
		super(user);
	}

	protected List<Month> getMonthList(int year) {
		return super.getMonthList(year);
	}

	protected List<Week> getWeekListWithDays(int year, int month) {
		return super.getWeekListWithDays(year, month,
				new HashMap<java.util.Calendar, List<CalendarEntry>>());
	}
}
