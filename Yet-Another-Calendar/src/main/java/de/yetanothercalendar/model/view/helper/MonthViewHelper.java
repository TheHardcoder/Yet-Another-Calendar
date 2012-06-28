package de.yetanothercalendar.model.view.helper;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;

import de.yetanothercalendar.model.calendar.Month;
import de.yetanothercalendar.model.calendar.Week;

public class MonthViewHelper extends ViewHelper {

	private Month month;

	public MonthViewHelper(Month pMonth) {
		this.month = pMonth;
		element = new Element("month");
		element.setAttribute("name", month.getName());
		element
				.setAttribute("number", String.valueOf(month.getNumber()));
		element.addContent(this.getWeekElements());
	}

	public List<Element> getWeekElements() {
		List<Element> listOfWeekElements = new ArrayList<Element>();
		List<WeekViewHelper> listOfWeekViews = new ArrayList<WeekViewHelper>();
		List<Week> listofWeeks = month.getWeeks();
		for (Week itWeek : listofWeeks) {
			listOfWeekViews.add(new WeekViewHelper(itWeek));
		}
		for (WeekViewHelper wView : listOfWeekViews) {
			listOfWeekElements.add(wView.getElement());
		}
		return listOfWeekElements;
	}

}
