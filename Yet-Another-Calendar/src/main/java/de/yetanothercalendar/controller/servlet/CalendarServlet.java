package de.yetanothercalendar.controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.yetanothercalendar.model.Calendar;
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
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			Calendar calendar = new CalendarImpl(user);
			// CAlendastruktur holen und in xml parsen
			String selectedYear = (String) request.getAttribute("selectedyear");
			String selectedMonth = (String) request
					.getAttribute("selectedmonth");
			String selectedWeek = (String) request.getAttribute("selectedweek");
			if (selectedYear == null) {
				throw new RuntimeException(
						"Das gegebene Jahr darf nicht null sein, und muss als parameter 'selectedyear' im GET request uebergeben werden");
			}
			// Year view
			if (selectedMonth == null) {
				// GET years
				int year = Integer.parseInt(selectedYear);
				Year entriesByYear = calendar.getEntriesByYear(year);
				YearView yearview = new YearView(entriesByYear);
				resp.getWriter().write(yearview.getXMLString());
			} else if (selectedWeek == null) {
				int year = Integer.parseInt(selectedYear);
				int month = Integer.parseInt(selectedMonth);
				Year entriesByMonth = calendar.getEntriesByMonth(year, month);
				MonthView monthview = new MonthView(entriesByMonth);
				resp.getWriter().write(monthview.getXMLString());
			} else {
				int year = Integer.parseInt(selectedYear);
				int month = Integer.parseInt(selectedMonth);
				int week = Integer.parseInt(selectedWeek);
				Year entriesByYear = calendar.getEntriesByWeek(year, month,
						week);
				MonthView monthview = new MonthView(entriesByYear);
				resp.getWriter().write(monthview.getXMLString());
			}
		} else {
			// TODO Ben redirection for corect site in frontend
			throw new RuntimeException("No user is logged in!");
		}

	}
}
