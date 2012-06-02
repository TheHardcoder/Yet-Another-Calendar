package de.yetanothercalendar.db.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import de.yetanothercalendar.db.helper.DatabaseConnectionManager;
import de.yetanothercalendar.model.database.Event;

/**
 * Über die Klasse {@link EventDAO} erfolgt der Zugriff auf auf die Datenbank
 * (Tabelle events).
 */
public class EventDAO {
	private DatabaseConnectionManager manager;

	public EventDAO(DatabaseConnectionManager manager) {
		this.manager = manager;
	}

	/**
	 * Erstellt die Tabelle EVENTS in der die Benutzer ({@link Event})
	 * abgespeichert werden sollen.
	 */
	public void createUserTable() {
		try {
			Connection con = manager.getConnection();
			Statement createStatement = con.createStatement();
			String tablecreationString = "create table events "
					+ "(id INT PRIMARY KEY," + "userId INT," + "dtstamp DATE,"
					+ "uid VARCHAR(500)," + "dtstart DATE," + "created DATE,"
					+ "description TEXT," + "lastmod DATE,"
					+ "location VARCHAR(500)," + "priority VARCHAR(500),"
					+ "summary TEXT," + "recurid VARCHAR(500),"
					+ "rrule VARCHAR(500)," + "dtend DATE," + "duration INT,"
					+ "color VARCHAR(500)," + "categories VARCHAR(500),"
					+ "comment TEXT," + "exdate DATE," + "rdate VARCHAR(500));";
			createStatement.executeUpdate(tablecreationString);
			createStatement.close();
			con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
