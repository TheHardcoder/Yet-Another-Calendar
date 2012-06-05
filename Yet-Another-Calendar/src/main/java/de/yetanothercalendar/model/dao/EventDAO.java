package de.yetanothercalendar.model.dao;

import de.yetanothercalendar.model.database.Event;

public interface EventDAO {

	/**
	 * Erstellt die Tabelle EVENTS in der die Benutzer ({@link Event})
	 * abgespeichert werden sollen.
	 */
	public abstract void createUserTable();

}