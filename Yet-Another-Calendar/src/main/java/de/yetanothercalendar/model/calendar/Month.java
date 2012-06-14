package de.yetanothercalendar.model.calendar;

import java.util.ArrayList;
import java.util.List;

/**
 * Repraesentiert einen Monat im XmlFormat. (<month)
 */
public class Month {
	private String name;
	private int number;
	private List<Week> weeks;

	public Month(String name, int number) {
		super();
		this.name = name;
		this.number = number;
		weeks = new ArrayList<Week>();
	}

	public Month(String name, int number, List<Week> weeks) {
		super();
		this.name = name;
		this.number = number;
		this.weeks = weeks;
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

	public List<Week> getWeeks() {
		return weeks;
	}

	public void setWeeks(List<Week> weeks) {
		this.weeks = weeks;
	}

}
