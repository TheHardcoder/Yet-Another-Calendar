package de.yetanothercalendar.model.view;

import de.yetanothercalendar.model.calendar.Year;

public class YearView extends CalendarView {

	public YearView(Year pYear, String pSelectedYear, String pSelectedMonth,
			String pSelectedWeek) {
		super(pYear, pSelectedYear, pSelectedMonth, pSelectedWeek,
				"Resources/calendar_yearview.xsl");
	}
}
