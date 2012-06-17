package de.yetanothercalendar.model.impl;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
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
	/** Creator Klasse zum Erstellen von Zeitrandwerten */
	private MomentCreator momentCreator;
	/** Wrapper fuer Wrap von Event zu CalendarEntry */
	private EventToCalendarEntryWrapper wrapper;

	public CalendarImpl(User user) {
		this.user = user;
		this.manager = new DatabaseConnectionManager();
		wrapper = new EventToCalendarEntryWrapper(locale);
		momentCreator = new MomentCreator(locale);
		eventDAO = new EventDAOImpl(new DatabaseConnectionManager());
	}

	public Year getEntriesByWeek(int year, int month, int week) {
		// Jetztigen Calendar auf das aktuelle Monat und Jahr setzen
		java.util.Calendar calendar = new GregorianCalendar(locale);
		calendar.set(java.util.Calendar.YEAR, year);
		calendar.set(java.util.Calendar.MONTH, month - 1);
		// TODO check if this uses the correct week
		calendar.set(java.util.Calendar.DAY_OF_WEEK_IN_MONTH, week + 1);
		// Die beiden Grenzwerte des Monats, in dem gesucht werden soll setzten
		java.util.Calendar firstMomentInWeek = momentCreator
				.createFirstPossibleMomentOfWeekReturningCalendar(calendar);
		java.util.Calendar lastMomentInLast = momentCreator
				.createLastPossibleMomentOfWeekReturningCalendar(calendar);
		// Alle Events in diesem Zeitraum holen
		return createYearInRange(firstMomentInWeek, lastMomentInLast, year);
	}

	public Year getEntriesByMonth(int year, int month) {
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
		java.util.Calendar calendar = new GregorianCalendar(locale);
		calendar.set(java.util.Calendar.YEAR, year);
		// Die beiden Grenzwerte des Jahres, in dem gesucht werden soll setzten
		java.util.Calendar firstMomentInYear = momentCreator
				.createFirstPossibleMomentOfYearReturningCalendar(calendar);
		java.util.Calendar lastMomentInYear = momentCreator
				.createLastPossibleMomentOfYearReturningCalendar(calendar);
		// Alle Events in diesem Zeitraum holen
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
		List<Event> eventBetweenDates = eventDAO.getEventBetweenDates(user,
				startDate.getTime(), endDate.getTime());
		Map<java.util.Calendar, List<CalendarEntry>> calendarDayOnCalendarEntryMap = new HashMap<java.util.Calendar, List<CalendarEntry>>();
		for (Event event : eventBetweenDates) {
			List<CalendarEntry> wrapEventToCalendar = wrapper
					.wrapEventToCalendar(event);
			// fill map for use insertion in CalendaEntry later. Benutzt
			// Referenzen der Liste und der Map
			fillCalendarEntryMapForEvent(wrapEventToCalendar,
					calendarDayOnCalendarEntryMap);
		}
		return calendarDayOnCalendarEntryMap;
	}

	/**
	 * Git eine Liste von Monaten fue das gegebenen Jahr zurueck.
	 * 
	 * @param year
	 *            Das Jahr
	 * @return Eine Liste den Monaten des gegebenen Jahres
	 */
	protected List<Month> getMonthList(int year) {
		List<Month> result = new ArrayList<Month>();
		java.util.Calendar calendar = java.util.Calendar.getInstance(locale);
		calendar.clear();
		calendar.set(java.util.Calendar.YEAR, year);
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
		calendar.set(java.util.Calendar.YEAR, year);
		calendar.set(java.util.Calendar.MONTH, monthToSearch);
		List<Week> weeks = new ArrayList<Week>();
		// Den aktuellen monat sichern
		int month = calendar.get(java.util.Calendar.MONTH);
		// Der Tag des Kalenders wird auf den ersten des monats gesetzt
		calendar.set(java.util.Calendar.DAY_OF_MONTH, 1);
		// Zwischenvariablen initialisieren
		List<Day> weekDays = new ArrayList<Day>();
		int weekOfYear = calendar.get(java.util.Calendar.WEEK_OF_YEAR);
		// Fussgesteuertes while(true) funktioniert nicht, da bei
		// nichteinhaltung der bedingung (nächste Monat erreicht) noch die
		// "resttage" in der zwischenvariable weekDays zum Monat (als woche
		// "verpackt") hinzugefügt werden müssen.
		int currentWeek = -1;
		// Zaehler fuer number der Woche im XML
		int weeknumer = 1;
		while (calendar.get(java.util.Calendar.MONTH) == month) {
			// Der aktuelle monatstag
			int dayOfMonth = calendar.get(java.util.Calendar.DAY_OF_MONTH);
			// Der name des aktuellen tages
			String dayname = dateFormatSymbols.getWeekdays()[calendar
					.get(java.util.Calendar.DAY_OF_WEEK)];
			Day day = new Day(dayname, dayOfMonth);
			// Die Woche des jetztigen Tags im calendar.
			currentWeek = calendar.get(java.util.Calendar.WEEK_OF_YEAR);
			// Wenn die nächste woche erreicht wird, werden die temporaer
			// abgespeicherten tage in weekDays zur Woche zusammengefasst und im
			// Monat gespeichert.
			if (!(weekOfYear == currentWeek)) {
				Week week = new Week(weeknumer);
				week.setDays(weekDays);
				weeks.add(week);
				// Zurücksetzung der attribute
				weekDays = new ArrayList<Day>();
				weekOfYear = currentWeek;
				weeknumer++;
			}
			insertCalendarEntriesToDay(calendarDayOnCalendarEntryMap,
					new Pair<java.util.Calendar, Day>(calendar, day));
			weekDays.add(day);
			calendar.add(java.util.Calendar.DAY_OF_YEAR, 1);
		}
		// Ueberpruefung, ob wir noch im Monat sind.
		if (weekDays.size() > 0) {
			Week week = new Week(weeknumer);
			week.setDays(weekDays);
			weeks.add(week);
		}
		return weeks;
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
