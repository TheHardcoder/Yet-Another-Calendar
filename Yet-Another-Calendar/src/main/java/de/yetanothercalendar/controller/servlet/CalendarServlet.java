package de.yetanothercalendar.controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.yetanothercalendar.model.Calendar;
import de.yetanothercalendar.model.calendar.CalendarEntry;
import de.yetanothercalendar.model.calendar.Day;
import de.yetanothercalendar.model.calendar.Month;
import de.yetanothercalendar.model.calendar.Week;
import de.yetanothercalendar.model.calendar.Year;
import de.yetanothercalendar.model.database.User;
import de.yetanothercalendar.model.impl.CalendarImpl;
import de.yetanothercalendar.model.view.MonthView;
import de.yetanothercalendar.model.view.YearView;

/**
 * Controller für die Kalender-sichten.
 */
public class CalendarServlet extends HttpServlet {

	public CalendarServlet() {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setCharacterEncoding("utf-8");
		
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			Calendar calendar = new CalendarImpl(user);
			String viewType = (String) request.getParameter("view");
			String selectedYear = (String) request.getParameter("selectedyear");
			String selectedMonth = (String) request
					.getParameter("selectedmonth");
			String selectedWeek = (String) request.getParameter("selectedweek");
			if (viewType != null) {
				if (viewType.toLowerCase().equals("yearview")) {
					if (selectedYear != null) {
						// GET years
						int year = Integer.parseInt(selectedYear);
						Year entriesByYear = calendar.getEntriesByYear(year);
						YearView yearview = new YearView(entriesByYear);
						String result = yearview.getXMLString();
						resp.getWriter().write(result);
						printYear(entriesByYear);
					} else {
						throw new RuntimeException(
								"Invalid parameters for view " + viewType);
					}
				} else if (viewType.toLowerCase().equals("monthview")) {
					if (selectedYear != null & selectedMonth != null) {
						int year = Integer.parseInt(selectedYear);
						int month = Integer.parseInt(selectedMonth);
						Year entriesByMonth = calendar.getEntriesByMonth(year,
								month);
						MonthView monthview = new MonthView(entriesByMonth);
						resp.getWriter().write(monthview.getXMLString());
					} else {
						throw new RuntimeException(
								"Invalid parameters for view " + viewType);
					}
				} else if (viewType.toLowerCase().equals("weekview")) {
					if (selectedYear != null & selectedWeek != null) {
						int year = Integer.parseInt(selectedYear);
						int week = Integer.parseInt(selectedWeek);
						Year entriesByYear = calendar.getEntriesByWeek(year,
								week);
						MonthView monthview = new MonthView(entriesByYear);
						resp.getWriter().write(monthview.getXMLString());
					} else {
						throw new RuntimeException(
								"Invalid parameters for view " + viewType);
					}
				} else {
					throw new RuntimeException(
							"Invalid parameters for viewtype parameter");
				}
			} else {
				throw new RuntimeException("Invalid View name");
			}
		} else {
			// TODO Ben redirection for corect site in frontend
			throw new RuntimeException("No user is logged in!");
		}
	}

	private static void printYear(Year year) {
		System.out.println("Returned year: ");
		int termincount = 0;
		System.out.println(year.toString());
		for (Month m : year.getMonths()) {
			System.out.println("\t" + m.toString());
			for (Week w : m.getWeeks()) {
				System.out.println("\t\t" + w.toString());
				for (Day day : w.getDays()) {
					System.out.println("\t\t\t" + day.toString());
					for (CalendarEntry e : day.getCalendarEntries()) {
						termincount++;
						System.out.println("\t\t\t\t" + e.toString());
					}
				}
			}
		}
		System.out.println("Count der Termine " + termincount);
		System.out.println("End of year\n\n");
	}
}
