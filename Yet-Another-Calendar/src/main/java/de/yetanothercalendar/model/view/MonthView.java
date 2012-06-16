package de.yetanothercalendar.model.view;

import de.yetanothercalendar.model.calendar.Year;

public class MonthView extends CalenderView {

	public MonthView(Year pYear) {
		super(pYear, "resources/calender_monthview.xsl");
		// Datei drucken, noch mit Literal. Noch überarbeiten
		printXml(dXml, "resources/calender_monthview.xml");
	}
}
