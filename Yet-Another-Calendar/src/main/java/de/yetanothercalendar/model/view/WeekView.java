package de.yetanothercalendar.model.view;

import de.yetanothercalendar.model.calendar.Year;

public class WeekView extends CalendarView {

	public WeekView(Year pYear) {
		super(pYear, "Resources/calendar_weekview.xsl");
		// Datei drucken, noch mit Literal. Noch �berarbeiten
		printXml(dXml, "resources/calender_weekview.xml");
	}

}
