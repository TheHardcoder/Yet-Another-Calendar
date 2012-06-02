package de.yetanothercalendar.model.calendar;

import java.util.ArrayList;
import java.util.List;

/**
 * Repraesentiert eine Woche im XmlFormat.(<week)
 */
public class Week {
	private int number;
	private List<Day> days;

	public Week(int number) {
		super();
		this.number = number;
		this.days = new ArrayList<Day>();
	}

	public Week(int number, List<Day> days) {
		super();
		this.number = number;
		this.days = days;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<Day> getDays() {
		return days;
	}

	public void setDays(List<Day> days) {
		this.days = days;
	}

}
