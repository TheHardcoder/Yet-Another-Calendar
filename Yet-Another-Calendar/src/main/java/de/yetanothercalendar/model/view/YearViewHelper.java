package de.yetanothercalendar.model.view;

import java.util.ArrayList;
import java.util.List;

import de.yetanothercalendar.model.calendar.Month;
import de.yetanothercalendar.model.calendar.Year;

import org.jdom.Element;

public class YearViewHelper extends ViewHelper {

	private Year year;

	public YearViewHelper(Year pYear) {
		this.year = pYear;
		element = new Element("year").setAttribute("number",
				String.valueOf(year.getNumber()));
		element.addContent(getMonthElements());
	}

	public List<Element> getMonthElements() {
		List<Element> listOfMonthElements = new ArrayList<Element>();
		List<MonthViewHelper> listOfMonthViews = new ArrayList<MonthViewHelper>();
		List<Month> listofMonths = year.getMonths();
		for (Month itMonth : listofMonths) {
			listOfMonthViews.add(new MonthViewHelper(itMonth));
		}
		for (MonthViewHelper mView : listOfMonthViews) {
			listOfMonthElements.add(mView.getElement());
		}
		return listOfMonthElements;

	}

}
