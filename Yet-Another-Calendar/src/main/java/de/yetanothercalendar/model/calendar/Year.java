package de.yetanothercalendar.model.calendar;

import java.util.ArrayList;
import java.util.List;

/**
 * Repraesentiert ein Jahr im XmlFormat. (<year)
 */
public class Year {
	private int number;
	public List<Month> months;

	public Year(int number) {
		super();
		this.number = number;
		this.months = new ArrayList<Month>();
	}

	public Year(int number, List<Month> months) {
		super();
		this.number = number;
		this.months = months;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<Month> getMonths() {
		return months;
	}

	public void setMonths(List<Month> months) {
		this.months = months;
	}

}
