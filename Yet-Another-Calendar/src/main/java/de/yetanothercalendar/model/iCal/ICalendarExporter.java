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
		if (!(Event.converDateToICSDate(e.getCreated()).equals(""))) {
			eventString.add("CREATED:" + Event.converDateToICSDate(e.getCreated()));
		}
		if (!(Event.converDateToICSDate(e.getLastmod()).equals(""))) {
			eventString.add("LAST-MODIFIED:"
					+ Event.converDateToICSDate(e.getLastmod()));
		}
		if (!(Event.converDateToICSDate(e.getDtstamp()).equals(""))) {
			eventString.add("DTSTAMP:" + Event.converDateToICSDate(e.getDtstamp()));
		}
		if (neitherNullnorEmpty(e.getUid())) {
			eventString.add("UID:" + e.getUid());
		}
		if (neitherNullnorEmpty(e.getSummary())) {
			eventString.add("SUMMARY:" + e.getSummary());
		}

		if (neitherNullnorEmpty(e.getRecurid())){
			eventString.add("RECURID:" + e.getRecurid());
		}

		if (!(Event.converDateToICSDate(e.getDtstart()).equals(""))) {
			eventString.add("DTSTART:" + Event.converDateToICSDate(e.getDtstart()));
		}
		if (!(Event.converDateToICSDate(e.getDtend()).equals(""))) {
			eventString.add("DTEND:" + Event.converDateToICSDate(e.getDtend()));
		}
		if (neitherNullnorEmpty(e.getDescription())) {
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
		if (neitherNullnorEmpty(e.getComment())) {
			eventString.add("COMMENT:" + e.getComment());
		}
		if (e.getDuration() > 0) {
			eventString.add("DURATION:PT" + Long.toString(e.getDuration())
					+ "M");
		}
		
		if (neitherNullnorEmpty(e.getExdateString())) {
			eventString.add(e.getExdateString());
		}
	
		String lineseparator = System.getProperty("line.separator");
		if (!((e.getRdate().equals("")) || (e.getRdate().equals(lineseparator)))) {
			eventString.add("RDATE:" + e.getRdate());
		}

		if (neitherNullnorEmpty(e.getRrule())) {

			eventString.add("RRULE:" + e.getRrule());
		}
		if (neitherNullnorEmpty(e.getPriority())) {
			eventString.add("PRIORITY:" + e.getPriority());
		}
		if (neitherNullnorEmpty(e.getLocation())) {
			eventString.add("LOCATION:" + e.getLocation());
		}

		eventString.add("END:VEVENT");

		return eventString;
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
	
	private boolean neitherNullnorEmpty(String str){
		if ((str == null)||(str.equals(""))){
			return false;
		} else {
			return true;
		}
	}
}
