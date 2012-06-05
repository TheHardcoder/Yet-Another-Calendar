package de.yetanothercalendar.model.impl;

import de.yetanothercalendar.model.Calendar;
import de.yetanothercalendar.model.calendar.Year;
import de.yetanothercalendar.model.dao.impl.EventDAOImpl;
import de.yetanothercalendar.model.database.User;
import de.yetanothercalendar.model.database.helper.DatabaseConnectionManager;

public class CalendarImpl implements Calendar {

	/** Gerade eingeloggter Benutzer aus der Session */
	private User user;
	private EventDAOImpl eventDAO;

	public CalendarImpl(User user) {
		this.user = user;
		eventDAO = new EventDAOImpl(new DatabaseConnectionManager());
	}

	public User getUser() {
		return user;
	}

	public void getUser(User user) {
		this.user = user;
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
		// TODO Auto-generated method stub
		return null;
	}
}
