package de.yetanothercalendar.model.view;

import de.yetanothercalendar.model.calendar.Year;

public class WeekView extends CalendarView {

	public WeekView(Year pYear, String pSelectedMonth, String pSelectedWeek,
			String pSelectedDay) {
		super(pYear, pSelectedMonth, pSelectedWeek, pSelectedDay,
				"Resources/calendar_weekview.xsl");
	}

}
