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

	public static Calendar importToIcal4J(InputStream in) throws IOException,
			ParserException {
		CalendarParserImpl cpI = new CalendarParserImpl();
		CalendarBuilder cb = new CalendarBuilder(cpI);
		Calendar iCal4JCal = cb.build(in);
		return iCal4JCal;
	}

	public static List<Event> parseIcal4JToEventList(Calendar iCal4j, User user) {
		ComponentList components = iCal4j.getComponents();
		Component comp;

		for (int i = 0; i < components.size(); i++) {
			comp = (Component) components.get(i);
			if (comp.getName() == comp.VEVENT) {
				Event event = new Event();
				// Property prop = comp.getProperty(Property.);
				// prop.
				Long id = (long) 1337; // What to set here?

				String uid = comp.getProperties(Property.UID).toString();
				String description = comp.getProperties(Property.DESCRIPTION)
						.toString();
				String location = comp.getProperties(Property.LOCATION)
						.toString();
				String priority = comp.getProperties(Property.PRIORITY)
						.toString();
				String summary = comp.getProperties(Property.SUMMARY)
						.toString();
				String recurid = comp.getProperties(Property.RECURRENCE_ID)
						.toString();
				String rrule = comp.getProperties(Property.RRULE).toString();

				String durationStr = comp.getProperties(Property.DURATION)
						.toString();
				Long duration;
				try {
					duration = Long.parseLong(durationStr);
					event.setDuration(duration);
				} catch (Exception e) {
					duration = null;
					System.out.println("Error Parsing Event Duration: "+durationStr);
				}

				List<String> categories = new ArrayList();

				PropertyList categoriesList = comp
						.getProperties(Property.CATEGORIES);

				for (Object category : categoriesList) {
					categories.add(category.toString());
				}

				String comment = comp.getProperties(Property.COMMENT)
						.toString();

				Date dtstart = setDateProperty(Property.DTSTART, comp);
				Date dtstamp = setDateProperty(Property.DTSTAMP, comp);
				Date created = setDateProperty(Property.CREATED, comp);
				Date lastmod = setDateProperty(Property.LAST_MODIFIED, comp);
				Date dtend = setDateProperty(Property.DTEND, comp);
				Date exdate = setDateProperty(Property.EXDATE, comp);
				Date rdate = setDateProperty(Property.RDATE, comp);

				event.setCategories(categories);
				//event.setColor(COLOR); can be set to a standard color
				event.setComment(comment);
				event.setCreated(created);
				event.setDescription(description);
				event.setDtend(dtend);
				event.setDtstamp(dtstamp);
				event.setDtstart(dtstart);
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
			}
		}
		return new ArrayList<Event>();
	}

	public static Date setDateProperty(String Propertyname, Component comp) {
		try {
			Date d = parseIcsDate(comp.getProperty(Propertyname).toString());
			return d;
		} catch (Exception e) {
			return null;
		}
	}

	public static Date parseIcsDate(String dateString) throws ParseException {
		dateString = dateString.replace('T', ' ');
		DateFormat df = new SimpleDateFormat("yyyyMMdd HHmmss");
		Date date = df.parse(dateString);
		System.out.println(date.toString());
		return date;
	}
}
