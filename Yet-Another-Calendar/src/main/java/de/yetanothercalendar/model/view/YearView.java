package de.yetanothercalendar.model.view;

import de.yetanothercalendar.model.calendar.Year;

public class YearView extends CalendarView {

	public YearView(Year pYear) {
		super(pYear, "Resources/calendar_yearview.xsl");
	}
}
