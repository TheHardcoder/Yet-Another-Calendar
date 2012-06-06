package de.yetanothercalendar.test;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import de.yetanothercalendar.model.calendar.Month;
import de.yetanothercalendar.model.calendar.Week;
import de.yetanothercalendar.model.calendar.Year;
import de.yetanothercalendar.model.dao.EventDAO;
import de.yetanothercalendar.model.impl.CalendarImpl;

public class CalendarWrapperTest extends TestCase {

	private CalendarImplMock mock;

	public CalendarWrapperTest() {
	}

	@Before
	public void setUp() {
		mock = new CalendarImplMock(null);
	}

	@Test
	public void test() {
		EventDAO eventDAO = new EventDAOMock();
		CalendarImpl calendar = new CalendarImpl(null);
		calendar.setEventDAO(eventDAO);
		int count = 0;
		Year entriesByYear = calendar.getEntriesByYear(2012);
		for (Month month : entriesByYear.getMonths()) {
			for (Week week : month.getWeeks()) {
				count += week.getDays().size();
			}
		}
		// 2012 ist ein Schaltjahr, also 366 tage
		assertEquals(366, count);
	}

	@Test
	public void testgetMonthList() {
		List<Month> months = mock.getMonthList(2012);
		assertEquals(12, months.size());
	}

	@Test
	public void testgetWeekListFEBRUARY() {
		List<Week> weeks = mock.getWeekListWithDays(2012, 1);
		int count = 0;
		for (Week week : weeks) {
			count += week.getDays().size();
		}
		assertEquals(29, count);
	}

	@Test
	public void testgetWeekListJULY() {
		List<Week> weeks = mock.getWeekListWithDays(2012, 6);
		int count = 0;
		for (Week week : weeks) {
			count += week.getDays().size();
		}
		assertEquals(31, count);
	}

	@Test
	public void testgetWeekListOCTOBER() {
		List<Week> weeks = mock.getWeekListWithDays(2012, 9);
		int count = 0;
		for (Week week : weeks) {
			count += week.getDays().size();
		}
		assertEquals(31, count);
	}

	@Test
	public void testgetWeekListSEPTEMBRE() {
		List<Week> weeks = mock.getWeekListWithDays(2012, 8);
		int count = 0;
		for (Week week : weeks) {
			count += week.getDays().size();
		}
		assertEquals(30, count);
	}
}
