package de.yetanothercalendar.controller.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.yetanothercalendar.model.dao.impl.EventDAOImpl;
import de.yetanothercalendar.model.dao.impl.UserDAOImpl;
import de.yetanothercalendar.model.database.Event;
import de.yetanothercalendar.model.database.User;
import de.yetanothercalendar.model.database.helper.DatabaseConnectionManager;

public class DummyDataCreatorServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		DatabaseConnectionManager manager = new DatabaseConnectionManager("",
				"", "localhost", 3306, "yetanothercalendar");
		UserDAOImpl daoUser = new UserDAOImpl(manager);
		daoUser.createUserTable();
		EventDAOImpl daoEvent = new EventDAOImpl(manager);
		daoEvent.createEventTable();
		User user = new User("test@test.de", "Vorname", "Nachname", "HashMe");
		daoUser.createUser(user);
		req.getSession().setAttribute("user", user);

		Calendar calendarCreated = new GregorianCalendar(Locale.GERMANY);
		calendarCreated.set(2012, 0, 3, 10, 0);
		Calendar calendar = new GregorianCalendar(Locale.GERMANY);
		calendar.set(2012, 0, 1, 12, 0);
		Calendar calendar2 = new GregorianCalendar(Locale.GERMANY);
		calendar2.set(2012, 0, 1, 14, 0);
		daoEvent.createEvents(createEvent(user, calendarCreated, calendar,
				calendar2, "Meeting", "DHBW"));
	}

	private Event createEvent(User user, Calendar created, Calendar start,
			Calendar end, String desc, String location) {
		return new Event(new Long((int) (Math.random() * 100000)), user,
				new Date(), "uuid", start.getTime(), created.getTime(), desc,
				new Date(), location, "very high", "what a great summary",
				"recurrid", "rrule", end.getTime(), 0, "#fff",
				new ArrayList<String>(), "comment", new Date(), new Date());
	}
}
