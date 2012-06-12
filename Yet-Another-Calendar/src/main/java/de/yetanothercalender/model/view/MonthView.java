package de.yetanothercalender.model.view;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;

import de.yetanothercalendar.model.calendar.Month;
import de.yetanothercalendar.model.calendar.Week;

public class MonthView extends ParentView {

	private Month month;
	private Element eMonthElement;

	public MonthView(Month pMonth) {
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
		List<WeekView> listOfWeekViews = new ArrayList<WeekView>();
		List<Week> listofWeeks = month.getWeeks();
		for (Week itWeek : listofWeeks) {
			listOfWeekViews.add(new WeekView(itWeek));
		}
		for (WeekView wView : listOfWeekViews) {
			listOfWeekElements.add(wView.getWeekElement());
		}
		return listOfWeekElements;
	}

}
