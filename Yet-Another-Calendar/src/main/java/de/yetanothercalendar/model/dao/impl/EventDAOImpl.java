package de.yetanothercalendar.model.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import de.yetanothercalendar.model.dao.EventDAO;
import de.yetanothercalendar.model.database.helper.DatabaseConnectionManager;

/**
 * Über die Klasse {@link EventDAOImpl} erfolgt der Zugriff auf auf die
 * Datenbank (Tabelle events).
 */
public class EventDAOImpl implements EventDAO {
	private DatabaseConnectionManager manager;

	public EventDAOImpl(DatabaseConnectionManager manager) {
		this.manager = manager;
	}

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
