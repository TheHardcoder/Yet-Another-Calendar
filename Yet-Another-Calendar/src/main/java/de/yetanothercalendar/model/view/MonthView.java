package de.yetanothercalendar.model.view;

import de.yetanothercalendar.model.calendar.Year;

public class MonthView extends CalendarView {

	public MonthView(Year pYear) {
		super(pYear, "Resources/calendar_monthview.xsl");
		// Datei drucken, noch mit Literal. Noch �berarbeiten
		printXml(dXml, "resources/calender_monthview.xml");
	}
}
