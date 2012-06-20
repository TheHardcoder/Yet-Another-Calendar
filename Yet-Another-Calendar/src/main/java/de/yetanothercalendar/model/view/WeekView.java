package de.yetanothercalendar.model.view;

import de.yetanothercalendar.model.calendar.Year;

public class WeekView extends CalendarView {

	public WeekView(Year pYear) {
		super(pYear, "Resources/calendar_weekview.xsl");
	}

}
