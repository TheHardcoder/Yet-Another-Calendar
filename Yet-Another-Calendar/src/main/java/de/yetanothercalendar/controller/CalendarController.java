package de.yetanothercalendar.controller;

import de.yetanothercalendar.model.calendar.Year;
import de.yetanothercalendar.model.database.User;

/**
 * Gibt {@link Year}s zurück, einem bestimmten Zeitraum entsprechen
 */
public interface CalendarController {
	/**
	 * @return den {@link User}, welcher gerade eingeloggt ist
	 */
	User getUser();

	/**
	 * Gibt ein {@link Year} zurück, welches die Strukture von Monaten, Wochen,
	 * Tagen und letztendlich dem {@link CalendarEntry} enthält
	 * 
	 * @param year
	 *            Das Jahr für das das {@link Year} generiert werden soll
	 * @param month
	 *            Der Monat für das das {@link Year} generiert werden soll
	 * @param week
	 *            Die Woche für das das {@link Year} generiert werden soll
	 * @return Ein {@link Year} welches die Strukture mit {@link CalendarEntriy}
	 *         s enthält
	 */
	Year getEntriesByWeek(int year, int month, int week);

	/**
	 * Gibt ein {@link Year} zurück, welches die Strukture von Monaten, Wochen,
	 * Tagen und letztendlich dem {@link CalendarEntry} enthält
	 * 
	 * @param year
	 *            Das Jahr für das das {@link Year} generiert werden soll
	 * @param month
	 *            Der Monat für das das {@link Year} generiert werden soll
	 * @return Ein {@link Year} welches die Strukture mit {@link CalendarEntriy}
	 *         s enthält
	 */
	Year getEntriesByMonth(int year, int month);

	/**
	 * Gibt ein {@link Year} zurück, welches die Strukture von Monaten, Wochen,
	 * Tagen und letztendlich dem {@link CalendarEntry} enthält
	 * 
	 * @param year
	 *            Das Jahr für das das {@link Year} generiert werden soll
	 * @return Ein {@link Year} welches die Strukture mit {@link CalendarEntriy}
	 *         s enthält
	 */
	Year getEntriesByYear(int year);
}
