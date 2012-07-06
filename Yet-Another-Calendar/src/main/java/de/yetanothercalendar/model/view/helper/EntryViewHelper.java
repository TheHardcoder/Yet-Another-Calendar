package de.yetanothercalendar.model.view.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jdom.Element;

import de.yetanothercalendar.model.calendar.CalendarEntry;

public class EntryViewHelper extends ViewHelper {

	private CalendarEntry entry;

	public EntryViewHelper(CalendarEntry pEntry) {
		this.entry = pEntry;
		element = new Element("entry");
		element.setAttribute("id", String.valueOf(entry.getId()));
		// remove all whitespaces
		String priority = entry.getPriority();
		priority = priority.replaceAll("\\s", "");
		element.setAttribute("priority", priority);
		element.setAttribute("color", entry.getColorString());
		// TODO Noch mit Ben abklären, wo er es haben will
		element.setAttribute("column", String.valueOf(entry.getColumn()));
		element.addContent(this.getEntryAttributes());
	}

	private List<Element> getEntryAttributes() {
		List<Element> listOfEntryAttributes = new ArrayList<Element>();
		listOfEntryAttributes.add(new Element("summary").setText(entry
				.getSummary()));

		listOfEntryAttributes.add(createTimeElement("starttime",
				entry.getStartTime(), false));
		listOfEntryAttributes.add(createTimeElement("endtime",
				entry.getEndTime(), false));
		listOfEntryAttributes.add(createTimeElement("duration",
				entry.getDurationTime(), false));

		listOfEntryAttributes.add(new Element("location").setText(entry
				.getLocation()));
		listOfEntryAttributes.add(new Element("description").setText(entry
				.getDescription()));

		listOfEntryAttributes.add(createTimeElement("created",
				entry.getCreated(), true));
		listOfEntryAttributes.add(createTimeElement("modified",
				entry.getModified(), true));

		listOfEntryAttributes.add(new Element("comment").setText(entry
				.getComment()));
		listOfEntryAttributes.add(createCategoriesElement());
		listOfEntryAttributes
				.add(new Element("rRule").setText(entry.getrRule()));
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
		String[] sDateStrings = prepareDateString(pDate.toString());
		if (pExtended) {
			rElement.setAttribute("day", sDateStrings[0]);
			rElement.setAttribute("month", sDateStrings[1]);
			rElement.setAttribute("year", sDateStrings[2]);
		}
		rElement.setAttribute("hours", sDateStrings[3]);
		rElement.setAttribute("minutes", sDateStrings[4]);

		return rElement;
	}

	/**
	 * Konvertiert eine Zeichenkette, die wie folgt Datumsangaben beinhaltet: <br>
	 * dow mon dd hh:mm:ss zzz yyyy <br>
	 * <br>
	 * where <br>
	 * dow is the day of the week (Sun, Mon, Tue, Wed, Thu, Fri, Sat).<br>
	 * mon is the month (Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov,
	 * Dec).<br>
	 * dd is the day of the month (01 through 31), as two decimal digits.<br>
	 * hh is the hour of the day (00 through 23), as two decimal digits.<br>
	 * mm is the minute within the hour (00 through 59), as two decimal digits.<br>
	 * ss is the second within the minute (00 through 61, as two decimal digits.<br>
	 * zzz is the time zone (and may reflect daylight saving time). Standard
	 * time zone abbreviations include those recognized by the method parse. If
	 * time zone information is not available, then zzz is empty - that is, it
	 * consists of no characters at all.<br>
	 * yyyy is the year, as four decimal digits.<br>
	 * <br>
	 * Das zurückgegebene Array hat den folgenden Aufbau:<br>
	 * Array[0] steht der Tag<br>
	 * Array[1] steht der Monat<br>
	 * Array[2] steht das Jahr<br>
	 * Array[3] stehen die Stunden<br>
	 * Array[4] stehen die Minuten<br>
	 * Array[5] steht der übergebene Zeichenkette <br>
	 * 
	 * @param dateString
	 *            - Eine Zeichenkette, die bspl. durch den Aufruf toString auf
	 *            ein Date-Objekt zurückgegeben wird
	 * @return - Die zersplittete Zeichenkette in einem Array vom Typ String
	 */
	private String[] prepareDateString(String dateString) {
		String[] dateStrings = dateString.split(" ");
		String[] timeStrings = dateStrings[3].split(":");
		dateStrings[0] = dateStrings[2]; // Tage
		// Konvertierung von Monatsnamen zu der dazugehörigen Monatszahl
		dateStrings[1] = MonthNumbers.valueOf(dateStrings[1]).monthNumber;
		dateStrings[2] = dateStrings[5]; // Jahr
		dateStrings[3] = timeStrings[0]; // Stunden
		dateStrings[4] = timeStrings[1]; // Minuten
		dateStrings[5] = dateString; // ursprünglicher String - not used
		return dateStrings;
	}
}
