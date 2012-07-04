package de.yetanothercalendar.model.impl;

import java.util.ArrayList;
import java.util.List;

import de.yetanothercalendar.model.calendar.CalendarEntry;
import de.yetanothercalendar.model.calendar.Day;

public class CalendarViewCalculation {

	public Day analyseColumns(Day pDay) {
		List<CalendarEntry> lCalendarEntries = new ArrayList<CalendarEntry>();
		lCalendarEntries.addAll(pDay.getCalendarEntries());

		// Liste der Calendereinträge nach Startzeitpunkt sortieren
		lCalendarEntries = sortByStartTime(lCalendarEntries);

		// Spaltenanzahl und Position berechnen
		int columns = 0;
		if (lCalendarEntries.get(0) != null) {
			for (CalendarEntry calendarEntry : lCalendarEntries) {
				for (CalendarEntry calendarEntryIterator : lCalendarEntries) {
					// Auf Ungleichheit prüfen
					if (calendarEntry.getStartTime() != calendarEntryIterator
							.getStartTime()) {
						// Wenn Endzeitpunkt des betrachteten Eintrags
						// (calendarEntry) nach dem Startzeitpunkt des
						// Vergleichseintrags (calendarEntryIterator) liegt und
						// zugleich Nebenbedingung (Startzeit des betrachteten
						if ((calendarEntry.getEndTime().after(
								calendarEntryIterator.getStartTime()) && calendarEntry
								.getStartTime().before(
										calendarEntryIterator.getEndTime()))
								|| (calendarEntry.getStartTime().before(
										calendarEntryIterator.getEndTime()) && calendarEntry
										.getEndTime().after(
												calendarEntryIterator
														.getStartTime()))) {
							columns++;
							// Spaltenposition setzen
						}
					} else {
						columns++;
					}
				}
			}

		}
		pDay.setCalendarEntries(lCalendarEntries);
		return pDay;
	}

	public CalendarEntry calculateEntryColumns(CalendarEntry pCalendarEntry) {

		return pCalendarEntry;
	}

	/**
	 * 
	 * Sortiert die Liste der CalenderEntries nach ihrem jeweiligen
	 * Startzeitpunkt. Als Sortierverfahren wird "Sortieren durch Einsetzen"
	 * verwendet. <br>
	 * <br>
	 * Beispielsweise steht an der 1. Stelle der Liste (addresiwert mit 0) der
	 * frühste Startzeitpunkt
	 * 
	 * @param list
	 *            - Liste, bestehend aus Kalendereinträgen
	 * @return - Eine Liste, bestehend aus Kalendereinträgen
	 */
	public List<CalendarEntry> sortByStartTime(List<CalendarEntry> list) {
		for (int i = 1; i < list.size(); i++) {
			CalendarEntry cEBuffer = list.get(i);
			int j = i;
			while (j > 0
					&& list.get(j - 1).getStartTime()
							.after(cEBuffer.getStartTime())) {
				list.set(j, list.get(j - 1));
				j = j - 1;
			}
			list.set(j, cEBuffer);
		}
		return list;
	}
}
