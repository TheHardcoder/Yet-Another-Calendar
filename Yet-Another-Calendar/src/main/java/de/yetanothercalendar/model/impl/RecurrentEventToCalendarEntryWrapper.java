package de.yetanothercalendar.model.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.yetanothercalendar.model.calendar.CalendarEntry;
import de.yetanothercalendar.model.database.Event;

public class RecurrentEventToCalendarEntryWrapper {
	
	private Locale locale;
	

	public RecurrentEventToCalendarEntryWrapper(Locale locale) {
		super();
		this.locale = locale;
	}


	public List<CalendarEntry> wrapEventToCalendar(Event event, Date begin, Date end) {
		// TODO Auto-generated method stub
		if (event.getRrule().equals("")) {
			event.setDtend(end);
			EventToCalendarEntryWrapper wrapper = new EventToCalendarEntryWrapper(
					Locale.GERMANY);
			return wrapper.wrapEventToCalendar(event);	
		} else {
			//parse Recurrent Event
			//if event is in before the given end of the time Frame
			String rrule = event.getRrule();
			if((event.getDtstart().compareTo(end)<=0)){
				if(getRRULEProperty("BYYEAR", rrule) != ""){
					String byYear = getRRULEProperty("BYYEAR", rrule);
					//FIXME: all years in the given time frame have to be checked, encapsule in Methods, because some have to be reused on different paths
					if (byYear.indexOf(begin.getYear())>-1) {
						
					}
				}
			}
			return new ArrayList<CalendarEntry>();
		}
	}
	
	public static String getRRULEProperty(String name, String rrule){
		if (rrule.indexOf(name)>-1){
			int attrBegin = rrule.indexOf(name)+name.length();
			int attrEnd = rrule.indexOf(';', attrBegin);
			return rrule.substring(attrBegin, attrEnd);
		} else return "";
	}
	
	

}
