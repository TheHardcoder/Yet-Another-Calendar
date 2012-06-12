package de.yetanothercalendar.model.dao;

import java.util.Date;
import java.util.List;

import de.yetanothercalendar.model.database.Event;
import de.yetanothercalendar.model.database.User;

public interface EventDAO {

	/**
	 * Erstellt die Tabelle EVENTS in der die Benutzer ({@link Event})
	 * abgespeichert werden sollen.
	 */
	public abstract void createEventTable();

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