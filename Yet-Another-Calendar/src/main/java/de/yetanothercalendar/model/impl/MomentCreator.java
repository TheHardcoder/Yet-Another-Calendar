package de.yetanothercalendar.model.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MomentCreator {

	private Locale locale;

	public MomentCreator(Locale locale) {
		this.locale = locale;
	}

	/**
	 * Erstellt den ersten moeglichen Punkt eines events
	 * 
	 * @param date
	 *            Das {@link Date} mit dem gegebenen Tag
	 * @return eine {@link Date} mit dem ersten Moment des Tages ( 00:00:00 )
	 */
	public Date createFirstPossibleMomentOfDay(Date date) {
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
	public Date createLastPossibleMomentOfDay(Date date) {
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
	public Calendar createFirstPossibleMomentOfDayReturningCalendar(
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
	public Calendar createLastPossibleMomentOfDayReturningCalendar(
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
	public Calendar createFirstPossibleMomentOfYearReturningCalendar(
			Calendar calendar) {
		createFirstPossibleMomentOfDayReturningCalendar(calendar);
		calendar.set(Calendar.DAY_OF_YEAR, 1);
		calendar.set(Calendar.WEEK_OF_YEAR, 0);
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
	public Calendar createLastPossibleMomentOfYearReturningCalendar(
			Calendar calendar) {
		createLastPossibleMomentOfDayReturningCalendar(calendar);
		calendar.set(Calendar.DAY_OF_YEAR,
				calendar.getMaximum(Calendar.DAY_OF_YEAR));
		calendar.set(Calendar.WEEK_OF_YEAR,
				calendar.getMaximum(Calendar.WEEK_OF_YEAR));
		return calendar;
	}

	/**
	 * Erstellt den ersten moeglichen Punkt im Monat
	 * 
	 * @param date
	 *            Das {@link Calendar} mit dem gegebenen Monat
	 * @return eine {@link Calendar} mit dem ersten Moment des Monats ( 00:00:00
	 *         )
	 */
	public Calendar createFirstPossibleMomentOfMonthReturningCalendar(
			Calendar calendar) {
		createFirstPossibleMomentOfDayReturningCalendar(calendar);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		return calendar;
	}

	/**
	 * Erstellt den letzt moeglichen Punkt im Monat
	 * 
	 * @param date
	 *            Das {@link Calendar} mit dem gegebenen Monat
	 * @return eine {@link Calendar} mit dem letzten Moment des Monats (
	 *         00:00:00 )
	 */
	public Calendar createLastPossibleMomentOfMonthReturningCalendar(
			Calendar calendar) {
		createLastPossibleMomentOfDayReturningCalendar(calendar);
		calendar.set(Calendar.DAY_OF_YEAR,
				calendar.getMaximum(Calendar.DAY_OF_MONTH));
		return calendar;
	}

	/**
	 * Erstellt den ersten moeglichen Punkt im woche
	 * 
	 * @param date
	 *            Das {@link Calendar} mit dem gegebenen woche
	 * @return eine {@link Calendar} mit dem ersten Moment des woche ( 00:00:00
	 *         )
	 */
	public Calendar createFirstPossibleMomentOfWeekReturningCalendar(
			Calendar calendar) {
		createFirstPossibleMomentOfDayReturningCalendar(calendar);
		calendar.set(Calendar.DAY_OF_WEEK, 0);
		return calendar;
	}

	/**
	 * Erstellt den letzt moeglichen Punkt im woche
	 * 
	 * @param date
	 *            Das {@link Calendar} mit dem gegebenen woche
	 * @return eine {@link Calendar} mit dem letzten Moment des woches (
	 *         00:00:00 )
	 */
	public Calendar createLastPossibleMomentOfWeekReturningCalendar(
			Calendar calendar) {
		createLastPossibleMomentOfDayReturningCalendar(calendar);
		calendar.set(Calendar.DAY_OF_YEAR,
				calendar.getMaximum(Calendar.DAY_OF_MONTH));
		return calendar;
	}

	public boolean isSameDay(Calendar calendarStartDay, Calendar calendarEndDay) {
		return calendarStartDay.get(Calendar.YEAR) == calendarEndDay
				.get(Calendar.YEAR)
				&& calendarStartDay.get(Calendar.DAY_OF_YEAR) == calendarEndDay
						.get(Calendar.DAY_OF_YEAR);
	}

	public boolean isOneDayDifference(Calendar calendarStartDay,
			Calendar calendarEndDay) {
		return ((calendarStartDay.get(Calendar.YEAR) == calendarEndDay
				.get(Calendar.YEAR) && calendarStartDay
				.get(Calendar.DAY_OF_YEAR) == calendarEndDay
				.get(Calendar.DAY_OF_YEAR) - 1));
	}

	public Calendar createMondayFirstWeekOfYear(int year) {
		java.util.Calendar calendar = new GregorianCalendar(locale);
		calendar.clear();
		calendar.set(java.util.Calendar.YEAR, year);
		calendar.set(java.util.Calendar.DAY_OF_YEAR, 1);
		calendar.get(java.util.Calendar.DAY_OF_YEAR);
		calendar.set(java.util.Calendar.DAY_OF_WEEK, java.util.Calendar.MONDAY);
		calendar.get(java.util.Calendar.DAY_OF_WEEK);
		return calendar;
	}

	public Calendar createLastSundayLastWeekOfYear(int year) {
		java.util.Calendar calendar = new GregorianCalendar(locale);
		calendar.set(java.util.Calendar.YEAR, year);
		calendar.set(java.util.Calendar.DAY_OF_YEAR,
				calendar.getActualMaximum(java.util.Calendar.DAY_OF_YEAR));
		calendar.get(java.util.Calendar.DAY_OF_YEAR);
		calendar.set(java.util.Calendar.DAY_OF_WEEK, java.util.Calendar.SUNDAY);
		calendar.get(java.util.Calendar.DAY_OF_WEEK);
		return calendar;
	}
}
