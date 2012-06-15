package de.yetanothercalendar.model.view;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;

import de.yetanothercalendar.model.calendar.CalendarEntry;
import de.yetanothercalendar.model.calendar.Day;

public class DayViewHelper extends ViewHelper {

	private Day day;
	private Element eDayElement;

	public DayViewHelper(Day pDay) {
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
		List<EntryViewHelper> listOfEntryViews = new ArrayList<EntryViewHelper>();
		List<CalendarEntry> listofEntries = day.getCalendarEntries();
		for (CalendarEntry itEntry : listofEntries) {
			listOfEntryViews.add(new EntryViewHelper(itEntry));
		}
		for (EntryViewHelper eView : listOfEntryViews) {
			listOfEntryElements.add(eView.getEntryElement());
		}
		return listOfEntryElements;
	}

}
