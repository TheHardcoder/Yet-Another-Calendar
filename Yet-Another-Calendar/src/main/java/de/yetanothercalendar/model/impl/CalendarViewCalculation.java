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
				List<CalendarEntry> lCalendarEntriesDecremented = lCalendarEntries;
				lCalendarEntriesDecremented.remove(calendarEntry);
				CalendarEntry equalCalendarEntry;
				if ((equalCalendarEntry = checkOverlapping(calendarEntry,
						lCalendarEntriesDecremented)) != null) {
					calendarEntry.setColumn(columns);
					columns++;
					equalCalendarEntry.setColumn(columns);
				}

				// for (CalendarEntry calendarEntry : lCalendarEntries) {
				//
				// for (CalendarEntry calendarEntryComperator :
				// lCalendarEntries) {
				// // Auf Gleichheit prüfen
				// if (calendarEntry.getStartTime().equals(
				// calendarEntryComperator.getStartTime())) {
				// addColumnNumber(calendarEntry, columns);
				// columns++;
				// addColumnNumber(calendarEntryComperator, columns);
				//
				// // Wenn Endzeitpunkt des betrachteten Eintrags
				// // (calendarEntry) nach dem Startzeitpunkt des
				// // Vergleichseintrags (calendarEntryIterator) liegt und
				// // zugleich Nebenbedingung (Startzeit des betrachteten
				// if ((calendarEntry.getEndTime().after(
				// calendarEntryComperator.getStartTime()) && calendarEntry
				// .getStartTime().before(
				// calendarEntryComperator.getEndTime()))
				// || (calendarEntry.getStartTime().before(
				// calendarEntryComperator.getEndTime()) && calendarEntry
				// .getEndTime().after(
				// calendarEntryComperator
				// .getStartTime()))) {
				// columns++;
				// // Spaltenposition setzen
				// }
				// } else {
				// columns++;
				// }
				// }
				// }
			}
			pDay.setColumnCount(columns);
			pDay.setCalendarEntries(lCalendarEntries);

		}
		return pDay;
	}

	private CalendarEntry checkOverlapping(CalendarEntry cEntry,
			List<CalendarEntry> lCalendarEntries) {
		if (lCalendarEntries.size() == 1) {
			CalendarEntry cEntryComperator = lCalendarEntries.get(0);
			if ((cEntry.getEndTime().after(cEntryComperator.getStartTime()) && cEntry
					.getStartTime().before(cEntryComperator.getEndTime()))
					|| (cEntry.getStartTime().before(
							cEntryComperator.getEndTime()) && cEntry
							.getEndTime()
							.after(cEntryComperator.getStartTime()))) {
				return cEntryComperator;
			}
		} else {
			CalendarEntry cEntryRecurse = lCalendarEntries
					.remove(lCalendarEntries.size() - 1);
			return checkOverlapping(cEntryRecurse, lCalendarEntries);

		}
		// Wenn es keine Überscheidung gibt wird null zurückgegeben
		return null;
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
