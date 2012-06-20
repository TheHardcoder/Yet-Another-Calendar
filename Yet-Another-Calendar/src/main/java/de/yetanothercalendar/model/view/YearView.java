package de.yetanothercalendar.model.view;

import de.yetanothercalendar.model.calendar.Year;

public class YearView extends CalendarView {

	public YearView(Year pYear, int pSelectedMonth, int pSelectedWeek,
			int pSelectedDay) {
		super(pYear, pSelectedMonth, pSelectedWeek, pSelectedDay,
				"Resources/calendar_yearview.xsl");
	}
}
