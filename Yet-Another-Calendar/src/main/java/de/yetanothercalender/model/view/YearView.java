package de.yetanothercalender.model.view;

import java.util.ArrayList;
import java.util.List;

import de.yetanothercalendar.model.calendar.Month;
import de.yetanothercalendar.model.calendar.Year;

import org.jdom.Element;

public class YearView extends ParentView {

	private Year year;
	private Element eYearElement;

	public YearView(Year pYear) {
		this.year = pYear;
		eYearElement = new Element("year").setAttribute("number",
				String.valueOf(year.getNumber()));
		eYearElement.addContent(getMonthElements());
	}

	public Element getYearElement() {
		return eYearElement;
	}

	public List<Element> getMonthElements() {
		List<Element> listOfMonthElements = new ArrayList<Element>();
		List<MonthView> listOfMonthViews = new ArrayList<MonthView>();
		List<Month> listofMonths = year.getMonths();
		for (Month itMonth : listofMonths) {
			listOfMonthViews.add(new MonthView(itMonth));
		}
		for (MonthView mView : listOfMonthViews) {
			listOfMonthElements.add(mView.getMonthElement());
		}
		return listOfMonthElements;

	}

}
