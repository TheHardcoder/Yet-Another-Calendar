package de.yetanothercalendar.model.view.helper;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;

import de.yetanothercalendar.model.calendar.CalendarEntry;
import de.yetanothercalendar.model.calendar.Day;

public class DayViewHelper extends ViewHelper {

	private Day day;

	public DayViewHelper(Day pDay) {
		this.day = pDay;
		element = new Element("day");
		element.setAttribute("name", day.getName());
		element.setAttribute("number", String.valueOf(day.getNumber()));
		// TODO Noch mit Ben abklären ob die Attribute hier in Ordnung sind
		element.setAttribute("columns", String.valueOf(day.getColumnCount()));
		element.addContent(this.getEntryElements());
	}

	private List<Element> getEntryElements() {
		List<Element> listOfEntryElements = new ArrayList<Element>();
		List<EntryViewHelper> listOfEntryViews = new ArrayList<EntryViewHelper>();
		List<CalendarEntry> listofEntries = day.getCalendarEntries();
		for (CalendarEntry itEntry : listofEntries) {
			listOfEntryViews.add(new EntryViewHelper(itEntry));
		}
		for (EntryViewHelper eView : listOfEntryViews) {
			listOfEntryElements.add(eView.getElement());
		}
		return listOfEntryElements;
	}

}
