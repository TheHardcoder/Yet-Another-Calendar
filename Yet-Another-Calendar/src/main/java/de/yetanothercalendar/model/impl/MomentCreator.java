package de.yetanothercalendar.model.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MomentCreator {

	private Locale locale;

	protected MomentCreator(Locale locale) {
		this.locale = locale;
	}

	/**
	 * Erstellt den ersten moeglichen Punkt eines events
	 * 
	 * @param date
	 *            Das {@link Date} mit dem gegebenen Tag
	 * @return eine {@link Date} mit dem ersten Moment des Tages ( 00:00:00 )
	 */
	protected Date createFirstPossibleMomentOfDay(Date date) {
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
	protected Date createLastPossibleMomentOfDay(Date date) {
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
	protected Calendar createFirstPossibleMomentOfDayReturningCalendar(
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
	protected Calendar createLastPossibleMomentOfDayReturningCalendar(
			Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.getMaximum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getMaximum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getMaximum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND,
				calendar.getMaximum(Calendar.MILLISECOND));
		return calendar;
	}

	/**
	 * Erstellt den ersten moeglichen Punkt im Jahr
	 * 
	 * @param date
	 *            Das {@link Calendar} mit dem gegebenen Jahr
	 * @return eine {@link Calendar} mit dem ersten Moment des Jahres ( 00:00:00
	 *         )
	 */
	protected Calendar createFirstPossibleMomentOfYearReturningCalendar(
			Calendar calendar) {
		createFirstPossibleMomentOfDayReturningCalendar(calendar);
		calendar.set(Calendar.DAY_OF_YEAR, 0);
		return calendar;
	}

	/**
	 * Erstellt den letzt moeglichen Punkt im Jahr
	 * 
	 * @param date
	 *            Das {@link Calendar} mit dem gegebenen Jahr
	 * @return eine {@link Calendar} mit dem letzten Moment des Jahres (
	 *         00:00:00 )
	 */
	protected Calendar createLastPossibleMomentOfYearReturningCalendar(
			Calendar calendar) {
		createLastPossibleMomentOfDayReturningCalendar(calendar);
		calendar.set(Calendar.DAY_OF_YEAR,
				calendar.getMaximum(Calendar.DAY_OF_YEAR));
		return calendar;
	}

	protected boolean isSameDay(Calendar calendarStartDay,
			Calendar calendarEndDay) {
		return calendarStartDay.get(Calendar.YEAR) == calendarEndDay
				.get(Calendar.YEAR)
				&& calendarStartDay.get(Calendar.DAY_OF_YEAR) == calendarEndDay
						.get(Calendar.DAY_OF_YEAR);
	}

	protected boolean isOneDayDifference(Calendar calendarStartDay,
			Calendar calendarEndDay) {
		return ((calendarStartDay.get(Calendar.YEAR) == calendarEndDay
				.get(Calendar.YEAR) && calendarStartDay
				.get(Calendar.DAY_OF_YEAR) == calendarEndDay
				.get(Calendar.DAY_OF_YEAR) - 1));
	}
}
