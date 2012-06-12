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
public class EventDAOMockSimple implements EventDAO {

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
		eventList.add(createDummyEvent(user, created, start, end));
		return eventList;
	}

	private Event createDummyEvent(User user, Calendar created, Calendar start,
			Calendar end) {
		return new Event(new Long(12), user, new Date(), "uuid",
				start.getTime(), created.getTime(), "description", new Date(),
				"location", "very high", "what a great summary", "recurrid",
				"rrule", end.getTime(), 0, "#fff", new ArrayList<String>(),
				"comment", new Date(), new Date());
	}
}