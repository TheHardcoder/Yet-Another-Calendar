package de.yetanothercalendar.controller.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.fortuna.ical4j.model.Property;

import de.yetanothercalendar.model.Calendar;
import de.yetanothercalendar.model.calendar.CalendarEntry;
import de.yetanothercalendar.model.calendar.Day;
import de.yetanothercalendar.model.calendar.Month;
import de.yetanothercalendar.model.calendar.Week;
import de.yetanothercalendar.model.calendar.Year;
import de.yetanothercalendar.model.dao.EventDAO;
import de.yetanothercalendar.model.dao.UserDAO;
import de.yetanothercalendar.model.dao.impl.EventDAOImpl;
import de.yetanothercalendar.model.dao.impl.UserDAOImpl;
import de.yetanothercalendar.model.database.Event;
import de.yetanothercalendar.model.database.User;
import de.yetanothercalendar.model.database.helper.DatabaseConnectionManager;
import de.yetanothercalendar.model.impl.CalendarImpl;
import de.yetanothercalendar.model.view.MonthView;
import de.yetanothercalendar.model.view.WeekView;
import de.yetanothercalendar.model.view.YearView;

/**
 * Controller Klasse für Termin im-/export.
 */
public class TerminServlet extends HttpServlet {

	private EventDAO dao;

	public TerminServlet() {
		dao = new EventDAOImpl(new DatabaseConnectionManager());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String action = (String) req.getParameter("action");
		HttpSession session = req.getSession();

		Event event = new Event();
		try {
			long id = Long.parseLong(req.getParameter("id"));
			event.setId(id);
			// TODO Wenn die id = 0 ist soll eine neue ID zum erstellen des
			// Termins generiert werden.
		} catch (Exception e) {
			throw new RuntimeException("Error parsing Id at Termin Servlet! ("
					+ req.getParameter("id") + ")");
		}

		// create User form given Data
		User user = (User) session.getAttribute("user");

		String description = req.getParameter("description");
		String location = req.getParameter("location");
		String priority = req.getParameter("priority");
		String summary = req.getParameter("summary");
		String recurid = req.getParameter("recurid");
		String rrule = req.getParameter("rrule");
		if(null != rrule) {
			rrule = rrule.replace("%3B", ";");
			rrule = rrule.replace("%3D", "=");
			rrule = rrule.replace("%2C", ",");
		}

		Date lastmod = getDateParameterValue("lastmod", req);
		Date dtstart = getDateParameterValue("dtstart", req);
		if (dtstart == null) {
			throw new RuntimeException("Error parsing Dtstart at TerminServlet");
		}

		Date dtend = getDateParameterValue("dtend", req);
		if (dtend == null) {
			// Duration should only be set if Dtend is not set
			try {
				long duration = Long.parseLong(req.getParameter("duration"));
				event.setDuration(duration);
			} catch (Exception e) {
				throw new RuntimeException(
						"Error at TerminServlet: Neither Dtend nor Duration is set");
			}
		}

		Date created = getDateParameterValue("created", req);
		Date dtstamp = getDateParameterValue("dtstamp", req);
		
		
		String color = req.getParameter("color");
		String comment = req.getParameter("comment");
		// Shouldn't be set because recurrent events get wrapped to
		// non-recurrent multiple single events
		String rdate = req.getParameter("rdate");

		List<String> categoriesList = new ArrayList<String>();

		String categoryString = req.getParameter("categories");
		if (categoryString != null) {
			String[] categories = categoryString.split(",");

			for (String category : categories) {
				categoriesList.add(category);
			}
		}

		// gelesene Eventeigenschaften setzen
		event.setColor(color);
		event.setCategories(categoriesList);
		event.setComment(comment);
		event.setCreated(created);
		event.setDescription(description);
		event.setDtend(dtend);
		event.setDtstamp(dtstamp);
		event.setDtstart(dtstart);
		//is empty, because Frontend doesn't get information about recurrent events
		event.setExdate(new ArrayList<Date>());
		event.setLastmod(lastmod);
		event.setLocation(location);
		event.setPriority(priority);
		event.setRdate(rdate);
		event.setRecurid(recurid);
		event.setRrule(rrule);
		event.setSummary(summary);
		// FIXME uid is not the user id!
		event.setUid(user.getId().toString());
		event.setUser(user);

		// action parameter (update oder create) lesen
		if (action.toLowerCase().equals("save") && event.getId() == 0) {
			dao.createEvents(event);
		} else if (action.toLowerCase().equals("save")) {
			dao.updateEvent(event);
		} else if (action.toLowerCase().equals("delete")) {
			dao.deleteEvent(event);
		} else {
			throw new RuntimeException(
					"Invalid action Parameter in TerminServlet");
		}
		java.util.Calendar c = GregorianCalendar.getInstance();
		c.setTime(dtstart);
		resp.sendRedirect("calendarservlet?view=" + req.getParameter("view")
				+ "&selectedyear=" + c.get(java.util.Calendar.YEAR)
				+ "&selectedmonth=" + (c.get(java.util.Calendar.MONTH) +1)
				+ "&selectedweek=" + c.get(java.util.Calendar.WEEK_OF_YEAR)
				+ "&selectedday=" + c.get(java.util.Calendar.DAY_OF_MONTH));
	}

	/**
	 * returns a Date representation of the Parameters name+"Year"
	 * name+"Month"....
	 * 
	 * @param name
	 *            Name of the Date property e.g. dtstart
	 * @param req
	 *            req HttpServletRequest
	 * @return Date representation of the parameter Values null if one of the
	 *         Parameters is not set
	 */
	private Date getDateParameterValue(String name, HttpServletRequest req) {
		String year = req.getParameter(name + "Year");
		String month = req.getParameter(name + "Month");
		String day = req.getParameter(name + "Day");
		String hour = req.getParameter(name + "Hour");
		String minute = req.getParameter(name + "Minute");
		String second = req.getParameter(name + "Second");
		try {
			GregorianCalendar cal = new GregorianCalendar(
					Integer.parseInt(year), Integer.parseInt(month) - 1,
					Integer.parseInt(day), Integer.parseInt(hour),
					Integer.parseInt(minute), Integer.parseInt(second));
			return cal.getTime();
		} catch (Exception e) {
			return null;
		}
	}
}
