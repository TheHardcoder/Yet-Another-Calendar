package de.yetanothercalendar.model.view;

import de.yetanothercalendar.model.calendar.Year;

public class YearView extends CalenderView {

	public YearView(Year pYear) {
		super(pYear, "resources/calender_yearview.xsl");
		// Datei drucken, noch mit Literal. Noch überarbeiten
		printXml(dXml, "resources/calender_yearview.xml");
	}
}
