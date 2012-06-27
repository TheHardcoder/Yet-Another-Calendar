package de.yetanothercalendar.model.view;

import de.yetanothercalendar.model.calendar.Year;

public class MonthView extends CalendarView {

	public MonthView(Year pYear, String pSelectedMonth, String pSelectedWeek,
			String pSelectedDay) {
		super(pYear, pSelectedMonth, pSelectedWeek, pSelectedDay,
				"Resources/calendar_monthview.xsl");
	}
}
