package de.yetanothercalendar.model.calendar;

import java.util.ArrayList;
import java.util.List;

/**
 * Repraesentiert einen Tag im XmlFormat. (<day)
 */
public class Day {
	private List<CalendarEntry> calendarEntries;
	private String name;
	private int number;

	public Day(String name, int number) {
		super();
		this.name = name;
		this.number = number;
		calendarEntries = new ArrayList<CalendarEntry>();
	}

	public Day(List<CalendarEntry> calendarEntries, String name, int number) {
		super();
		this.calendarEntries = calendarEntries;
		this.name = name;
		this.number = number;
	}

	public List<CalendarEntry> getCalendarEntries() {
		return calendarEntries;
	}

	public void setCalendarEntries(List<CalendarEntry> calendarEntries) {
		this.calendarEntries = calendarEntries;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
