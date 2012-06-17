package de.yetanothercalendar.model.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import de.yetanothercalendar.model.calendar.CalendarEntry;
import de.yetanothercalendar.model.database.Event;

public class RecurrentEventToCalendarEntryWrapper {

	private Locale locale;

	public RecurrentEventToCalendarEntryWrapper(Locale locale) {
		super();
		this.locale = locale;
	}

	public List<CalendarEntry> wrapEventToCalendar(Event event, Date begin,
			Date end) {
		// TODO Auto-generated method stub
		if (event.getRrule().equals("")) {
			event.setDtend(end);
			EventToCalendarEntryWrapper wrapper = new EventToCalendarEntryWrapper(
					Locale.GERMANY);
			return wrapper.wrapEventToCalendar(event);
		} else {
			// parse Recurrent Event
			// if event is in before the given end of the time Frame

			RRule rrule = new RRule(event.getRrule());
			boolean endsBeforeStartOfEvent = ((rrule.getUntil() != null) && (rrule
					.getUntil().compareTo(begin) <= 0));
			boolean startsAfterEndOfEvent = (event.getDtstart().compareTo(end) > 0);

			// RRule ends before end of the given Time Frame
			if ((rrule.getUntil() != null)
					&& (rrule.getUntil().compareTo(end) < 0)) {
				end = rrule.getUntil();
			}

			if (!endsBeforeStartOfEvent || !startsAfterEndOfEvent) {

			} else {
				// No event in the given time Frame
				return null;
			}
			return new ArrayList<CalendarEntry>();
		}
	}

	public static String getRRULEProperty(String name, String rrule) {
		if (rrule.indexOf(name) > -1) {
			int attrBegin = rrule.indexOf(name) + name.length();
			int attrEnd = rrule.indexOf(';', attrBegin);
			return rrule.substring(attrBegin, attrEnd);
		} else
			return "";
	}

	public List<Integer> getMonthsInTimeFrame(Date start, Date end) {
		boolean sameYear = start.getYear() == end.getYear();
		boolean oneYearDifference = start.getYear() == (end.getYear() - 1);

		if (sameYear || oneYearDifference) {
			int monthDifference = (sameYear) ? start.getMonth()
					- end.getMonth() : 12 - (start.getMonth() + 1)
					+ (end.getMonth() + 1);
			List<Integer> months = new ArrayList<Integer>();
			for (int i = 0; i < monthDifference; i++) {
				// +1 because the Month start counting at zero in the Date-Class
				months.add((start.getMonth() + 1 + i) % 12);
			}
			return months;

		} else {
			// all months occur between start and end date --> return a List
			// with all Months
			List<Integer> months = new ArrayList<Integer>(12);
			for (int i = 1; i <= 12; i++) {
				months.add(i);
			}
			return months;
		}

	}

	public List<Integer> getWeeksInTimeFrame(Date start, Date end) {
		boolean sameYear = start.getYear() == end.getYear();
		boolean oneYearDifference = start.getYear() == (end.getYear() - 1);
		Calendar startCal = new GregorianCalendar();
		startCal.setTime(start);

		Calendar endCal = new GregorianCalendar();
		startCal.setTime(end);

		if (sameYear || oneYearDifference) {
			int weekDifference = (sameYear) ? startCal
					.get(Calendar.WEEK_OF_YEAR)
					- endCal.get(Calendar.WEEK_OF_YEAR) : 52
					- startCal.get(Calendar.WEEK_OF_YEAR)
					+ endCal.get(Calendar.WEEK_OF_YEAR);

			List<Integer> weeks = new ArrayList<Integer>();
			for (int i = 0; i < weekDifference; i++) {
				weeks.add((startCal.get(Calendar.WEEK_OF_YEAR) + i) % 52);
			}
			return weeks;

		} else {
			// all Weeks occur between start and end date --> return a List
			// with all Weeks
			List<Integer> weeks = new ArrayList<Integer>(52);
			for (int i = 1; i <= 52; i++) {
				weeks.add(i);
			}
			return weeks;
		}

	}

	public List<Date> getByMonthCandidates(Date start, Date end)
			throws IllegalArgumentException {
		List<Date> candidates = new ArrayList<Date>();
		Calendar startCal = new GregorianCalendar();
		startCal.setTime(start);
		Calendar endCal = new GregorianCalendar();
		startCal.setTime(end);
		Calendar curCal = (Calendar) startCal.clone();

		if (start.before(end)) {
			List<Integer> monthsInTimeFrame = getMonthsInTimeFrame(start, end);
			while (curCal.before(endCal)) {
				if (monthsInTimeFrame
						.contains((curCal.get(Calendar.MONTH) + 1))) {
					candidates.add(curCal.getTime());
				}
				// increment Calendar Day
				curCal.roll(Calendar.DATE, true);
			}
		} else {
			throw new IllegalArgumentException(
					"GetByMonthCandidates: Enddate before start date");
		}

		return candidates;
	}

	public List<Date> getByWeekNoCandidates(List<Date> dates, Date start,
			Date end) throws IllegalArgumentException {
		// Filter wrong weeks form the given Datelist, remember to set Weekstart
		// first
		List<Date> candidates = new ArrayList<Date>();
		List<Integer> weeksInTimeFrame = getWeeksInTimeFrame(start, end);

		for (Date date : dates) {
			Calendar tmpCal = new GregorianCalendar();
			tmpCal.setTime(date);
			if (weeksInTimeFrame.contains(tmpCal.get(Calendar.WEEK_OF_YEAR))) {
				candidates.add(date);
			}
		}
		return candidates;
	}
}
