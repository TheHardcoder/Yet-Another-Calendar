package de.yetanothercalendar.model.view;

import de.yetanothercalendar.model.calendar.Year;

public class WeekView extends CalenderView {

	public WeekView(Year pYear) {
		super(pYear, "resources/calender_weekview.xsl");
		// Datei drucken, noch mit Literal. Noch überarbeiten
		printXml(dXml, "resources/calender_weekview.xml");
	}

}
