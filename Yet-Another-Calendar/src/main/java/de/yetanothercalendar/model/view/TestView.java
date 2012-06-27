package de.yetanothercalendar.model.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.yetanothercalendar.model.calendar.CalendarEntry;
import de.yetanothercalendar.model.calendar.Day;
import de.yetanothercalendar.model.calendar.Month;
import de.yetanothercalendar.model.calendar.Week;
import de.yetanothercalendar.model.calendar.Year;

public class TestView extends View {

	private List<Month> lMonths = new ArrayList<Month>();
	private List<Week> lWeeks = new ArrayList<Week>();
	private List<Day> lDays = new ArrayList<Day>();
	private List<CalendarEntry> lCalendarEntries = new ArrayList<CalendarEntry>();
	Date dt = new Date();
	List<String> sCate = new ArrayList<String>();

	public TestView() {
		sCate.add("Cate1");
		sCate.add("Cate2");
		sCate.add("Cate3");
		CalendarEntry cE = new CalendarEntry(1, "high", "#FFFF", "Hammer Job",
				dt, dt, dt, "KA", "Teeeest", dt, dt, "Hoffentlich klappts",
				sCate);
		lCalendarEntries.add(cE);
		Day dTday = new Day("Mo", 1);
		dTday.setCalendarEntries(lCalendarEntries);
		lDays.add(dTday);
		Week wTWeek = new Week(1);
		wTWeek.setDays(lDays);
		lWeeks.add(wTWeek);
		Month mTMonth = new Month("Jan", 1);
		Month mTMonth2 = new Month("Feb", 2);
		mTMonth.setWeeks(lWeeks);
		mTMonth2.setWeeks(lWeeks);
		lMonths.add(mTMonth);
		lMonths.add(mTMonth2);
		Year yTest = new Year(2012, lMonths);
		YearView yearview = new YearView(yTest, "1", "1", "1");
		System.out.println(yearview.getXMLString());
	}

	public static void main(String[] args) {
		new TestView();
	}

}
