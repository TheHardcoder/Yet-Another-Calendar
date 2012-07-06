package de.yetanothercalendar.model.impl;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import de.yetanothercalendar.helper.Pair;
import de.yetanothercalendar.model.Calendar;
import de.yetanothercalendar.model.calendar.CalendarEntry;
import de.yetanothercalendar.model.calendar.Day;
import de.yetanothercalendar.model.calendar.Month;
import de.yetanothercalendar.model.calendar.Week;
import de.yetanothercalendar.model.calendar.Year;
import de.yetanothercalendar.model.dao.EventDAO;
import de.yetanothercalendar.model.dao.impl.EventDAOImpl;
import de.yetanothercalendar.model.database.Event;
import de.yetanothercalendar.model.database.User;
import de.yetanothercalendar.model.database.helper.DatabaseConnectionManager;

public class CalendarImpl implements Calendar {

	private static final Locale locale = Locale.GERMANY;
	private static final DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(
			locale);

	/** Gerade eingeloggter Benutzer aus der Session */
	private User user;
	/** EventDAO fuer Datenbankzugriff */
	private EventDAO eventDAO;
	private DatabaseConnectionManager manager;
	private MomentCreator momentCreator;
	/** Wrapper fuer Wrap von Event zu CalendarEntry */
	private RecurrentEventToCalendarEntryWrapper wrapperRecurringEvents;
	private CalendarViewCalculation viewCalculation = new CalendarViewCalculation();

	public CalendarImpl(User user) {
		this.user = user;
		this.manager = new DatabaseConnectionManager();
		eventDAO = new EventDAOImpl(new DatabaseConnectionManager());
		momentCreator = new MomentCreator(locale);
		wrapperRecurringEvents = new RecurrentEventToCalendarEntryWrapper(
				locale);
	}

	public Year getEntriesByWeek(int year, int week) {
		// FIXME
		// Jetztigen Calendar auf das aktuelle Monat und Jahr setzen
		java.util.Calendar calendar = new GregorianCalendar(locale);
		// TODO check if this uses the correct week
		calendar.set(java.util.Calendar.DAY_OF_YEAR, 1);
		calendar.set(java.util.Calendar.DAY_OF_WEEK, 1);
		Date time = calendar.getTime();
//		System.out.println(new SimpleDateFormat().format(time));
		// Die beiden Grenzwerte des Monats, in dem gesucht werden soll setzten
		java.util.Calendar firstMomentInWeek = momentCreator
				.createFirstPossibleMomentOfWeekReturningCalendar(calendar);
		java.util.Calendar lastMomentInLast = momentCreator
				.createLastPossibleMomentOfWeekReturningCalendar(calendar);
		// Alle Events in diesem Zeitraum holen
		return createYearInRange(firstMomentInWeek, lastMomentInLast, year);
	}

	public Year getEntriesByMonth(int year, int month) {
		// FIXME
		// Jetztigen Calendar auf das aktuelle Monat und Jahr setzen
		java.util.Calendar calendar = new GregorianCalendar(locale);
		calendar.set(java.util.Calendar.YEAR, year);
		calendar.set(java.util.Calendar.MONTH, month - 1);
		// Die beiden Grenzwerte des Monats, in dem gesucht werden soll setzten
		java.util.Calendar firstMomentInMonth = momentCreator
				.createFirstPossibleMomentOfMonthReturningCalendar(calendar);
		java.util.Calendar lastMomentInMonth = momentCreator
				.createLastPossibleMomentOfMonthReturningCalendar(calendar);
		// Alle Events in diesem Zeitraum holen
		return createYearInRange(firstMomentInMonth, lastMomentInMonth, year);
	}

	public Year getEntriesByYear(int year) {
		// Jetztigen Calendar auf das aktuelle Jahr setzen
		java.util.Calendar calendar = momentCreator
				.createMondayFirstWeekOfYear(year);
		// Die beiden Grenzwerte des Jahres, in dem gesucht werden soll setzten
		java.util.Calendar firstMomentInYear = (java.util.Calendar) calendar
				.clone();
		java.util.Calendar createLastSundayLastWeekOfYear = momentCreator
				.createLastSundayLastWeekOfYear(year);
		java.util.Calendar lastMomentInYear = (java.util.Calendar) createLastSundayLastWeekOfYear
				.clone();
		// Alle Events in diesem Zeitraum holen
		// Kompletter Zeitraum
		return createYearInRange(firstMomentInYear, lastMomentInYear, year);
	}

	private Year createYearInRange(java.util.Calendar firstMomentInMonth,
			java.util.Calendar lastMomentInMonth, int year) {
		Map<java.util.Calendar, List<CalendarEntry>> calendarDayOnCalendarEntryMap = geteventsBetweenDatesAndFillStrucuture(
				firstMomentInMonth, lastMomentInMonth);
		Year result = new Year(year);
		List<Month> monthlist = getMonthList(year);
		for (Month month : monthlist) {
			List<Week> weekList = getWeekListWithDays(year,
					month.getNumber() - 1, calendarDayOnCalendarEntryMap);
			month.setWeeks(weekList);
		}
		result.setMonths(monthlist);
		return result;
	}

	/**
	 * Holt alle evnts des users diese @{link CalendarImpl}s und fuellt diese im
	 * zwischenraum der gegebenen Daten auf.
	 * 
	 * @param startDate
	 * @param endDate
	 * @return eine map die Datum auf Liste von CalendarEntries mappt
	 */
	private Map<java.util.Calendar, List<CalendarEntry>> geteventsBetweenDatesAndFillStrucuture(
			java.util.Calendar startDate, java.util.Calendar endDate) {
		List<Event> events = eventDAO.getEventsFromUserRecurring(user);
		events.addAll(eventDAO.getEventsFromUserNotRecurring(user));
		List<CalendarEntry> allEntries = new ArrayList<CalendarEntry>();
		// FIXME Calendar Entries are Empty
		for (Event event : events) {
			try {
				allEntries.addAll(wrapperRecurringEvents.wrapEventToCalendar(
						event, startDate.getTime(), endDate.getTime()));
			} catch (Exception e) {
				// TODO richtige exception abfangen?
				e.printStackTrace();
			}
		}
		Map<java.util.Calendar, List<CalendarEntry>> calendarDayOnCalendarEntryMap = new HashMap<java.util.Calendar, List<CalendarEntry>>();
		fillCalendarEntryMapForEvent(allEntries, calendarDayOnCalendarEntryMap);
		return calendarDayOnCalendarEntryMap;
	}

	/**
	 * Git eine Liste von Monaten fue das gegebenen Jahr zurueck.
	 * 
	 * @param year
	 *            Das Jahr
	 * @return Eine Liste den Monaten des gegebenen Jahres Mit den vollen
	 *         Randwochen
	 */
	protected List<Month> getMonthList(int year) {
		List<Month> result = new ArrayList<Month>();
		java.util.Calendar calendar = java.util.Calendar.getInstance(locale);
		// auch die andern beiden monate
		int maximumMonthCount = calendar.getMaximum(java.util.Calendar.MONTH);
		for (int monthindex = 0; monthindex <= maximumMonthCount; monthindex++) {
			result.add(new Month(dateFormatSymbols.getMonths()[monthindex],
					monthindex + 1));
		}
		return result;
	}

	/**
	 * Gibt die XML aehnliche Struktur aus {@link Week}s und den dazugehoerigen
	 * {@link Day}s zurueck
	 * 
	 * @param year
	 *            Das Jahr fuer welches die WochenListe erstellt werden soll
	 * @param monthToSearch
	 *            Der Monat fuer welches die WochenListe erstellt werden soll
	 * @return eine Liste aus {@link Week}s (welche Tage enthaelt) im
	 *         angegebenen Zeitraum
	 */
	protected List<Week> getWeekListWithDays(
			int year,
			int monthToSearch,
			Map<java.util.Calendar, List<CalendarEntry>> calendarDayOnCalendarEntryMap) {
		java.util.Calendar calendar = java.util.Calendar.getInstance(locale);
		calendar.clear();
		// calendar.setMinimalDaysInFirstWeek(calendar.getMinimalDaysInFirstWeek()
		// + 1);
		calendar.set(java.util.Calendar.YEAR, year);
		calendar.set(java.util.Calendar.MONTH, monthToSearch);
		calendar.set(java.util.Calendar.DAY_OF_MONTH, 1);
		List<Week> weeks = new ArrayList<Week>();
		int month = calendar.get(java.util.Calendar.MONTH);
		List<Day> weekDays = new ArrayList<Day>();
		int weekOfYear = getWeekNumberMondayToSaturDay(calendar);
		while (calendar.get(java.util.Calendar.MONTH) == month) {
			int currenWeekNumber = getWeekNumberMondayToSaturDay(calendar);
			int dayOfMonth = calendar.get(java.util.Calendar.DAY_OF_MONTH);
			String dayname = dateFormatSymbols.getShortWeekdays()[calendar
					.get(java.util.Calendar.DAY_OF_WEEK)];
			Day day = new Day(dayname, dayOfMonth);
			day = insertCalendarEntriesToDay(calendarDayOnCalendarEntryMap,
					new Pair<java.util.Calendar, Day>(calendar, day));
			if (weekOfYear != currenWeekNumber) {
				Week week = new Week(weekOfYear);
				week.setDays(weekDays);
				weeks.add(week);
				weekDays = new ArrayList<Day>();
				weekOfYear = currenWeekNumber;
			}
			weekDays.add(day);
			calendar.add(java.util.Calendar.DAY_OF_YEAR, 1);
		}
		if (weekDays.size() > 0) {
			Week week = new Week(weekOfYear);
			week.setDays(weekDays);
			weeks.add(week);
		}
		addLastOrFirstWeekDaysOfYear(weeks, monthToSearch, year);
		return weeks;
	}

	private int getWeekNumberMondayToSaturDay(java.util.Calendar calendar) {
		int currentWeek = calendar.get(java.util.Calendar.WEEK_OF_YEAR);
		return currentWeek;
	}

	private void addLastOrFirstWeekDaysOfYear(List<Week> weeks, int month,
			int year) {
		// TODO FIXME: Dringenst werden test cases benötigt
		// Add the days of the first week to the first week
		if (month == java.util.Calendar.JANUARY) {
			java.util.Calendar firstMonday = momentCreator
					.createMondayFirstWeekOfYear(year);
			int pos = 0;
			while (firstMonday.get(java.util.Calendar.YEAR) != year) {
				// get add before the first day of the first week: a new day
				// with the name and
				weeks.get(0)
						.getDays()
						.add(pos,
								new Day(
										dateFormatSymbols.getShortWeekdays()[firstMonday
												.get(java.util.Calendar.DAY_OF_WEEK)],
										firstMonday
												.get(java.util.Calendar.DAY_OF_MONTH)));
				firstMonday.add(java.util.Calendar.DAY_OF_YEAR, 1);
				pos++;
			}
		} else if (month == java.util.Calendar.DECEMBER) {
			java.util.Calendar lastSunday = momentCreator
					.createLastSundayLastWeekOfYear(year);
			int pos = 0;
			while (lastSunday.get(java.util.Calendar.YEAR) != year) {
				// get add before the first day of the first week: a new day
				// with the name and
				weeks.get(weeks.size() - 1)
						.getDays()
						.add(weeks.get(weeks.size() - 1).getDays().size() - pos,
								new Day(
										dateFormatSymbols.getShortWeekdays()[lastSunday
												.get(java.util.Calendar.DAY_OF_WEEK)],
										lastSunday
												.get(java.util.Calendar.DAY_OF_WEEK)));
				lastSunday.add(java.util.Calendar.DAY_OF_YEAR, -1);
				pos++;
			}
		}
	}

	private Day insertCalendarEntriesToDay(
			Map<java.util.Calendar, List<CalendarEntry>> calendarDayOnCalendarEntryMap,
			Pair<java.util.Calendar, Day> calendarWithDay) {
		List<CalendarEntry> list = calendarDayOnCalendarEntryMap
				.get(calendarWithDay.getA());
		if (list != null) {
			calendarWithDay.getB().getCalendarEntries().addAll(list);
		}
		return calendarWithDay.getB();
	}

	private void fillCalendarEntryMapForEvent(
			List<CalendarEntry> wrapEventToCalendar,
			Map<java.util.Calendar, List<CalendarEntry>> calendarDayOnCalendarEntryMap) {
		for (CalendarEntry calendarEntry : wrapEventToCalendar) {
			java.util.Calendar tmpCalendar = new GregorianCalendar(locale);
			// Start time muss laut wrapper immer am Tag des Entries sein -
			// ein CalendarEntry geht ja _nicht_ ueber mehrere Tage!
			tmpCalendar.setTime(calendarEntry.getStartTime());
			// Alle Calendars (die Keys) werden auf die erstmögliche Tageuhrzeit
			// des Tages in dem der CalendarEvent startet, gesetzt.
			momentCreator
					.createFirstPossibleMomentOfDayReturningCalendar(tmpCalendar);
			List<CalendarEntry> calendarEntrylistInMap = calendarDayOnCalendarEntryMap
					.get(tmpCalendar);
			if (calendarEntrylistInMap == null) {
				List<CalendarEntry> newCalendarEntryList = new ArrayList<CalendarEntry>();
				newCalendarEntryList.add(calendarEntry);
				calendarDayOnCalendarEntryMap.put(tmpCalendar,
						newCalendarEntryList);
			} else {
				calendarEntrylistInMap.add(calendarEntry);
			}
		}
	}

	public User getUser() {
		return user;
	}

	public void getUser(User user) {
		this.user = user;
	}

	public EventDAO getEventDAO() {
		return eventDAO;
	}

	public void setEventDAO(EventDAO eventDAO) {
		this.eventDAO = eventDAO;
	}
}
