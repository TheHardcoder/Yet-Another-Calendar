package de.yetanothercalendar.model.iCal;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import de.yetanothercalendar.model.database.Event;
import de.yetanothercalendar.model.database.User;

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
	
	public static List<Event> parseEvents(List<String> events) throws ParseException{
		List<Event> eventList = new ArrayList<Event>();
		
		int begin;
		final int DATE_LENGTH = 15;
		Event tmpEvent;
		Long id;				//What to set here?
		User user;				//Need this from somewhere --> Import Parameter?
		Date dtstamp;
		String uid;
		Date dtstart;
		Date created;
		String description;
		Date lastmod;
		String location;
		String priority;
		String summary;
		String recurid;
		String rrule;
		Date dtend;
		long duration;
		String color;
		List<String> categories;
		String comment;
		Date exdate;
		Date rdate;
		
		/**
		 * 	private Long id;					//What to set here?
		 *  private User user;					//Need this from somewhere --> Import Parameter?
		 * 	private Date dtstamp;
		 * 	private String uid;
		 * 	private Date dtstart;
		 * 	private Date created;
		 * 	private String description;
		 * 	private Date lastmod;
		 * 	private String location;
		 * 	private String priority;
		 * 	private String summary;
		 * 	private String recurid;
		 * 	private String rrule;
		 * 	private Date dtend;
		 * 	private long duration;
		 * 	private String color;				//Need this as import parameter
		 * 	private List<String> categories;
		 * 	private String comment;
		 * 	private Date exdate;
		 * 	private Date rdate;
		 */
		
		ListIterator<String> it = events.listIterator(events.size());
		while(it.hasNext()){
			dtstamp = parseIcsDate(getEventItem("DTSTAMP:", DATE_LENGTH, it.next()));
		}
		
		return eventList;
	}
	
	public static Date parseIcsDate (String dateString) throws ParseException{
		dateString = dateString.replace('T', ' ');
		DateFormat df = new SimpleDateFormat ("yyyyMMdd HHmmss");
		Date date = df.parse(dateString);
		System.out.println(date.toString());
		return date;
	}
	public static String getEventItem (String name, int itemLength, String event){
		String eventItem;
		int itemStart;
		itemStart = event.indexOf(name);
		eventItem = event.substring(itemStart+name.length()+1, itemStart+name.length()+1+itemLength);
		return eventItem;
	}
}


