package de.yetanothercalendar.model.impl;

import java.text.DateFormatSymbols;
import java.util.Locale;

import de.yetanothercalendar.model.Calendar;
import de.yetanothercalendar.model.calendar.Month;
import de.yetanothercalendar.model.calendar.Week;
import de.yetanothercalendar.model.calendar.Year;
import de.yetanothercalendar.model.dao.EventDAO;
import de.yetanothercalendar.model.dao.impl.EventDAOImpl;
import de.yetanothercalendar.model.database.User;
import de.yetanothercalendar.model.database.helper.DatabaseConnectionManager;

public class CalendarImpl implements Calendar {

	private static final Locale locale = Locale.GERMANY;
	private static final DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(
			locale);

	/** Gerade eingeloggter Benutzer aus der Session */
	private User user;
	private EventDAO eventDAO;

	public CalendarImpl(User user) {
		this.user = user;
		eventDAO = new EventDAOImpl(new DatabaseConnectionManager());
	}

	public Year getEntriesByWeek(int year, int month, int week) {
		// TODO Auto-generated method stub
		return null;
	}

	public Year getEntriesByMonth(int year, int month) {
		// TODO Auto-generated method stub
		return null;
	}

	public Year getEntriesByYear(int year) {
		Year result = new Year(year);
		java.util.Calendar calendar = java.util.Calendar.getInstance(locale);
		calendar.set(java.util.Calendar.YEAR, year);
		int maximumMonthCount = calendar.getMaximum(java.util.Calendar.MONTH);
		for (int monthindex = 0; monthindex < maximumMonthCount; monthindex++) {
			Month month = new Month(dateFormatSymbols.getMonths()[monthindex],
					monthindex);
			calendar.set(java.util.Calendar.MONTH, monthindex);
			// Die Zahl der Wochen in einem Monat ist immer 4
			int weekcount = 4;
			for (int weekindex = 0; weekindex < weekcount; weekindex++) {
				Week week = new Week(weekindex);
				calendar.set(java.util.Calendar.WEEK_OF_MONTH, weekindex);
				int maximum = calendar
						.getMaximum(java.util.Calendar.DAY_OF_WEEK);
				String string = dateFormatSymbols.getWeekdays()[calendar
						.get(maximum)];
				System.out.println(string);
			}
			result.getMonths().add(month);
		}
		return null;
	}

	public User getUser() {
		return user;
	}

	public void getUser(User user) {
		this.user = user;
	}

	public EventDAO getEventDAO() {
		return eventDAO;
	}

	public void setEventDAO(EventDAO eventDAO) {
		this.eventDAO = eventDAO;
	}
}
