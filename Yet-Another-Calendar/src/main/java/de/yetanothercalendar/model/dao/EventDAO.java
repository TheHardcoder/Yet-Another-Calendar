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
	 * Updated das event mit der Id von @param event;
	 * 
	 * @param event
	 *            Event, welches geupdatet werden soll
	 */
	public abstract void updateEvent(Event event);
	
	/**
	 * Löscht das event mit der Id von @param event;
	 * 
	 * @param event
	 *            Event, welches gelöscht werden soll
	 */
	public void deleteEvent(Event event);

	/**
	 * Gibt eine Liste mit allen Events, die in der Tablle EVENTS einem User
	 * zugeordnet sind, zurï¿½ck
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
	 *         enthalten sind. NatÃ¼rlich werden {@link Event}s zurÃ¼ckgegeben,
	 *         die auf einen {@link User} bezogen sind.
	 */
	public abstract List<Event> getEventBetweenDates(User user, Date from,
			Date til);

}