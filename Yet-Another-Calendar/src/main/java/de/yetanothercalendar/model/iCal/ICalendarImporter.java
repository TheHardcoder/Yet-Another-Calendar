package de.yetanothercalendar.model.iCal;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import de.yetanothercalendar.model.database.Event;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.CalendarParserImpl;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.ComponentList;

public class ICalendarImporter {
	
	public Calendar importToIcal4J (InputStream in) throws IOException, ParserException{
		CalendarParserImpl cpI = new CalendarParserImpl();
		CalendarBuilder cb = new CalendarBuilder(cpI);
		Calendar iCal4JCal = cb.build(in);
		return iCal4JCal;
	}
	
	public List<Event> parseIcal4JToEventList(Calendar iCal4j){
		ComponentList components = iCal4j.getComponents();
		Component comp;
		
		for (int i = 0; i < components.size(); i++) {
			comp = (Component) components.get(i);
		}
		return new ArrayList<Event>();
	}
}
