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

		// Basisspalte auf 1 setzen
		int columns = 1;
		if (lCalendarEntries != null && lCalendarEntries.size() > 0) {
			// Ersten Eintrag des Tages der ersten Spaöte zuweisen
			lCalendarEntries.get(0).setColumn(columns);

			for (int i = 1; i < lCalendarEntries.size(); i++) {
				CalendarEntry activeEntry = lCalendarEntries.get(i);

				for (CalendarEntry comparedEntry : lCalendarEntries) {
					int checkColumn = 1;
					boolean stay = true;
					boolean entryOrdered = false;
					while (!entryOrdered) {
						if (comparedEntry.getColumn() != 0) {
							if (activeEntry.getStartTime().after(
									comparedEntry.getEndTime())) {
								for (CalendarEntry columnEntry : lCalendarEntries) {
									if (comparedEntry.getColumn() == columnEntry
											.getColumn()) {
										if (comparedEntry.getStartTime().after(
												columnEntry.getEndTime())) {
										} else {
											stay = false;
										}
									}
								}
							}
						}
						if (stay) {
							activeEntry.setColumn(columns);
							entryOrdered = true;
						} else {
							checkColumn++;
						}
					}

				}
			}
		}
		pDay.setColumnCount(columns);
		pDay.setCalendarEntries(lCalendarEntries);

		return pDay;
	}

	// private CalendarEntry checkOverlapping(CalendarEntry cEntry,
	// List<CalendarEntry> lCalendarEntries) {
	// if (lCalendarEntries.size() == 1) {
	// CalendarEntry cEntryComperator = lCalendarEntries.get(0);
	// if ((cEntry.getEndTime().after(cEntryComperator.getStartTime()) && cEntry
	// .getStartTime().before(cEntryComperator.getEndTime()))
	// || (cEntry.getStartTime().before(
	// cEntryComperator.getEndTime()) && cEntry
	// .getEndTime()
	// .after(cEntryComperator.getStartTime()))) {
	// return cEntryComperator;
	// }
	// } else {
	// checkOverlapping(cEntry, lCalendarEntries);
	// }
	// // Wenn es keine Überscheidung gibt wird null zurückgegeben
	// return null;
	// }

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

// Dumbing Area for useless code

//
// // Äußere Schleife, die für jeden Kalendereintrag evtl. Überscheidungen
// // prüft
// List<CalendarEntry> lComparableCalendarEntries = lCalendarEntries;
// CalendarEntry comparedCalendarEntry;

//
// // Wurzelkalendareintag momentaner Basisspalte zuweisen
// rootCalendarEntry.setColumn(columns);
//
// // Vergleichsobjekt festlegen
// lComparableCalendarEntries.remove(lComparableCalendarEntries
// .indexOf(rootCalendarEntry));
// if (lCalendarEntries.get(
// lCalendarEntries.indexOf(rootCalendarEntry) + 1)
// .getColumn() == 0) {
// comparedCalendarEntry = lCalendarEntries.get(lCalendarEntries
// .indexOf(rootCalendarEntry) + 1);
// } else {
// continue;
// }
//
// // Überprüfung auf nahtlosen Übergang
// if (rootCalendarEntry.getEndTime().equals(
// comparedCalendarEntry.getStartTime())) {
// comparedCalendarEntry.setColumn(columns);
// continue;
// }
//
// int overlapColumn = 1;
// // Innere Schleife, mit dem vergleichenden Kalendareintrag
// while (rootCalendarEntry.getEndTime().after(
// comparedCalendarEntry.getStartTime())) {
//
// // Überprüfung auf Überschneidung
// // Wenn Endzeitpunkt des Wurzeleintrags
// // (rootCalendarEntry) nach dem Startzeitpunkt des
// // Vergleichseintrags (compareCalendarEntry) liegt
// if (rootCalendarEntry.getEndTime().after(
//
// comparedCalendarEntry.getStartTime())) {
// columns++;
//
// comparedCalendarEntry.setColumn(columns);
// }
//
// if ((rootCalendarEntry.getStartTime().before(
//
// comparedCalendarEntry.getEndTime()) && rootCalendarEntry
// .getEndTime().after(
// comparedCalendarEntry.getStartTime()))) {
//
// }
// }

// if (lCalendarEntries.get(0) != null) {
// for (CalendarEntry calendarEntry : lCalendarEntries) {
// List<CalendarEntry> lCalendarEntriesDecremented =
// lCalendarEntries;
// lCalendarEntriesDecremented.remove(calendarEntry);
// CalendarEntry equalCalendarEntry;
// if ((equalCalendarEntry = checkOverlapping(calendarEntry,
// lCalendarEntriesDecremented)) != null) {
// calendarEntry.setColumn(columns);
// columns++;
// equalCalendarEntry.setColumn(columns);
// }
// }
// }

