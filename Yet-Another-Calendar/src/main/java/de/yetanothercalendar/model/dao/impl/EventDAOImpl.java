package de.yetanothercalendar.model.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.fortuna.ical4j.model.property.Categories;

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
					+ "(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
					+ "userId INT," + "dtstamp DATE," + "uid VARCHAR(100),"
					+ "dtstart DATE," + "created DATE," + "description TEXT,"
					+ "lastmod DATE," + "location VARCHAR(100),"
					+ "priority VARCHAR(100)," + "summary TEXT,"
					+ "recurid VARCHAR(100)," + "rrule VARCHAR(150),"
					+ "dtend DATE," + "duration INT," + "color VARCHAR(10),"
					+ "categories VARCHAR(250)," + "comment TEXT,"
					+ "exdate DATE," + "rdate DATE,"
					+ "FOREIGN KEY (userId)  REFERENCES users (id));";
			createStatement.executeUpdate(tablecreationString);
			createStatement.close();
			con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public boolean createEvents(Event event) {
		try {
			Connection con = manager.getConnection();
			Statement createStatement = con.createStatement();
			
			SimpleDateFormat sdf = new SimpleDateFormat();
			sdf.applyPattern( "yyyy-MM-DD hh:mm" );
			
			int id = event.getId();

			User user = event.getUser();
			int userId = event.getUser().getId();
			
			
			String dtstamp = sdf.format(event.getDtstamp()); System.out.println(dtstamp);
			String uid = event.getUid();
			String dtstart = sdf.format(event.getDtstart());
			String created = sdf.format(event.getCreated()); 
			String description = event.getDescription();
			String lastmod = sdf.format(event.getLastmod()); 
			String location = event.getLocation();
			String priority = event.getPriority();
			String summary = event.getSummary();
			String recurid = event.getRecurid();
			String rrule = event.getRrule();
			String dtend = sdf.format(event.getDtend());
			long duration = event.getDuration();
			String color = event.getColor();
			List<String> categories = event.getCategories();
			String comment = event.getComment();
			String exdate = sdf.format(event.getExdate());
			String rdate = sdf.format(event.getRdate());

			String eventCreationString = "INSERT INTO events "
					+ "(userId, dtstamp, uid, dtstart,"
					+ " created, description, lastmod, location,"
					+ " priority, summary, recurid, rrule, dtend,"
					+ " duration,	color, categories, comment, exdate,"
					+ " rdate)" + "VALUES (\n\""
					+ userId
					+ "\", \""
					+ dtstamp
					+ "\", \""
					+ uid
					+ "\", \""
					+ dtstart
					+ "\", \""
					+ created
					+ "\",\n\" "
					+ description
					+ "\", \""
					+ lastmod
					+ "\", \""
					+ location
					+ "\", \""
					+ priority
					+ "\", \""
					+ summary
					+ "\",\n \""
					+ recurid
					+ "\", \""
					+ rrule
					+ "\", \""
					+ dtend
					+ "\", "
					+ duration
					+ ", \""
					+ color
					+ "\", \""
					+ categories
					+ "\", \""
					+ comment
					+ "\", \""
					+ exdate
					+ "\", \"" + rdate + "\");";

			createStatement.executeUpdate(eventCreationString);

			createStatement.close();
			con.close();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}

	}

	public List<Event> getEventBetweenDates(User user, Date from, Date til) {
		try {
			Connection con = manager.getConnection();
			Statement createStatement = con.createStatement();
			String email = user.getEmail();

			String eventCreationString = "SELECT events.id, events.dtstamp,"
					+ " events.uid, events.dtstart, events.created, events.description,"
					+ " events.lastmod, events.location, events.priority,	events.summary,"
					+ " events.recurid,	events.rrule, events.dtend, events.duration,"
					+ "events.color, events.categories, events.comment, events.exdate,"
					+ " events.rdate " + "from events INNER JOIN users"
					+ " ON  (events.userID = users.ID)"
					+ "Where users.email = \"" + email
					+ "\" and events.dtend BETWEEN \"" + from + "\" and \""
					+ til + "\";";

			ResultSet rsEvent = createStatement
					.executeQuery(eventCreationString);
			List<Event> events = new ArrayList<Event>();

			while (rsEvent.next()) {

				int id = rsEvent.getInt(1);
				Date dtstamp = rsEvent.getDate(2);
				String uid = rsEvent.getString(3);
				Date dtstart = rsEvent.getDate(4);
				Date created = rsEvent.getDate(5);
				String description = rsEvent.getString(6);
				Date lastmod = rsEvent.getDate(7);
				String location = rsEvent.getString(8);
				String priority = rsEvent.getString(9);
				String summary = rsEvent.getString(10);
				String recurid = rsEvent.getString(11);
				String rrule = rsEvent.getString(12);
				Date dtend = rsEvent.getDate(13);
				long duration = rsEvent.getLong(14);
				String color = rsEvent.getString(15);

				List<String> categories = new ArrayList<String>();
				categories.add(rsEvent.getString(16));

				String comment = rsEvent.getString(17);
				Date exdate = rsEvent.getDate(18);
				Date rdate = rsEvent.getDate(19);

				events.add(new Event(id, user, dtstamp, uid, dtstart, created,
						description, lastmod, location, priority, summary,
						recurid, rrule, dtend, duration, color, categories,
						comment, exdate, rdate));
			}

			createStatement.close();
			con.close();
			return events;
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}
}
