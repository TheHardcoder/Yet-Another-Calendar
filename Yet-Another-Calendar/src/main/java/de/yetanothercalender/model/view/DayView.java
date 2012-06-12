package de.yetanothercalender.model.view;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;

import de.yetanothercalendar.model.calendar.CalendarEntry;
import de.yetanothercalendar.model.calendar.Day;

public class DayView extends ParentView {

	private Day day;
	private Element eDayElement;

	public DayView(Day pDay) {
		this.day = pDay;
		eDayElement = new Element("day");
		eDayElement.setAttribute("name", day.getName());
		eDayElement.setAttribute("number", String.valueOf(day.getNumber()));
		eDayElement.addContent(this.getEntryElements());
	}

	public Element getWeekElement() {
		return eDayElement;
	}

	private List<Element> getEntryElements() {
		List<Element> listOfEntryElements = new ArrayList<Element>();
		List<EntryView> listOfEntryViews = new ArrayList<EntryView>();
		List<CalendarEntry> listofEntries = day.getCalendarEntries();
		for (CalendarEntry itEntry : listofEntries) {
			listOfEntryViews.add(new EntryView(itEntry));
		}
		for (EntryView eView : listOfEntryViews) {
			listOfEntryElements.add(eView.getEntryElement());
		}
		return listOfEntryElements;
	}

}
