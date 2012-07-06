package de.yetanothercalendar.model.iCal;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.CalendarParserImpl;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.ComponentList;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyList;
import de.yetanothercalendar.model.database.Event;
import de.yetanothercalendar.model.database.User;

public class ICalendarImporter {

	public ICalendarImporter() {

	}

	/**
	 * Imports a Calendar from a File Input Stream
	 * 
	 * @param in
	 *            InputStream of the Calendar File
	 * @return Calendar Object from Ical4J
	 * @throws IOException
	 * @throws ParserException
	 */
	public Calendar importToIcal4J(InputStream in) throws IOException,
			ParserException {
		CalendarParserImpl cpI = new CalendarParserImpl();
		CalendarBuilder cb = new CalendarBuilder(cpI);
		Calendar iCal4JCal = cb.build(in);
		return iCal4JCal;
	}

	/**
	 * 
	 * @param iCal4j
	 *            iCal4J Calendar
	 * @param user
	 *            user to set as database property
	 * @return Calendar as List of Events in our internal calendar format
	 */
	public List<Event> parseIcal4JToEventList(Calendar iCal4j, User user) {
		ComponentList components = iCal4j.getComponents();
		Component comp;
		List<Event> eventList = new ArrayList<Event>();

		for (int i = 0; i < components.size(); i++) {
			comp = (Component) components.get(i);
			// Only Events get parsed: TODOs, ... get ignored (so far)
			if (comp.getName() == Component.VEVENT) {
				Event event = new Event();

				/*
				 * Get the events' properties an delete the propertyname at the
				 * beginning
				 */

				String uid = getProperty(comp, Property.UID);
				String description = getProperty(comp, Property.DESCRIPTION);
				String location = getProperty(comp, Property.LOCATION);
				String priority = getProperty(comp, Property.PRIORITY);
				String summary = getProperty(comp, Property.SUMMARY);
				String recurid = getProperty(comp, Property.RECURRENCE_ID);
				String rrule = getProperty(comp, Property.RRULE);

				String durationStr = comp.getProperties(Property.DURATION)
						.toString();
				if (durationStr.startsWith("DURATION:PT")) {
					durationStr = durationStr.substring("DURATION:PT".length());
				}

				long duration = 0;
				int durH, durM; // durS
				if (durationStr.indexOf("H") > -1) {
					durH = Integer.parseInt(durationStr.substring(0,
							durationStr.indexOf("H")));
					duration = (60 * durH);
					durationStr = durationStr.substring(durationStr
							.indexOf("H"));
				}
				if (durationStr.indexOf("M") > -1) {
					durM = Integer.parseInt(durationStr.substring(0,
							durationStr.indexOf("M")));
					duration += (durM);
					durationStr = durationStr.substring(durationStr
							.indexOf("M"));
				}

				/*
				 * NOTE: Seconds are ignored so far
				 * 
				 * if (durationStr.indexOf("S")> -1){ durS =
				 * Integer.parseInt(durationStr.substring(0,
				 * durationStr.indexOf("S"))); //duration = (long) (60*durH); }
				 */

				if (duration > 0) {
					event.setDuration(duration);
				}

				List<String> categories = new ArrayList<String>();

				PropertyList categoriesList = comp
						.getProperties(Property.CATEGORIES);

				String categoryString = categoriesList.toString();

				if (categoryString.startsWith("CATEGORIES:")) {
					// +1 because of the : after the name
					categoryString = categoryString.substring("CATEGORIES:"
							.length());
				}

				categoryString = deleteLineFeed(categoryString);

				if (!(categoryString.equals(""))) {
					String[] categoriesString = categoryString.split(",");
					for (String string : categoriesString) {
						categories.add(string);
					}
					event.setCategories(categories);
				}

				String comment = getProperty(comp, Property.COMMENT);

				Date dtstart = convertPropertyToDate(Property.DTSTART, comp);
				Date dtstamp = convertPropertyToDate(Property.DTSTAMP, comp);
				Date created = convertPropertyToDate(Property.CREATED, comp);
				Date lastmod = convertPropertyToDate(Property.LAST_MODIFIED,
						comp);
				Date dtend = convertPropertyToDate(Property.DTEND, comp);
				Date exdate = convertPropertyToDate(Property.EXDATE, comp);

				String rdate = getProperty(comp, Property.RDATE);

				event.setComment(comment);
				event.setCreated(created);
				event.setDescription(description);
				event.setDtend(dtend);
				event.setDtstamp(dtstamp);
				event.setDtstart(dtstart);
				event.setExdate(exdate);
				event.setLastmod(lastmod);
				event.setLocation(location);
				event.setPriority(priority);
				event.setRdate(rdate);
				event.setRecurid(recurid);
				event.setRrule(rrule);
				event.setSummary(summary);
				event.setUid(uid);
				event.setUser(user);
				
				//Event Id not set --> Database sets a new one!
				//event.setId(id);

				System.out.println(event.toString());

				eventList.add(event);
			}
		}

		return eventList;
	}

	/**
	 * Converts a given property of the Component to a Date
	 * 
	 * @param Propertyname
	 *            Name of the Property to convert
	 * @param comp
	 *            Name of the Component of the property
	 * @return Date representation of the property
	 */
	private Date convertPropertyToDate(String Propertyname, Component comp) {
		try {
			String dateStr = comp.getProperty(Propertyname).toString();
			Date d = parseIcsDate(dateStr);
			return d;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Parse an icsDateString ("yyyyMMddTHHmmssZ") to a Date
	 * 
	 * @param dateString
	 *            iCal Date String
	 * @return Date Representation of the iCal dateString
	 * @throws ParseException
	 *             is thrown in case something goes wrong :-(
	 */
	public static Date parseIcsDate(String dateString) throws ParseException {
		StringBuffer dateBuf = new StringBuffer(dateString);
		// Delete Object Description: e.g. "DTSTAMP:" from
		// "DTSTAMP:20120508T201446Z\r\n"

		while (!(Character.isDigit(dateBuf.charAt(0)))) {
			dateBuf.deleteCharAt(0);
		}
		// Delete the T in Date 20120508T201446Z\r\n
		dateString = dateBuf.toString();
		dateString = dateString.replace('T', ' ');

		DateFormat df = new SimpleDateFormat("yyyyMMdd HHmmss");
		Date date = df.parse(dateString.toString());
		System.out.println(date.toString());
		return date;
	}

	/**
	 * Deletes the lineseparator at the end of a String, if there is one
	 * 
	 * @param s
	 *            Input String
	 * @return String without the lineseparator at the End
	 */
	private String deleteLineFeed(String s) {
		int lineSeperatorLength = System.getProperty("line.separator").length();
		if ((s.length() >= lineSeperatorLength)
				&& (s.substring(s.length() - lineSeperatorLength, s.length())
						.equals(System.getProperty("line.separator")))) {
			s = s.substring(0, s.length() - lineSeperatorLength);
		}
		return s;
	}

	/**
	 * Returns a String representation of the Property {@link name} of Component
	 * {@link comp}
	 * 
	 * @param comp
	 * @param name
	 * @return
	 */
	private String getProperty(Component comp, String name) {
		String propertyString = comp.getProperties(name).toString();
		if (propertyString.startsWith(name)) {
			// +1 because of the : after the name
			propertyString = propertyString.substring(name.length() + 1);
		}
		propertyString = deleteLineFeed(propertyString);
		return propertyString;
	}
}
