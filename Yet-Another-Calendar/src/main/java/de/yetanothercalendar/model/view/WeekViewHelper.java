package de.yetanothercalendar.model.view;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;

import de.yetanothercalendar.model.calendar.Day;
import de.yetanothercalendar.model.calendar.Week;

public class WeekViewHelper extends ViewHelper {

	private Week week;

	public WeekViewHelper(Week pWeek) {
		this.week = pWeek;
		element = new Element("week");
		element.setAttribute("number", String.valueOf(week.getNumber()));
		element.addContent(this.getWeekElements());
	}

	private List<Element> getWeekElements() {
		List<Element> listOfDayElements = new ArrayList<Element>();
		List<DayViewHelper> listOfDayViews = new ArrayList<DayViewHelper>();
		List<Day> listofDays = week.getDays();
		for (Day itDay : listofDays) {
			listOfDayViews.add(new DayViewHelper(itDay));
		}
		for (DayViewHelper dView : listOfDayViews) {
			listOfDayElements.add(dView.getElement());
		}
		return listOfDayElements;
	}
}
