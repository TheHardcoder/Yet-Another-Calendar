package de.yetanothercalender.model.view;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;

import de.yetanothercalendar.model.calendar.Day;
import de.yetanothercalendar.model.calendar.Week;

public class WeekView extends ParentView {

	private Week week;
	private Element eWeekElement;

	public WeekView(Week pWeek) {
		this.week = pWeek;
		eWeekElement = new Element("week");
		eWeekElement.setAttribute("number", String.valueOf(week.getNumber()));
		eWeekElement.addContent(this.getWeekElements());
	}

	public Element getWeekElement() {
		return eWeekElement;
	}

	private List<Element> getWeekElements() {
		List<Element> listOfDayElements = new ArrayList<Element>();
		List<DayView> listOfDayViews = new ArrayList<DayView>();
		List<Day> listofDays = week.getDays();
		for (Day itDay : listofDays) {
			listOfDayViews.add(new DayView(itDay));
		}
		for (DayView dView : listOfDayViews) {
			listOfDayElements.add(dView.getWeekElement());
		}
		return listOfDayElements;
	}
}
