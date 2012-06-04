package de.yetanothercalendar.model.iCal;

import java.awt.Event;
import java.util.ArrayList;
import java.util.List;

import de.yetanothercalendar.model.calendar.CalendarEntry;

public class ICalendarParser {

	private static final String EVENT_END = "END:VEVENT";
	private static final String EVENT_BEGIN = "BEGIN:VEVENT";

	public static List<String> getEvents(StringBuffer calendarString) {
		List<String> events = new ArrayList<String>();
		// Stash the timezone Information in the rubbish bin
		// maybe this Information will be stored in the database later on
		int firstEventPos = calendarString.indexOf(EVENT_BEGIN);
		calendarString.delete(0, firstEventPos);

		int eventBeginPos = calendarString.indexOf(EVENT_BEGIN);
		int eventEndPos = calendarString.indexOf(EVENT_END);

		String debug = calendarString.substring(0, EVENT_BEGIN.length());
		while ((eventBeginPos != -1) && (eventEndPos != -1)) {
			// Maybe replace by if calendarString.startsWith(EVENT_BEGIN),
			// because after an End must be a start --> faster? Problem with Space, Line Breaks --> this is safer ;-)
			
			events.add(calendarString.substring(eventBeginPos+EVENT_BEGIN.length(),
					eventEndPos));
			calendarString.delete(0,
					eventEndPos + EVENT_END.length());
			
			eventBeginPos = calendarString.indexOf(EVENT_BEGIN);
			eventEndPos = calendarString.indexOf(EVENT_END);
		}
		return events;
	}
}


