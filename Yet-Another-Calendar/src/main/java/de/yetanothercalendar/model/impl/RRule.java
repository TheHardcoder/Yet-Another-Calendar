package de.yetanothercalendar.model.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.yetanothercalendar.model.iCal.ICalendarImporter;

public class RRule {

	private Date until;

	private List<Integer> byMonth;
	private List<Integer> byWeekNo;
	private List<Integer> byMonthDay;
	private List<Integer> byYearDay;
	private List<Integer> byHour;
	private List<Integer> byMinute;
	private List<Integer> bySecond;
	private List<Integer> bySetPos;
	
	//Start of the week
	private String wkst;
	private String freq;
	private int interval;
	private int count;
	/**
	 * Standard Constructor
	 */
	public RRule(){
		
	}
	
	/**
	 * Parses an RRule-String to an RRule-Object
	 * @param rrule 
	 * RRule String
	 */
	public RRule(String rrule){
		this.parseRRule(rrule);
	}
	
	/**
	 * Parses an RRule-String to an RRule-Object
	 * @param rrule 
	 * RRule String
	 */
	public void parseRRule(String rrule){
		/* FIXME: Mit JDK 1.7 switch über den substring(0,4) von rrule und dann Elemente direkt setzen --> wesentlich schneller*/
		
		String untilStr = getRRULEProperty("UNTIL=", rrule);
		try {
			until = ICalendarImporter.parseIcsDate(untilStr);
		} catch (ParseException e) {
			//no until Date set
			until = null;
		}
		
		byMonth = parsePropertyToList(getRRULEProperty("BYMONTH=", rrule));
		byWeekNo = parsePropertyToList(getRRULEProperty("BYWEEKNO=", rrule));
		byMonthDay = parsePropertyToList(getRRULEProperty("BYMONTHDAY=", rrule));
		byYearDay = parsePropertyToList(getRRULEProperty("BYYEARDAY=", rrule));
		byHour = parsePropertyToList(getRRULEProperty("BYHOUR=", rrule));
		byMinute = parsePropertyToList(getRRULEProperty("BYMINUTE=", rrule));
		bySecond = parsePropertyToList(getRRULEProperty("BYSECOND=", rrule));
		bySetPos = parsePropertyToList(getRRULEProperty("BYSETPOS=", rrule));
		
		wkst	= getRRULEProperty("WKST=", rrule);
		freq = getRRULEProperty("FREQ=", rrule);
		interval = Integer.parseInt(getRRULEProperty("INTERVAL=", rrule));
		count = Integer.parseInt(getRRULEProperty("COUNT=", rrule));
	}

	/**
	 * gets a specified Property of the RRule from the RRule string
	 * @param name name of the property
	 * @param rrule RRULE String
	 * @return Property as text
	 */
	private String getRRULEProperty(String name, String rrule){
		if (rrule.indexOf(name)>-1){
			int attrBegin = rrule.indexOf(name)+name.length();
			int attrEnd = rrule.indexOf(';', attrBegin);
			//No semicolon found --> is last entry in RRULE-Property
			if (attrEnd < 0){
				attrEnd = rrule.length();
			}
			return rrule.substring(attrBegin, attrEnd);
		} else return "";
	}
	
	/**
	 * function used to determine, whether a specific property contains a given value
	 * e.g. if byYearDay contains the day 233
	 * @param s given value
	 * @param property property
	 * @return
	 */
	public boolean propertyContains(String s, String property){
		//makes the search much easier, because one might search for ,s,
		property = ","+property+",";
		return property.contains(","+s+",");
	}
	
	/**
	 * Parses a Property into a sorted String List
	 * used for example for byYearDay to get a sorted List with all of the YearDays specified in RRULE
	 * @param propertyString
	 * String representation of the property
	 * @return
	 * Property as List (Splited at ;)
	 */
	public List<Integer> parsePropertyToList(String propertyString){
		final String SEPERATOR = ",";
		List<Integer> propertyList = new ArrayList<Integer>();
		String[] properties = propertyString.split(SEPERATOR);
		for (String string : properties) {
			propertyList.add(Integer.parseInt(string));
		}
		return propertyList;
	}
	
	public List<Integer> getByMonth() {
		return byMonth;
	}

	public void setByMonth(List<Integer> byMonth) {
		this.byMonth = byMonth;
	}

	public List<Integer> getByWeekNo() {
		return byWeekNo;
	}

	public void setByWeekNo(List<Integer> byWeekNo) {
		this.byWeekNo = byWeekNo;
	}

	public List<Integer> getByMonthDay() {
		return byMonthDay;
	}

	public void setByMonthDay(List<Integer> byMonthDay) {
		this.byMonthDay = byMonthDay;
	}

	public List<Integer> getByYearDay() {
		return byYearDay;
	}

	public void setByYearDay(List<Integer> byYearDay) {
		this.byYearDay = byYearDay;
	}

	public List<Integer> getByHour() {
		return byHour;
	}

	public void setByHour(List<Integer> byHour) {
		this.byHour = byHour;
	}

	public List<Integer> getByMinute() {
		return byMinute;
	}

	public void setByMinute(List<Integer> byMinute) {
		this.byMinute = byMinute;
	}

	public List<Integer> getBySecond() {
		return bySecond;
	}

	public void setBySecond(List<Integer> bySecond) {
		this.bySecond = bySecond;
	}

	public List<Integer> getBySetPos() {
		return bySetPos;
	}

	public void setBySetPos(List<Integer> bySetPos) {
		this.bySetPos = bySetPos;
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
}
