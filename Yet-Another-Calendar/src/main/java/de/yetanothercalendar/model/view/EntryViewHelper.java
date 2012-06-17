package de.yetanothercalendar.model.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jdom.Element;

import de.yetanothercalendar.model.calendar.CalendarEntry;

public class EntryViewHelper extends ViewHelper {

	private CalendarEntry entry;
	private Element eEntryElement;

	public EntryViewHelper(CalendarEntry pEntry) {
		this.entry = pEntry;
		eEntryElement = new Element("entry");
		eEntryElement.setAttribute("id", String.valueOf(entry.getId()));
		eEntryElement.setAttribute("priority", entry.getPriority());
		eEntryElement.setAttribute("color", entry.getColorString());
		eEntryElement.addContent(this.getEntryAttributes());
	}

	public Element getEntryElement() {
		return eEntryElement;
	}

	private List<Element> getEntryAttributes() {
		List<Element> listOfEntryAttributes = new ArrayList<Element>();
		listOfEntryAttributes.add(new Element("summery").setText(entry
				.getSummary()));

		listOfEntryAttributes.add(createTimeElement("starttime",
				entry.getStartTime(), false));
		listOfEntryAttributes.add(createTimeElement("endtime",
				entry.getStartTime(), false));
		listOfEntryAttributes.add(createTimeElement("duration",
				entry.getStartTime(), false));

		listOfEntryAttributes.add(new Element("location").setText(entry
				.getLocation()));
		listOfEntryAttributes.add(new Element("description").setText(entry
				.getDescription()));

		listOfEntryAttributes.add(createTimeElement("created",
				entry.getStartTime(), true));
		listOfEntryAttributes.add(createTimeElement("modified",
				entry.getStartTime(), true));

		listOfEntryAttributes.add(new Element("comment").setText(entry
				.getComment()));
		listOfEntryAttributes.add(createCategoriesElement());

		return listOfEntryAttributes;

	}

	private Element createCategoriesElement() {
		Element rElement = new Element("categories");
		List<String> listOfCatagories = entry.getCategory();
		for (String itCategory : listOfCatagories) {
			rElement.addContent(new Element("category").setText(itCategory));
		}
		return rElement;
	}

	private Element createTimeElement(String pName, Date pDate,
			boolean pExtended) {
		Element rElement = new Element(pName);
		// TODO Zeiten noch Berechnung noch implementieren (Werteinsetzen)
		if (pExtended) {
			rElement.setAttribute("day", "Wert");
			rElement.setAttribute("month", "Wert");
			rElement.setAttribute("year", "Wert");
		}
		rElement.setAttribute("hours", "Wert");
		rElement.setAttribute("minutes", "Wert");

		return rElement;
	}
}
