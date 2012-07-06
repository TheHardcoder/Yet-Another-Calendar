package de.yetanothercalendar.model.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
	private MomentCreator momentCreator;

	public EventToCalendarEntryWrapper(Locale locale) {
		super();
		this.locale = locale;
		momentCreator = new MomentCreator(locale);
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
			throw new RuntimeException("Startdatum ist hinter enddatum, Event(" +event.getId() + "): " + event.getSummary());
		}
		Calendar calendarStartDay = Calendar.getInstance(locale);
		calendarStartDay.setTime(startdate);
		Calendar calendarEndDay = Calendar.getInstance(locale);
		calendarEndDay.setTime(enddate);
		// Untersuche, ob das Start und das Enddatum des Termins am gleichen Tag
		// liegen.
		boolean sameDay = momentCreator.isSameDay(calendarStartDay,
				calendarEndDay);
		// Untersuche ob die Tage einen Tag abstand haben (daher das enddatum am
		// naechsten Tag liegt, nicht ob die Differenz 24 Stunden betraegt
		boolean oneDayDifference = momentCreator.isOneDayDifference(
				calendarStartDay, calendarEndDay);
		if (sameDay) {
			result.add(createCalendarEntryFromEvent(event));
		} else {
			CalendarEntry firstEntry = createCalendarEntryFromEvent(event,
					startdate,
					momentCreator.createLastPossibleMomentOfDay(startdate));
			CalendarEntry lastEntry = createCalendarEntryFromEvent(event,
					momentCreator.createFirstPossibleMomentOfDay(enddate),
					enddate);
			List<CalendarEntry> fullDayEntries = new ArrayList<CalendarEntry>();
			// Falls nicht ein tag dazwischen liegt (und die Termine auch nicht
			// am selben tag sind) muss eine CalendarEntry fuer den gesamten tag
			// erstellt werden.
			if (!oneDayDifference) {
				// Erster Moment der zu fuellenden "vollen" tagen (also von 0:00
				// bis 23:59)
				Calendar startFullDay = (Calendar) calendarStartDay.clone();
				startFullDay.add(Calendar.DAY_OF_YEAR, 1);
				startFullDay = momentCreator
						.createFirstPossibleMomentOfDayReturningCalendar(startFullDay);
				// Letzter Moment der zu fuellenden "vollen" tagen (also von
				// 0:00 bis 23:59)
				Calendar endFullDay = (Calendar) calendarEndDay.clone();
				endFullDay = momentCreator
						.createLastPossibleMomentOfDayReturningCalendar(endFullDay);
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
		while (currentdate.before(enddate)) {
			result.add(createCalendarEntryFromEvent(
					event,
					momentCreator
							.createFirstPossibleMomentOfDayReturningCalendar(
									currentdate).getTime(),
					momentCreator
							.createLastPossibleMomentOfDayReturningCalendar(
									currentdate).getTime()));
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
		// TODO rRule noch richtig hinzufügen
		return new CalendarEntry(new Long(event.getId()), event.getPriority(),
				event.getColor(), event.getSummary(), startDate, endDate,
				duration, event.getLocation(), event.getDescription(),
				event.getCreated(), event.getLastmod(), event.getComment(),
				event.getCategories(), null, event.getDtstart(),
				event.getDtend());
	}

	/**
	 * Erstellt einen {@link CalendarEntry} aus einem Event
	 * 
	 * @param event
	 *            das event fuer den der CalendarEntry erstellt wird
	 * @return einen {@link CalendarEntry} aus dem gegebenen {@link Event]
	 */
	private CalendarEntry createCalendarEntryFromEvent(Event event) {
		Date duration = new Date(event.getDuration());
		// TODO rRule noch richtig hinzufügen
		return new CalendarEntry(new Long(event.getId()), event.getPriority(),
				event.getColor(), event.getSummary(), event.getDtstart(),
				event.getDtend(), duration, event.getLocation(),
				event.getDescription(), event.getCreated(), event.getLastmod(),
				event.getComment(), event.getCategories(), event.getRrule(),
				event.getDtstart(), event.getDtend());
	}
}
