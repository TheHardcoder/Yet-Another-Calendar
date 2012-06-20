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
	 * Eyports an Eventlist into an ICal-String
	 * 
	 * @param events
	 *            Events to be exported
	 * @return String representation of the ICal-File
	 */
	public List<String> exportToIcal(List<Event> events) {
		List<String> iCal = getCalendarInitText();
		for (Event event : events) {
			iCal.addAll(convertEventToString(event));

		}
		iCal.add("END:VCALENDAR");
		return iCal;
	}

	private List<String> convertEventToString(Event e) {
		List<String> eventString = new ArrayList<String>();
		eventString.add("BEGIN:VEVENT");
		if (converDateToICSDate(e.getCreated()) != "") {
			eventString.add("CREATED:" + converDateToICSDate(e.getCreated()));
		}
		if (converDateToICSDate(e.getLastmod()) != "") {
			eventString.add("LAST-MODIFIED:"
					+ converDateToICSDate(e.getLastmod()));
		}
		if (converDateToICSDate(e.getDtstamp()) != "") {
			eventString.add("DTSTAMP:" + converDateToICSDate(e.getDtstamp()));
		}
		if (e.getUid() != "") {
			eventString.add("UID:" + e.getUid());
		}
		if (e.getSummary() != "") {
			eventString.add("SUMMARY" + e.getSummary());
		}

		if (e.getRecurid() != "") {
			eventString.add("SUMMARY" + e.getRecurid());
		}

		if (converDateToICSDate(e.getDtstart()) != "") {
			eventString.add("DTSTART:" + converDateToICSDate(e.getDtstart()));
		}
		if (converDateToICSDate(e.getDtend()) != "") {
			eventString.add("DTEND:" + converDateToICSDate(e.getDtend()));
		}
		if (e.getDescription() != "") {
			eventString.add("DESCRIPTION" + e.getDescription());
		}
		if (e.getCategories() != null) {
			String categories = "CATEGORIES:";
			for (String string : e.getCategories()) {
				categories += string;
			}
			eventString.add("CATEGORIES" + categories);
		}
		if (e.getComment() != "") {
			eventString.add("COMMENT:" + e.getComment());
		}
		if (Long.toString(e.getDuration()) != "") {
			eventString.add("DURATION:" + Long.toString(e.getDuration()));
		}
		if (converDateToICSDate(e.getExdate()) != "") {
			eventString.add("EXDATE:" + converDateToICSDate(e.getExdate()));
		}

		if (converDateToICSDate(e.getCreated()) != "") {
			eventString.add("RDATE:" + converDateToICSDate(e.getCreated()));
		}

		if (e.getRrule() != "") {
			eventString.add("RRULE:" + e.getRrule());
		}
		if (e.getPriority() != "") {
			eventString.add("PRIORITY:" + e.getPriority());
		}
		if (e.getLocation() != "") {
			eventString.add("LOCATION:" + e.getLocation());
		}

		return new ArrayList<String>();
	}

	private String converDateToICSDate(Date d) {
		if (d != null) {
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(d);
			String month = getTwoCharacterString(cal.get(Calendar.MONTH) + 1);
			String day = getTwoCharacterString(cal.get(Calendar.DAY_OF_MONTH) + 1);
			String hour = getTwoCharacterString(cal.get(Calendar.HOUR_OF_DAY));
			String minutes = getTwoCharacterString(cal.get(Calendar.MINUTE));
			String seconds = getTwoCharacterString(cal.get(Calendar.SECOND));
			return cal.get(Calendar.YEAR) + month + day + "T" + hour + minutes
					+ seconds + "Z";
		} else {
			return "";
		}
	}

	private String getTwoCharacterString(int i) {
		if (i < 9) {
			return "0" + i;
		} else {
			return Integer.toString(i);
		}
	}

	private List<String> getCalendarInitText() {
		List<String> initText = new ArrayList<String>();
		initText.add("BEGIN:VCALENDAR");
		initText.add("VERSION:2.0");
		initText.add("PRODID:-//YET-ANOTHER-CALENDAR " + VERSION + "//DE");
		initText.add("BEGIN:VCALENDAR");
		initText.add("BEGIN:VCALENDAR");
		initText.add("BEGIN:VCALENDAR");
		initText.add("BEGIN:VCALENDAR");
		initText.add("BEGIN:VCALENDAR");
		initText.add("BEGIN:VCALENDAR");
		initText.add("BEGIN:VCALENDAR");

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
