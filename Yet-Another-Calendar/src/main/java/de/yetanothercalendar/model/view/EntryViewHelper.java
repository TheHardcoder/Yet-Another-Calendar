package de.yetanothercalendar.model.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.jdom.Element;

import de.yetanothercalendar.model.calendar.CalendarEntry;

public class EntryViewHelper extends ViewHelper {

	private CalendarEntry entry;

	public EntryViewHelper(CalendarEntry pEntry) {
		this.entry = pEntry;
		element = new Element("entry");
		element.setAttribute("id", String.valueOf(entry.getId()));
		element.setAttribute("priority", entry.getPriority().trim());
		element.setAttribute("color", entry.getColorString());
		element.addContent(this.getEntryAttributes());
	}

	private List<Element> getEntryAttributes() {
		List<Element> listOfEntryAttributes = new ArrayList<Element>();
		listOfEntryAttributes.add(new Element("summary").setText(entry
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
		// TODO set local germany locale
		Calendar calendar = Calendar.getInstance(Locale.GERMANY);
		// TODO Zeiten noch Berechnung noch implementieren (Werteinsetzen)
		if (pExtended) {
			rElement.setAttribute("day", calendar.get(Calendar.DAY_OF_MONTH)
					+ "");
			rElement.setAttribute("month", calendar.get(Calendar.MONTH) + "");
			rElement.setAttribute("year", calendar.get(Calendar.YEAR) + "");
		}
		rElement.setAttribute("hours", calendar.get(Calendar.HOUR_OF_DAY) + "");
		rElement.setAttribute("minutes", calendar.get(Calendar.MINUTE) + "");

		return rElement;
	}
}
