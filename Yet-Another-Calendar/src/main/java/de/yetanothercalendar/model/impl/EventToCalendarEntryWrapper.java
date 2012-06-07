package de.yetanothercalendar.model.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import de.yetanothercalendar.model.calendar.CalendarEntry;
import de.yetanothercalendar.model.database.Event;

/**
 * Wrappt einen gegebenen {@link Event} zu einem {@link CalendarEntry}.
 * 
 */
public class EventToCalendarEntryWrapper {

	private Locale locale;

	public EventToCalendarEntryWrapper(Locale locale) {
		super();
		this.locale = locale;
	}

	/**
	 * Wrappt einen gegebenen {@link Event} zu gegebenen {@link CalendarEntry}s.
	 * 
	 * @param event
	 *            Der event, der gerappt werden soll
	 * @return a List of {@link CalendarEntry}s.
	 */
	public List<CalendarEntry> wrapEventToCalendar(Event event) {
		List<CalendarEntry> result = new ArrayList<CalendarEntry>();
		Date startdate = event.getDtstart();
		Date enddate = event.getDtend();
		if (startdate.after(enddate)) {
			throw new RuntimeException("Startdatum ist hinter enddatum");
		}
		Calendar calendarStartDay = Calendar.getInstance(locale);
		calendarStartDay.setTime(startdate);
		Calendar calendarEndDay = Calendar.getInstance(locale);
		calendarEndDay.setTime(enddate);
		// Untersuche, ob das Start und das Enddatum des Termins am gleichen Tag
		// liegen.
		boolean sameDay = calendarStartDay.get(Calendar.YEAR) == calendarEndDay
				.get(Calendar.YEAR)
				&& calendarStartDay.get(Calendar.DAY_OF_YEAR) == calendarEndDay
						.get(Calendar.DAY_OF_YEAR);
		// Untersuche ob die Tage einen Tag abstand haben (daher das enddatum am
		// naechsten Tag liegt, nicht ob die Differenz 24 Stunden betraegt
		boolean oneDayDifference = ((calendarStartDay.get(Calendar.YEAR) == calendarEndDay
				.get(Calendar.YEAR)
		// TODO FIXME Error here with 31.12....
		&& calendarStartDay.get(Calendar.DAY_OF_YEAR) == calendarEndDay
				.get(Calendar.DAY_OF_YEAR) - 1));

		if (sameDay) {
			result.add(createCalendarEntryFromEvent(event));
		} else {
			CalendarEntry firstEntry = createCalendarEntryFromEvent(event,
					startdate, createLastPossibleMomentOfDay(startdate));
			CalendarEntry lastEntry = createCalendarEntryFromEvent(event,
					createFirstPossibleMomentOfDay(enddate), enddate);
			List<CalendarEntry> fullDayEntries = new ArrayList<CalendarEntry>();
			// Falls nicht ein tag dazwischen liegt (und die Termine auch nicht
			// am selben tag sind) muss eine CalendarEntry fuer den gesamten tag
			// erstellt werden.
			if (!oneDayDifference) {
				// Erster Moment der zu fuellenden "vollen" tagen (also von 0:00
				// bis 23:59)
				Calendar startFullDay = (Calendar) calendarStartDay.clone();
				startFullDay.add(Calendar.DAY_OF_YEAR, 1);
				startFullDay = createFirstPossibleMomentOfDayReturningCalendar(startFullDay);
				// Letzter Moment der zu fuellenden "vollen" tagen (also von
				// 0:00 bis 23:59)
				Calendar endFullDay = (Calendar) calendarEndDay.clone();
				endFullDay.add(Calendar.DAY_OF_YEAR, -1);
				// Liste mit Einträgen über den Gesamten Tag holen
				fullDayEntries = fillFullDaysWithCalendarEntries(event,
						startFullDay, endFullDay);
			}
			// Zuerst den ersten "halben" Entry
			result.add(firstEntry);
			// Dazwischen die vollen, ganztaegigen Entries
			result.addAll(fullDayEntries);
			// Zuletzt den letzten "halben" Entry
			result.add(lastEntry);
		}
		return result;
	}

	/**
	 * Gibt eine Liste mit "gefuelleten" Tagen aus {@link CalendarEntry}s
	 * zurueck
	 * 
	 * @param event
	 *            der event fuer den die {@link CalendarEntry}s erstellt werden.
	 * @param currentdate
	 *            das startdatum (erster Moment des ersten zu fuellenden Tages)
	 * @param enddate
	 *            das enddateum (letzter Moment des ersten zu fuellenden Tages)
	 * @return
	 */
	private List<CalendarEntry> fillFullDaysWithCalendarEntries(Event event,
			Calendar currentdate, Calendar enddate) {
		List<CalendarEntry> result = new ArrayList<CalendarEntry>();
		// FIXME: Bugfix: Last date is forgotten
		enddate.add(Calendar.DAY_OF_YEAR, 1);
		while (currentdate.before(enddate)) {
			result.add(createCalendarEntryFromEvent(
					event,
					createFirstPossibleMomentOfDayReturningCalendar(currentdate)
							.getTime(),
					createLastPossibleMomentOfDayReturningCalendar(currentdate)
							.getTime()));
			currentdate.add(Calendar.DAY_OF_YEAR, 1);
		}
		return result;
	}

	/**
	 * Erstellt einen {@link CalendarEntry} vom gegebenen Start und gegebenem
	 * Enddatum
	 * 
	 * @param event
	 *            das even fuer den der CalendarEntry erstellt wird
	 * @param startDate
	 *            das startdatum des entries
	 * @param endDate
	 *            das enddatum des entries
	 * @return einen {@link CalendarEntry} aus den gegebenen parametern
	 */
	private CalendarEntry createCalendarEntryFromEvent(Event event,
			Date startDate, Date endDate) {
		Date duration = new Date(event.getDuration());
		return new CalendarEntry(new Long(event.getId()), event.getPriority(),
				event.getColor(), event.getSummary(), startDate, endDate,
				duration, event.getLocation(), event.getDescription(),
				event.getCreated(), event.getLastmod(), event.getComment(),
				event.getCategories());
	}

	/**
	 * Erstellt einen {@link CalendarEntry} aus einem Event
	 * 
	 * @param event
	 *            das even fuer den der CalendarEntry erstellt wird
	 * @return einen {@link CalendarEntry} aus dem gegebenen {@link Event]
	 */
	private CalendarEntry createCalendarEntryFromEvent(Event event) {
		Date duration = new Date(event.getDuration());
		return new CalendarEntry(new Long(event.getId()), event.getPriority(),
				event.getColor(), event.getSummary(), event.getDtstart(),
				event.getDtend(), duration, event.getLocation(),
				event.getDescription(), event.getCreated(), event.getLastmod(),
				event.getComment(), event.getCategories());
	}

	/**
	 * Erstellt den ersten moeglichen Punkt eines events
	 * 
	 * @param date
	 *            Das {@link Date} mit dem gegebenen Tag
	 * @return eine {@link Date} mit dem ersten Moment des Tages ( 00:00:00 )
	 */
	private Date createFirstPossibleMomentOfDay(Date date) {
		Calendar calendar = new GregorianCalendar(locale);
		calendar.setTime(date);
		createFirstPossibleMomentOfDayReturningCalendar(calendar);
		return calendar.getTime();
	}

	/**
	 * Erstellt den letzten moeglichen Punkt eines events
	 * 
	 * @param date
	 *            Das {@link Date} mit dem gegebenen Tag
	 * @return eine {@link Date} mit dem letzten Moment des Tages ( 23:59:59 )
	 */
	private Date createLastPossibleMomentOfDay(Date date) {
		Calendar calendar = new GregorianCalendar(locale);
		calendar.setTime(date);
		createLastPossibleMomentOfDayReturningCalendar(calendar);
		return calendar.getTime();
	}

	/**
	 * Erstellt den ersten moeglichen Punkt eines events
	 * 
	 * @param date
	 *            Das {@link Calendar} mit dem gegebenen Tag
	 * @return eine {@link Calendar} mit dem ersten Moment des Tages ( 00:00:00
	 *         )
	 */
	private Calendar createFirstPossibleMomentOfDayReturningCalendar(
			Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar;
	}

	/**
	 * Erstellt den ersten letzt Punkt eines events
	 * 
	 * @param date
	 *            Das {@link Calendar} mit dem gegebenen Tag
	 * @return eine {@link Calendar} mit dem letzten Moment des Tages ( 23:59:59
	 *         )
	 */
	private Calendar createLastPossibleMomentOfDayReturningCalendar(
			Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.getMaximum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getMaximum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getMaximum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND,
				calendar.getMaximum(Calendar.MILLISECOND));
		return calendar;
	}
}
