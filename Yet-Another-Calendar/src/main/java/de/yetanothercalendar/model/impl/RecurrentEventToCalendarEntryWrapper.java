package de.yetanothercalendar.model.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import net.fortuna.ical4j.model.DateList;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Dur;
import net.fortuna.ical4j.model.Period;
import net.fortuna.ical4j.model.PeriodList;
import net.fortuna.ical4j.model.Recur;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.DtEnd;
import net.fortuna.ical4j.model.property.DtStart;
import net.fortuna.ical4j.model.property.Duration;
import net.fortuna.ical4j.model.property.ExDate;
import net.fortuna.ical4j.model.property.RDate;
import net.fortuna.ical4j.model.property.RecurrenceId;

import de.yetanothercalendar.model.calendar.CalendarEntry;
import de.yetanothercalendar.model.database.Event;

public class RecurrentEventToCalendarEntryWrapper {

	private Locale locale;

	public RecurrentEventToCalendarEntryWrapper(Locale locale) {
		super();
		this.locale = locale;
	}

	/**
	 * Wraps a (recurrent) Event to multiple Events in a given time frame
	 * without the RRULE
	 * 
	 * @param event
	 *            Event to wrap
	 * @param begin
	 *            begin of the time frame
	 * @param end
	 *            end of the time frame
	 * @return List of Calendar Entries
	 * @throws IllegalArgumentException
	 * @throws ParseException
	 */
	public List<CalendarEntry> wrapEventToCalendar(Event event, Date begin,
			Date end) throws IllegalArgumentException, ParseException {
		// TODO Auto-generated method stub
		if ((event.getRrule() == null)||(event.getRrule().equals(""))) {
			// TODO warum wird hier das enddatum ueberschrieben?
			// event.setDtend(end);
			EventToCalendarEntryWrapper wrapper = new EventToCalendarEntryWrapper(
					Locale.GERMANY);
			return wrapper.wrapEventToCalendar(event);
		} else {
			// parse Recurrent Event
			// if event is in before the given end of the time Frame

			Calendar calBegin = new GregorianCalendar(locale);
			calBegin.setTime(begin);

			// Convert Dates into ICal4J Date format
			DateTime dtBegin = new DateTime(begin);
			DateTime dtEnd = new DateTime(end);

			Period period = new Period(dtBegin, dtEnd);

			// Create a VEvent this is needed to use the
			// calculateReccurenceSet-Method of iCal4J
			VEvent ve = new VEvent();

			/**
			 * Only the necessary properties are added to the ICal4J Event
			 */

			net.fortuna.ical4j.model.Date d = new net.fortuna.ical4j.model.DateTime(
					event.getDtstart());
			ve.getProperties().add(new DtStart(d));

			try {
				d = new net.fortuna.ical4j.model.DateTime(event.getDtend());
				ve.getProperties().add(new DtEnd(d));
			} catch (Exception e) {
				// End time not set
				System.err.println("Error Parrsing Endtime");
			}

			Recur recur = new Recur(event.getRrule());
			ve.getProperties().add(
					new net.fortuna.ical4j.model.property.RRule(recur));

			if (event.getRecurid() != "") {
				ve.getProperties().add(new RecurrenceId(event.getRecurid()));
			}
			
			if ((event.getExdate() != null) && (event.getExdate().size() >= 1)){
				DateList dList = new DateList(event.getExdateString(), null);
				ve.getProperties().add(new ExDate(dList));
			}

			DateList rdates = new DateList(event.getRdate(), null);
			ve.getProperties().add(new RDate(rdates));

			// calculate an end Date, if Duration Attribute is set to create a
			// Duration Object for Ical4J
			if (event.getDuration() > 0) {
				Dur dur = new Dur(0, 0, (int) event.getDuration(), 0);
				ve.getProperties().add(new Duration(dur));
			}

			PeriodList perList = ve.calculateRecurrenceSet(period);
			// perList = perList.normalise();

			List<Event> events = new ArrayList<Event>();

			for (Iterator<Period> iterator = perList.iterator(); iterator
					.hasNext();) {
				Period per = iterator.next();
				// all the original Properties of the Event get reused, only
				// Start and Enddate get set
				// otherwise it would always the same pointer
				event = event.getCopy();
				event.setRrule("");
				event.setDtstart(per.getStart());
				event.setDtend(per.getEnd());
				events.add(event);
//				System.out.println("Start: " + per.getStart() + " Ende: "
//						+ per.getEnd());
			}

			EventToCalendarEntryWrapper wrapper = new EventToCalendarEntryWrapper(
					Locale.GERMANY);

			List<CalendarEntry> calendarEntries = new ArrayList<CalendarEntry>();

			for (Iterator<Event> iterator = events.iterator(); iterator
					.hasNext();) {
				Event event2 = iterator.next();
				calendarEntries.addAll(wrapper.wrapEventToCalendar(event2));
			}

			return calendarEntries;
		}
	}

	/**
	 * Returns a proper String representation of an RRULE Property
	 * 
	 * @param name
	 *            Property name
	 * @param rrule
	 *            rrule-String
	 * @return String representation of the property-value
	 */
	public static String getRRULEProperty(String name, String rrule) {
		if (rrule.indexOf(name) > -1) {
			int attrBegin = rrule.indexOf(name) + name.length();
			int attrEnd = rrule.indexOf(';', attrBegin);
			return rrule.substring(attrBegin, attrEnd);
		} else
			return "";
	}
}
