package de.yetanothercalendar.controller.impl;

import de.yetanothercalendar.controller.Calendar;
import de.yetanothercalendar.db.dao.EventDAO;
import de.yetanothercalendar.db.helper.DatabaseConnectionManager;
import de.yetanothercalendar.model.calendar.Year;
import de.yetanothercalendar.model.database.User;

public class CalendarImpl implements Calendar {

	/** Gerade eingeloggter Benutzer aus der Session */
	private User user;
	private EventDAO eventDAO;

	public CalendarImpl(User user) {
		this.user = user;
		eventDAO = new EventDAO(new DatabaseConnectionManager());
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
