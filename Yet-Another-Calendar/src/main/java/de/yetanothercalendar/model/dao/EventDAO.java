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
	 * 			welches gespeichert werden soll
	 * @return  eine {@link Event}, das auf einen {@link User} bezogen ist
	 * 				und vorher gespeichert wurde.
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
	 * 
	 * @param user
	 * @return eine Liste mit {@link Event}s, die auf einen {@link User} bezogen sind.
	 */
	public abstract List<Event> getEventsFromUser(User user);
	
	/**
	 * 
	 * @param user
	 * @return eine Liste mit {@link Event}s, die auf einen {@link User} bezogen sind und 
	 * 		   rrule NULL ist
	 */
	public abstract List<Event> getEventsFromUserRecurring(User user);
	
	/**
	 * 
	 * @param user
	 * @return eine Liste mit {@link Event}s, die auf einen {@link User} bezogen sind und 
	 * 		   rrule NICHT NULL ist
	 */
	public abstract List<Event> getEventsFromUserNotRecurring(User user);

	/**
	 * 
	 * @param from
	 *            startdatum
	 * @param til
	 *            enddateum
	 * @return eine Liste mit {@link Event}s, welche in den gegebenen Daten
	 *         enthalten sind. Natuerlich werden {@link Event}s zurueckgegeben,
	 *         die auf einen {@link User} bezogen sind.
	 */
	public abstract List<Event> getEventBetweenDates(User user, Date from,
			Date til);
	/**
	 * 
	 * 
	 * @param event
	 * @return ein {@link Event}, welches geupdatet oder erstellt worden ist,
	 * 		   das auf einen {@link User} bezogen ist.
	 */
	public abstract Event checkEvent(Event event);
}