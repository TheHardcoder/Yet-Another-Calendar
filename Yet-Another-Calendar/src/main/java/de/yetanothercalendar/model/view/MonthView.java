package de.yetanothercalendar.model.view;

import de.yetanothercalendar.model.calendar.Year;

public class MonthView extends CalendarView {

	public MonthView(Year pYear, int pSelectedMonth, int pSelectedWeek,
			int pSelectedDay) {
		super(pYear, pSelectedMonth, pSelectedWeek, pSelectedDay,
				"Resources/calendar_monthview.xsl");
	}
}
