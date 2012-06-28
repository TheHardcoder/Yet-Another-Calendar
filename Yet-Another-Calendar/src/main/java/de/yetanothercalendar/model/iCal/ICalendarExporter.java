package de.yetanothercalendar.model.iCal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import de.yetanothercalendar.model.database.Event;

public class ICalendarExporter {

	private final String VERSION = "0.5 Beta";

	public ICalendarExporter() {

	}

	/**
	 * Exports an Eventlist into an ICal-String
	 * 
	 * @param events
	 *            Events to be exported
	 * @return String representation of the ICal-File
	 */
	public List<String> exportToIcal(List<Event> events) {
		if (events != null) {
			List<String> iCal = getCalendarInitText();
			for (Event event : events) {
				iCal.addAll(convertEventToString(event));

			}
			iCal.add("END:VCALENDAR");
			return iCal;
		}
		return null;
	}

	/**
	 * Converts an Event to an Event-String (iCal Syntax)
	 * 
	 * @param e
	 *            Event e
	 * @return String representation of the Event
	 */
	private List<String> convertEventToString(Event e) {
		List<String> eventString = new ArrayList<String>();
		eventString.add("BEGIN:VEVENT");
		if (!(converDateToICSDate(e.getCreated()).equals(""))) {
			eventString.add("CREATED:" + converDateToICSDate(e.getCreated()));
		}
		if (!(converDateToICSDate(e.getLastmod()).equals(""))) {
			eventString.add("LAST-MODIFIED:"
					+ converDateToICSDate(e.getLastmod()));
		}
		if (!(converDateToICSDate(e.getDtstamp()).equals(""))) {
			eventString.add("DTSTAMP:" + converDateToICSDate(e.getDtstamp()));
		}
		if (!(e.getUid().equals(""))) {
			eventString.add("UID:" + e.getUid());
		}
		if (!(e.getSummary().equals(""))) {
			eventString.add("SUMMARY:" + e.getSummary());
		}

		if (!(e.getRecurid().equals(""))) {
			eventString.add("RECURID:" + e.getRecurid());
		}

		if (!(converDateToICSDate(e.getDtstart()).equals(""))) {
			eventString.add("DTSTART:" + converDateToICSDate(e.getDtstart()));
		}
		if (!(converDateToICSDate(e.getDtend()).equals(""))) {
			eventString.add("DTEND:" + converDateToICSDate(e.getDtend()));
		}
		if (!(e.getDescription().equals(""))) {
			eventString.add("DESCRIPTION:" + e.getDescription());
		}
		if (e.getCategories() != null) {
			StringBuffer categories = new StringBuffer("");
			for (String string : e.getCategories()) {
				if (!(string.equals(""))) {
					categories.append(string + ",");
				}
			}
			// delete the last ,
			int lastChar = categories.length() - 1;
			if (lastChar > 0) {
				categories.deleteCharAt(lastChar);
			}

			if (!(categories.equals(""))) {
				eventString.add("CATEGORIES:" + categories);
			}
		}
		if (!(e.getComment().equals(""))) {
			eventString.add("COMMENT:" + e.getComment());
		}
		if (e.getDuration() > 0) {
			eventString.add("DURATION:PT" + Long.toString(e.getDuration())
					+ "M");
		}
		if (!(converDateToICSDate(e.getExdate()).equals(""))) {
			eventString.add("EXDATE:" + converDateToICSDate(e.getExdate()));
		}
		String lineseparator = System.getProperty("line.separator");
		if (!((e.getRdate().equals("")) || (e.getRdate().equals(lineseparator)))) {
			eventString.add("RDATE:" + e.getRdate());
		}

		if (!(e.getRrule().equals(""))) {
			eventString.add("RRULE:" + e.getRrule());
		}
		if (!(e.getPriority().equals(""))) {
			eventString.add("PRIORITY:" + e.getPriority());
		}
		if (!(e.getLocation().equals(""))) {
			eventString.add("LOCATION:" + e.getLocation());
		}

		eventString.add("END:VEVENT");

		return eventString;
	}

	/**
	 * Converts a Date to an iCal Date Strin 20120626T140000
	 * 
	 * @param d
	 *            Date to convert
	 * @return String representation of the Date
	 */
	private String converDateToICSDate(Date d) {
		if (d != null) {
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(d);
			String month = getTwoCharacterString(cal.get(Calendar.MONTH) + 1);
			String day = getTwoCharacterString(cal.get(Calendar.DAY_OF_MONTH));
			String hour = getTwoCharacterString(cal.get(Calendar.HOUR_OF_DAY));
			String minutes = getTwoCharacterString(cal.get(Calendar.MINUTE));
			String seconds = getTwoCharacterString(cal.get(Calendar.SECOND));
			return cal.get(Calendar.YEAR) + month + day + "T" + hour + minutes
					+ seconds + "Z";
		} else {
			return "";
		}
	}

	/**
	 * Converts an Integer into at least two Chars
	 * 
	 * @param i
	 *            Integer to convert
	 * @return String representation of the Integer (with at least two Chars)
	 */
	private String getTwoCharacterString(int i) {
		if (i < 9) {
			return "0" + i;
		} else {
			return Integer.toString(i);
		}
	}

	/**
	 * Returns the Calendar init Text
	 * 
	 * @return init Text like BEGIN:VCALENDAR
	 */
	private List<String> getCalendarInitText() {
		List<String> initText = new ArrayList<String>();
		initText.add("BEGIN:VCALENDAR");
		initText.add("VERSION:2.0");
		initText.add("PRODID:-//YET-ANOTHER-CALENDAR " + VERSION + "//DE");

		return initText;

		/*
		 * TODO: maybe add Timezone Information like: BEGIN:VTIMEZONE
		 * TZID:Europe/Berlin X-LIC-LOCATION:Europe/Berlin BEGIN:DAYLIGHT
		 * TZOFFSETFROM:+0100 TZOFFSETTO:+0200 TZNAME:CEST
		 * DTSTART:19700329T020000 RRULE:FREQ=YEARLY;BYDAY=-1SU;BYMONTH=3
		 * END:DAYLIGHT BEGIN:STANDARD TZOFFSETFROM:+0200 TZOFFSETTO:+0100
		 * TZNAME:CET DTSTART:19701025T030000
		 * RRULE:FREQ=YEARLY;BYDAY=-1SU;BYMONTH=10 END:STANDARD END:VTIMEZONE
		 * BEGIN:VTIMEZONE TZID:Pacific Standard Time BEGIN:STANDARD
		 * DTSTART:16011104T020000 RRULE:FREQ=YEARLY;BYDAY=1SU;BYMONTH=11
		 * TZOFFSETFROM:-0700 TZOFFSETTO:-0800 END:STANDARD BEGIN:DAYLIGHT
		 * DTSTART:16010311T020000 RRULE:FREQ=YEARLY;BYDAY=2SU;BYMONTH=3
		 * TZOFFSETFROM:-0800 TZOFFSETTO:-0700 END:DAYLIGHT END:VTIMEZONE
		 */

	}
}
