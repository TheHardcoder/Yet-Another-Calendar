package de.yetanothercalendar.model.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import de.yetanothercalendar.model.dao.EventDAO;
import de.yetanothercalendar.model.database.Event;
import de.yetanothercalendar.model.database.User;
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

	public void createEventTable() {
		try {
			Connection con = manager.getConnection();
			Statement createStatement = con.createStatement();
			String tablecreationString = "CREATE TABLE IF NOT EXISTS events "
					+ "(id INT PRIMARY KEY," + "userId INT," + "dtstamp DATE,"
					+ "uid VARCHAR(100)," + "dtstart DATE," + "created DATE,"
					+ "description TEXT," + "lastmod DATE,"
					+ "location VARCHAR(100)," + "priority VARCHAR(100),"
					+ "summary TEXT," + "recurid VARCHAR(100),"
					+ "rrule VARCHAR(150)," + "dtend DATE," + "duration INT,"
					+ "color VARCHAR(10)," + "categories VARCHAR(250),"
					+ "comment TEXT," + "exdate DATE," + "rdate DATE," +
							"FOREIGN KEY (userID)  REFERENCES users (id));";
			createStatement.executeUpdate(tablecreationString);
			createStatement.close();
			con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public List<Event> getEventBetweenDates(User user, Date from, Date til) {
		// TODO Auto-generated method stub
		return null;
	}
}
