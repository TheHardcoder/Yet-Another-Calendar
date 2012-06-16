package de.yetanothercalendar.model.view;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;

import de.yetanothercalendar.model.calendar.Month;
import de.yetanothercalendar.model.calendar.Week;

public class MonthViewHelper extends ViewHelper {

	private Month month;
	private Element eMonthElement;

	public MonthViewHelper(Month pMonth) {
		this.month = pMonth;
		eMonthElement = new Element("month");
		eMonthElement.setAttribute("name", month.getName());
		eMonthElement.setAttribute("number", String.valueOf(month.getNumber()));
		eMonthElement.addContent(this.getWeekElements());
	}

	public Element getMonthElement() {
		return eMonthElement;
	}

	public List<Element> getWeekElements() {
		List<Element> listOfWeekElements = new ArrayList<Element>();
		List<WeekViewHelper> listOfWeekViews = new ArrayList<WeekViewHelper>();
		List<Week> listofWeeks = month.getWeeks();
		for (Week itWeek : listofWeeks) {
			listOfWeekViews.add(new WeekViewHelper(itWeek));
		}
		for (WeekViewHelper wView : listOfWeekViews) {
			listOfWeekElements.add(wView.getWeekElement());
		}
		return listOfWeekElements;
	}

}
