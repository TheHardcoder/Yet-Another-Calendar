package de.yetanothercalendar.model.view;

import de.yetanothercalendar.model.calendar.Year;

public class MonthView extends CalendarView {

	public MonthView(Year pYear, String pSelectedYear, String pSelectedMonth,
			String pSelectedWeek) {
		super(pYear, pSelectedYear, pSelectedMonth, pSelectedWeek,
				"Resources/calendar_monthview.xsl");
	}
}
