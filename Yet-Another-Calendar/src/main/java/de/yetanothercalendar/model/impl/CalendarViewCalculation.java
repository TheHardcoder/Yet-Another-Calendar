package de.yetanothercalendar.model.impl;

import java.util.ArrayList;
import java.util.List;

import de.yetanothercalendar.model.calendar.CalendarEntry;
import de.yetanothercalendar.model.calendar.Day;

/**
 * Diese Klasse dient zu diversen Berechnungen für die Darstellung im FrontEnd.
 * Um komplexe Berechnungen im Frontend zu vermeiden, werden die Berechnungen
 * schon im BackEnd erledigt
 * 
 * @author Lukas
 * 
 */
public class CalendarViewCalculation {

	/**
	 * Berechnet die maximale Anzahl der Spalten pro Tag und setzt bei jedem
	 * Kalendereintrag noch die dazugehörige Spalte
	 * 
	 * @param pDay
	 *            - Der Tag, an dem die Berechnungen ausgeführt werden sollen
	 * @return - Der überarbeitete Tag mit den berechneten Attributen
	 */
	public Day initializeColumns(Day pDay) {
		List<CalendarEntry> lCalendarEntries = new ArrayList<CalendarEntry>();
		lCalendarEntries.addAll(pDay.getCalendarEntries());
		List<CalendarEntry> lBufferEntries = lCalendarEntries;
		List<CalendarEntry> lRemovedEntries = new ArrayList<CalendarEntry>();

		// Liste der Calendereinträge nach Startzeitpunkt sortieren
		lCalendarEntries = sortByStartTime(lCalendarEntries);
		int i = 0;
		int column = 0;
		CalendarEntry eActive;
		CalendarEntry ePrivious = lBufferEntries.remove(0);
		ePrivious.setColumn(column);
		lRemovedEntries.add(ePrivious);
		while (!lBufferEntries.isEmpty()) {
			eActive = lBufferEntries.get(i);
			if (checkOnOverlapping(ePrivious, eActive)) {
				if (i < lBufferEntries.size() - 1) {
					i++;
				} else {
					column++;
					i = 0;
					ePrivious = lBufferEntries.remove(i);
					ePrivious.setColumn(column);
					lRemovedEntries.add(ePrivious);
				}
			} else {
				ePrivious = lBufferEntries.remove(i);
				ePrivious.setColumn(column);
				lRemovedEntries.add(ePrivious);
			}
		}
		lCalendarEntries.addAll(lRemovedEntries);
		pDay.setCalendarEntries(lCalendarEntries);
		pDay.setColumnCount(column + 1);
		return pDay;
	}

	/**
	 * Prüft ob der ausgewählte Kalendereintrag (cActive) hinter dem vorherigen
	 * Eintrag (cPrivious) liegt
	 * 
	 * @param cPrivious
	 *            - vorheriger Eintrag
	 * @param cActive
	 *            - der zu vergleichende Eintrag
	 * @return Falls sie sich überscheiden "true" <br>
	 *         wenn sie aneinander anschließen oder wenn der Ausgewählte Eintrag
	 *         hinter dem vorherigen liegt "false"
	 */
	private boolean checkOnOverlapping(CalendarEntry cPrivious,
			CalendarEntry cActive) {
		boolean overlap = false;
		if (cActive.getStartTime().after(cPrivious.getEndTime())) {
			overlap = false;
		} else if (cActive.getStartTime().before(cPrivious.getEndTime())) {
			overlap = true;
		}
		return overlap;
	}

	/**
	 * Checkt ob sich die übergebenen Kalendereinträge überschneiden
	 * 
	 * @param firstEntry
	 *            - ein Kalendereintrag mit früherer Startzeit als secondentry
	 * @param secondEntry
	 *            - ein Kalendareintrag mit späterer Startzeit als firstEntry
	 * @return - true wenn sie sich nicht überscheiden
	 */
	@SuppressWarnings("unused")
	private boolean checkOverlapping(CalendarEntry activeEntry,
			CalendarEntry comparedEntry) {
		boolean overlap = false;
		// Aktiver Eintag vollständig vor comparedEntry
		if (activeEntry.getStartTime().before(comparedEntry.getStartTime())
				&& activeEntry.getEndTime()
						.before(comparedEntry.getStartTime())) {
			overlap = false;
		}
		// Aktiver Eintrag vollständig nach comparedEntry
		else if (activeEntry.getStartTime().after(comparedEntry.getStartTime())
				&& activeEntry.getStartTime().after(comparedEntry.getEndTime())) {
			overlap = false;
		}
		// Überschneidung im Falle Startzeit des comparedEntry vor Startzeit des
		// ActiveEntry
		else if (activeEntry.getStartTime().after(comparedEntry.getStartTime())
				&& activeEntry.getStartTime()
						.before(comparedEntry.getEndTime())) {
			overlap = true;
		}
		// Überschneidung im Falle Startzeit des comparedEntry nach Startzeit
		// des ActiveEntry
		else if (comparedEntry.getStartTime().after(activeEntry.getStartTime())
				&& comparedEntry.getStartTime()
						.before(activeEntry.getEndTime())) {
			overlap = true;
		}
		return overlap;
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

// Dumping Area
// /**
// * Zu komplizierter Algorythmus
// *
// * @param pDay
// * @return
// */
// public Day analyseColumns(Day pDay) {
// List<CalendarEntry> lCalendarEntries = new ArrayList<CalendarEntry>();
// lCalendarEntries.addAll(pDay.getCalendarEntries());
//
// // Liste der Calendereinträge nach Startzeitpunkt sortieren
// lCalendarEntries = sortByStartTime(lCalendarEntries);
//
// // Basisspalte auf 1 setzen
// int columns = 1;
// if (lCalendarEntries != null && lCalendarEntries.size() > 0) {
// // Ersten Eintrag des Tages der ersten Spaöte zuweisen
// lCalendarEntries.get(0).setColumn(columns);
//
// for (int i = 1; i < lCalendarEntries.size(); i++) {
// CalendarEntry activeEntry = lCalendarEntries.get(i);
// int checkColumn = 1;
// boolean stay = true;
// boolean placeFound = false;
//
// // Prüfe solange, bis der Eintrag eingeordnet wurde
// while (!placeFound) {
//
// // Prüfe mit jedem Eintrag
// if (stay) {
// for (CalendarEntry comparedEntry : lCalendarEntries) {
// if (!checkOverlapping(activeEntry, comparedEntry)) {
// continue;
// } else {
// stay = false;
// break;
// }
// }
// placeFound = stay;
// } else if (stay == false && placeFound == false) {
// checkColumn++;
// if (checkColumn > columns) {
// columns++;
// }
// }
// }
//
// activeEntry.setColumn(checkColumn);
// }
// }
// pDay.setColumnCount(columns);
// pDay.setCalendarEntries(lCalendarEntries);
// return pDay;
// }