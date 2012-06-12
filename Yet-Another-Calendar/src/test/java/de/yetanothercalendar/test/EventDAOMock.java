package de.yetanothercalendar.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import de.yetanothercalendar.model.dao.EventDAO;
import de.yetanothercalendar.model.database.Event;
import de.yetanothercalendar.model.database.User;

/**
 * Mock fue das EventDAO, um testdaten zu generieren
 * 
 * @author D056995
 * 
 */
public class EventDAOMock implements EventDAO {

	public void createEventTable() {
		// TODO Auto-generated method stub

	}

	public List<Event> getEventBetweenDates(User user, Date from, Date til) {
		List<Event> eventList = new ArrayList<Event>();
		Calendar created = new GregorianCalendar(Locale.GERMANY);
		created.set(2012, 03, 06, 10, 00);
		Calendar start = new GregorianCalendar(Locale.GERMANY);
		start.set(2012, 03, 06, 11, 00);
		Calendar end = new GregorianCalendar(Locale.GERMANY);
		end.set(2012, 03, 06, 13, 00);
		eventList.add(new Event(null, null, null, null, start.getTime(),
				created.getTime(), null, null, null, null, null, null, null,
				end.getTime(), 0, null, null, null, null, null));
		return eventList;
	}
}
