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

public class EventDAOMockFull implements EventDAO {

	public List<Event> getEventBetweenDates(User user, Date from, Date til) {
		List<Event> eventList = new ArrayList<Event>();
		// TODO : viele events einfuegen und im CalendarEventToEntryWrapper die
		// GESAMTE OO Strukture testen. Dabei wird vorallem die neu
		// hinzugefuegte
		// map getestet..
		// TODO es werden erst alle termine im Jahr gemappt
		Calendar calendarCreated = new GregorianCalendar(Locale.GERMANY);
		calendarCreated.set(2011, 1, 1, 1, 1);
		Calendar calendar = new GregorianCalendar(Locale.GERMANY);
		calendar.set(2012, 0, 01, 10, 00);
		Calendar calendar2 = new GregorianCalendar(Locale.GERMANY);
		calendar2.set(2012, 0, 05, 10, 00);
		for (int yearcount = 0; yearcount < 4; yearcount++) {
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 5; j++) {
					Event createDummyEvent = createDummyEvent(user,
							calendarCreated, calendar, calendar2);
					System.out.println(createDummyEvent.getId() + " - "
							+ createDummyEvent.getDtstart().toString() + " - "
							+ createDummyEvent.getDtend().toString());
					eventList.add(createDummyEvent);
					calendar.add(Calendar.DAY_OF_YEAR, 7);
					calendar2.add(Calendar.DAY_OF_YEAR, 7);
				}
				calendar.add(Calendar.MONTH, 1);
				calendar2.add(Calendar.MONTH, 1);
			}
			calendar.add(Calendar.YEAR, 1);
			calendar2.add(Calendar.YEAR, 1);
		}
		return eventList;
	}

	private Event createDummyEvent(User user, Calendar created, Calendar start,
			Calendar end) {
		return new Event(new Long((int) (Math.random() * 100000)), user,
				new Date(), "uuid", start.getTime(), created.getTime(),
				"description", new Date(), "location", "very high",
				"what a great summary", "recurrid", "rrule", end.getTime(), 0,
				"#fff", new ArrayList<String>(), "comment", new Date(),
				new Date());
	}

	public void createEventTable() {
	}
}
