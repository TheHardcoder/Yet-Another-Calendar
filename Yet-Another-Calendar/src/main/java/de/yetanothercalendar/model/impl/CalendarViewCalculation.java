package de.yetanothercalendar.model.impl;

import java.util.ArrayList;
import java.util.List;

import de.yetanothercalendar.model.calendar.CalendarEntry;
import de.yetanothercalendar.model.calendar.Day;

public class CalendarViewCalculation {

	public void analyseColumns(Day pDay) {
		List<CalendarEntry> lCalendarEntries = new ArrayList<CalendarEntry>();
		lCalendarEntries.addAll(pDay.getCalendarEntries());

		// Liste der Calendereinträge nach Startzeitpunkt sortieren

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
	}
}
