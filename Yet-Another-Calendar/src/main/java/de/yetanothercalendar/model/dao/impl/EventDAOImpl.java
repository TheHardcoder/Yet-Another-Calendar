package de.yetanothercalendar.model.dao.impl;

import java.io.ObjectInputStream.GetField;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.yetanothercalendar.model.dao.EventDAO;

import de.yetanothercalendar.model.database.Event;
import de.yetanothercalendar.model.database.User;
import de.yetanothercalendar.model.database.helper.DatabaseConnectionManager;

/**
 * �ber die Klasse {@link EventDAOImpl} erfolgt der Zugriff auf auf die
 * Datenbank (Tabelle events).
 */
public class EventDAOImpl implements EventDAO {
	private DatabaseConnectionManager manager;

	public EventDAOImpl(DatabaseConnectionManager manager) {
		this.manager = manager;
	}

	/**
	 * Erstellt die Tabelle EVENTS in der die Events ({@link Event})
	 * abgespeichert werden sollen.
	 */
	public void createEventTable() {
		try {
			Connection con = manager.getConnection();
			Statement createStatement = con.createStatement();
			String tablecreationString = "CREATE TABLE IF NOT EXISTS events "
					+ "(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
					+ "userId INT," + "dtstamp DATETIME," + "uid VARCHAR(100),"
					+ "dtstart DATETIME," + "created DATETIME,"
					+ "description TEXT," + "lastmod DATETIME,"
					+ "location VARCHAR(100)," + "priority VARCHAR(100),"
					+ "summary TEXT," + "recurid VARCHAR(100),"
					+ "rrule VARCHAR(150)," + "dtend DATETIME,"
					+ "duration INT," + "color VARCHAR(10),"
					+ "categories VARCHAR(250)," + "comment TEXT,"
					+ "exdate DATETIME," + "rdate DATETIME,"
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

			User user = event.getUser();
			System.out.println(user.toString());
			Long userid = event.getUser().getId();
			if (userid != null) {
				java.sql.Timestamp dtstamp = new java.sql.Timestamp(event
						.getDtstamp().getTime());

				String uid = event.getUid();
				java.sql.Timestamp dtstart = new java.sql.Timestamp(event
						.getDtstart().getTime());
				java.sql.Timestamp created = new java.sql.Timestamp(event
						.getCreated().getTime());
				String description = event.getDescription();
				java.sql.Timestamp lastmod = new java.sql.Timestamp(event
						.getLastmod().getTime());
				String location = event.getLocation();
				String priority = event.getPriority();
				String summary = event.getSummary();
				String recurid = event.getRecurid();
				String rrule = event.getRrule();
				java.sql.Timestamp dtend = new java.sql.Timestamp(event
						.getDtend().getTime());
				long duration = event.getDuration();
				String color = event.getColor();
				List<String> categories = event.getCategories();
				String comment = event.getComment();
				java.sql.Timestamp exdate = new java.sql.Timestamp(event
						.getExdate().getTime());
				String rdate = event.getRdate();

				String eventCreationString = "INSERT INTO events "
						+ "(userId, dtstamp, uid, dtstart,"
						+ " created, description, lastmod, location,"
						+ " priority, summary, recurid, rrule, dtend,"
						+ " duration,	color, categories, comment, exdate,"
						+ " rdate)" + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? );";
				java.sql.PreparedStatement pstmt = con.prepareStatement(eventCreationString);
				pstmt.setLong(1, userid);
				pstmt.setTimestamp(2, dtstamp);				
				pstmt.setString(3, uid);
				pstmt.setTimestamp(4, dtstart);
				pstmt.setTimestamp(5, created);
				pstmt.setString(6, description);
				pstmt.setTimestamp(7, lastmod);
				pstmt.setString(8, location);
				pstmt.setString(9, priority);
				pstmt.setString(10, summary);
				pstmt.setString(11, recurid);
				pstmt.setString(12, rrule);
				pstmt.setTimestamp(13, dtend);
				pstmt.setLong(14, duration);
				pstmt.setString(15, color);
				pstmt.setObject(16, categories);
				pstmt.setString(17, comment);
				pstmt.setTimestamp(18, exdate);
				pstmt.setString(19, rdate);
				
				
				pstmt.executeUpdate();

				pstmt.close();
				con.close();
				return true;
			} else {
				throw new RuntimeException("User id must not be null");
			}
		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}

	}

	public List<Event> getEventsFromUser(User user) {
		List<Event> events = new ArrayList<Event>();
		try {
			String email = user.getEmail();

			String eventCreationString = "SELECT events.id, events.dtstamp,"
					+ " events.uid, events.dtstart, events.created, events.description,"
					+ " events.lastmod, events.location, events.priority,	events.summary,"
					+ " events.recurid,	events.rrule, events.dtend, events.duration,"
					+ "events.color, events.categories, events.comment, events.exdate,"
					+ " events.rdate " + "from events INNER JOIN users"
					+ " ON  (events.userID = users.ID)"
					+ "Where users.email = \"" + email + "\";";

			events = executeSELECTQuery(user, eventCreationString);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return events;
	}

	public List<Event> getEventBetweenDates(User user, Date from, Date til) {
		List<Event> eventlist = getEventsFromUser(user);
		List<Event> btwEventList = new ArrayList<Event>();
		if (!eventlist.isEmpty()) {

			for (int i = 0; i < eventlist.size(); i++) {
				Date start = eventlist.get(i).getDtstart();

				if (start.compareTo(from) >= 0 && start.compareTo(til) <= 0) {
					btwEventList.add(eventlist.get(i));
				}

			}

			return btwEventList;
		} else {
			return null;
		}

	}

	public List<Event> getEventsFromUserRecurring(User user) {
		List<Event> events = new ArrayList<Event>();
		try {
			String email = user.getEmail();
			String eventCreationString = "SELECT events.id, events.dtstamp,"
					+ " events.uid, events.dtstart, events.created, events.description,"
					+ " events.lastmod, events.location, events.priority,	events.summary,"
					+ " events.recurid,	events.rrule, events.dtend, events.duration,"
					+ "events.color, events.categories, events.comment, events.exdate,"
					+ " events.rdate " + "from events INNER JOIN users"
					+ " ON  (events.userID = users.ID)"
					+ "Where users.email = \"" + email
					+ "\" and events.rrule IS NULL;";
			events = executeSELECTQuery(user, eventCreationString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return events;
	}

	public List<Event> getEventsFromUserNotRecurring(User user) {
		List<Event> events = new ArrayList<Event>();
		try {
			String email = user.getEmail();
			String eventCreationString = "SELECT events.id, events.dtstamp,"
					+ " events.uid, events.dtstart, events.created, events.description,"
					+ " events.lastmod, events.location, events.priority,	events.summary,"
					+ " events.recurid,	events.rrule, events.dtend, events.duration,"
					+ "events.color, events.categories, events.comment, events.exdate,"
					+ " events.rdate " + "from events INNER JOIN users"
					+ " ON  (events.userID = users.ID)"
					+ "Where users.email = \"" + email
					+ "\" and events.rrule IS NOT NULL;";
			events = executeSELECTQuery(user, eventCreationString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return events;
	}

	private List<Event> executeSELECTQuery(User user, String query)
			throws SQLException, ParseException {
		Connection con = manager.getConnection();
		Statement createStatement = con.createStatement();
		ResultSet rsEvent = createStatement.executeQuery(query);
		List<Event> events = new ArrayList<Event>();
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern("yyyy-MM-dd HH:mm");
		while (rsEvent.next()) {

			long id = (long) rsEvent.getInt(1);
			Date dtstamp = sdf.parse(rsEvent.getString(2));
			String uid = rsEvent.getString(3);
			Date dtstart = sdf.parse(rsEvent.getString(4));
			Date created = sdf.parse(rsEvent.getString(5));
			String description = rsEvent.getString(6);
			Date lastmod = sdf.parse(rsEvent.getString(7));
			String location = rsEvent.getString(8);
			String priority = rsEvent.getString(9);
			String summary = rsEvent.getString(10);
			String recurid = rsEvent.getString(11);
			String rrule = rsEvent.getString(12);
			Date dtend = sdf.parse(rsEvent.getString(13));
			long duration = rsEvent.getLong(14);
			String color = rsEvent.getString(15);

			List<String> categories = new ArrayList<String>();
			categories.add(rsEvent.getString(16));

			String comment = rsEvent.getString(17);
			Date exdate = sdf.parse(rsEvent.getString(18));
			String rdate = rsEvent.getString(19);

			events.add(new Event(id, user, dtstamp, uid, dtstart, created,
					description, lastmod, location, priority, summary, recurid,
					rrule, dtend, duration, color, categories, comment, exdate,
					rdate));
		}

		rsEvent.close();
		createStatement.close();
		con.close();
		return events;
	}
}
