package de.yetanothercalendar.model.iCal;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.CalendarParserImpl;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.ComponentList;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Period;
import net.fortuna.ical4j.model.PeriodList;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyList;
import de.yetanothercalendar.model.database.Event;
import de.yetanothercalendar.model.database.User;

public class ICalendarImporter {

	public ICalendarImporter() {

	}

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
			if (comp.getName() == comp.VEVENT) {
				Event event = new Event();

				// TODO: Clarify what to set for ID and Color when importing
				// iCal-Files
				Long id = (long) 1337;
				// event.setColor(COLOR); can be set to a standard color

				/**
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

				/**
				 * Debug Code for ICal RRule Testing FIXME: Evaluate and Delete
				 */

				String durationStr = comp.getProperties(Property.DURATION)
						.toString();
				if (durationStr.startsWith("DURATION:PT")) {
					durationStr = durationStr.substring("DURATION:PT".length());
				}

				long duration = -1;
				int durH, durM, durS;
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
				/**
				 * NOTE: Seconds are ignored so far
				 * 
				 * if (durationStr.indexOf("S")> -1){ durS =
				 * Integer.parseInt(durationStr.substring(0,
				 * durationStr.indexOf("S"))); //duration = (long) (60*durH); }
				 * 
				 */

				List<String> categories = new ArrayList();

				PropertyList categoriesList = comp
						.getProperties(Property.CATEGORIES);

				for (Object category : categoriesList) {
					if (category.toString().startsWith("CATEGORIES:")) {
						categories.add(category.toString().substring(
								"CATEGORIES:".length()));
					} else {
						categories.add(category.toString());
					}
				}

				String comment = getProperty(comp, Property.COMMENT);

				Date dtstart = setDateProperty(Property.DTSTART, comp);
				Date dtstamp = setDateProperty(Property.DTSTAMP, comp);
				Date created = setDateProperty(Property.CREATED, comp);
				Date lastmod = setDateProperty(Property.LAST_MODIFIED, comp);
				Date dtend = setDateProperty(Property.DTEND, comp);
				Date exdate = setDateProperty(Property.EXDATE, comp);

				/**
				 * FIXME: rdate can be a List of Dates! maybe save as String or
				 * as arrayList, depending on what is needed to parse back to
				 * Ical4J later on
				 */
				Date rdate = setDateProperty(Property.RDATE, comp);

				event.setCategories(categories);
				event.setComment(comment);
				event.setCreated(created);
				event.setDescription(description);
				event.setDtend(dtend);
				event.setDtstamp(dtstamp);
				event.setDtstart(dtstart);
				event.setDuration(duration);
				event.setExdate(exdate);
				event.setId(id);
				event.setLastmod(lastmod);
				event.setLocation(location);
				event.setPriority(priority);
				event.setRdate(rdate);
				event.setRecurid(recurid);
				event.setRrule(rrule);
				event.setSummary(summary);
				event.setUid(uid);
				event.setUser(user);

				System.out.println(event.toString());

				eventList.add(event);
			}
		}

		return eventList;
	}

	private Date setDateProperty(String Propertyname, Component comp) {
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
	 * Deletes the lineseperator at the end of a String, if there is one
	 * 
	 * @param s
	 *            Input String
	 * @return String without the lineseperator at the End
	 */
	private String deleteLineFeed(String s) {
		int lineSeperatorLength = System.getProperty("line.separator").length();
		if ((s.length() > lineSeperatorLength)
				&& (s.substring(s.length() - lineSeperatorLength, s.length())
						.equals(System.getProperty("line.separator")))) {
			s = s.substring(0, s.length() - lineSeperatorLength);
		}
		return s;
	}

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
