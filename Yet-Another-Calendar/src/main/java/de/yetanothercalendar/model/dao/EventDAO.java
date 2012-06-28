package de.yetanothercalendar.model.dao;

import java.util.Date;
import java.util.List;

import de.yetanothercalendar.model.database.Event;
import de.yetanothercalendar.model.database.User;

public interface EventDAO {

	/**
	 * Speichert Eventdaten in die Tabelle EVENTS
	 * 
	 * @param event
	 * @return
	 */
	public abstract boolean createEvents(Event event);

	/**
	 * Updated den event mit der Id von @param event;
	 * 
	 * @param event
	 *            Event, welcher geupdate werden soll
	 */
	public abstract void updateEvent(Event event);

	/**
	 * Gibt eine Liste mit allen Events, die in der Tablle EVENTS einem User
	 * zugeordnet sind, zur�ck
	 * 
	 * @param user
	 * @return
	 */
	public abstract List<Event> getEventsFromUser(User user);

	public abstract List<Event> getEventsFromUserRecurring(User user);

	public abstract List<Event> getEventsFromUserNotRecurring(User user);

	/**
	 * 
	 * @param from
	 *            startdatum
	 * @param til
	 *            enddateum
	 * @return eine Liste mit {@link Event}s, welche in den gegebenen Daten
	 *         enthalten sind. Natürlich werden {@link Event}s zurückgegeben,
	 *         die auf einen {@link User} bezogen sind.
	 */
	public abstract List<Event> getEventBetweenDates(User user, Date from,
			Date til);

}