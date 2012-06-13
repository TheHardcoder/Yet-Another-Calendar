package de.yetanothercalendar.model.impl;

import java.text.ParseException;
import java.util.Date;

import de.yetanothercalendar.model.iCal.ICalendarImporter;

public class RRule {

	private Date until;

	public String getBySecond() {
		return bySecond;
	}

	public void setBySecond(String bySecond) {
		this.bySecond = bySecond;
	}

	private String byMonth;
	private String byWeekNo;
	private String byMonthDay;
	private String byYearDay;
	private String byHour;
	private String byMinute;
	private String bySecond;
	private String bySetPos;

	// Start of the week
	private String wkst;
	private String freq;
	private int interval;
	private int count;

	/**
	 * Standard Constructor
	 */
	public RRule() {

	}

	/**
	 * Parses an RRule-String to an RRule-Object
	 * 
	 * @param rrule
	 *            RRule String
	 */
	public RRule(String rrule) {
		this.parseRRule(rrule);
	}

	/**
	 * Parses an RRule-String to an RRule-Object
	 * 
	 * @param rrule
	 *            RRule String
	 */
	public void parseRRule(String rrule) {
		/*
		 * FIXME: Mit JDK 1.7 switch �ber den substring(0,4) von rrule und dann
		 * Elemente direkt setzen --> wesentlich schneller
		 */

		String untilStr = getRRULEProperty("UNTIL=", rrule);
		try {
			until = ICalendarImporter.parseIcsDate(untilStr);
		} catch (ParseException e) {
			// no until Date set
			until = null;
		}

		byMonth = getRRULEProperty("BYMONTH=", rrule);
		byWeekNo = getRRULEProperty("BYWEEKNO=", rrule);
		byMonthDay = getRRULEProperty("BYMONTHDAY=", rrule);
		byYearDay = getRRULEProperty("BYYEARDAY=", rrule);
		byHour = getRRULEProperty("BYHOUR=", rrule);
		byMinute = getRRULEProperty("BYMINUTE=", rrule);
		bySecond = getRRULEProperty("BYSECOND=", rrule);
		bySetPos = getRRULEProperty("BYSETPOS=", rrule);
		wkst = getRRULEProperty("WKST=", rrule);

		freq = getRRULEProperty("FREQ=", rrule);
		interval = Integer.parseInt(getRRULEProperty("INTERVAL=", rrule));
		count = Integer.parseInt(getRRULEProperty("COUNT=", rrule));
	}

	public String getWkst() {
		return wkst;
	}

	public void setWkst(String wkst) {
		this.wkst = wkst;
	}

	public Date getUntil() {
		return until;
	}

	public void setUntil(Date until) {
		this.until = until;
	}

	public String getByMonth() {
		return byMonth;
	}

	public void setByMonth(String byMonth) {
		this.byMonth = byMonth;
	}

	public String getByWeekNo() {
		return byWeekNo;
	}

	public void setByWeekNo(String byWeekNo) {
		this.byWeekNo = byWeekNo;
	}

	public String getByMonthDay() {
		return byMonthDay;
	}

	public void setByMonthDay(String byMonthDay) {
		this.byMonthDay = byMonthDay;
	}

	public String getByYearDay() {
		return byYearDay;
	}

	public void setByYearDay(String byYearDay) {
		this.byYearDay = byYearDay;
	}

	public String getByHour() {
		return byHour;
	}

	public void setByHour(String byHour) {
		this.byHour = byHour;
	}

	public String getByMinute() {
		return byMinute;
	}

	public void setByMinute(String byMinute) {
		this.byMinute = byMinute;
	}

	public String getBySetPos() {
		return bySetPos;
	}

	public void setBySetPos(String bySetPos) {
		this.bySetPos = bySetPos;
	}

	public String getFreq() {
		return freq;
	}

	public void setFreq(String freq) {
		this.freq = freq;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * gets a specified Property of the RRule from the RRule string
	 * 
	 * @param name
	 *            name of the property
	 * @param rrule
	 *            RRULE String
	 * @return Property as text
	 */
	private String getRRULEProperty(String name, String rrule) {
		if (rrule.indexOf(name) > -1) {
			int attrBegin = rrule.indexOf(name) + name.length();
			int attrEnd = rrule.indexOf(';', attrBegin);
			// No semicolon found --> is last entry in RRULE-Property
			if (attrEnd < 0) {
				attrEnd = rrule.length();
			}
			return rrule.substring(attrBegin, attrEnd);
		} else
			return "";
	}

	/**
	 * function used to determine, whether a specific property contains a given
	 * value e.g. if byYearDay contains the day 233
	 * 
	 * @param s
	 *            given value
	 * @param property
	 *            property
	 * @return
	 */
	public boolean propertyContains(String s, String property) {
		// makes the search much easier, because one might search for ,s,
		property = "," + property + ",";
		return property.contains("," + s + ",");
	}
}
