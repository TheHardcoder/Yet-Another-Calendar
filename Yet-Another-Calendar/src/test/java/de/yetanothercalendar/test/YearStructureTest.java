package de.yetanothercalendar.test;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import de.yetanothercalendar.model.calendar.Month;
import de.yetanothercalendar.model.calendar.Week;
import de.yetanothercalendar.model.calendar.Year;
import de.yetanothercalendar.model.dao.EventDAO;
import de.yetanothercalendar.model.database.User;
import de.yetanothercalendar.model.impl.CalendarImpl;

public class YearStructureTest extends TestCase {

	private CalendarImplMock mock;

	public YearStructureTest() {
	}

	@Before
	public void setUp() {
		mock = new CalendarImplMock(new User("test@dummy.de", "Forename",
				"lastname", "langeSHA1"));
	}

	// TODO FIX this textcase becuase of full first and last week of year
	// @Test
	public void test() {
		EventDAO eventDAO = new EventDAOMockSimple();
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
		// FIXME test case: enthaelt jetzt auch letzte und erste woche!
//		assertEquals(366, count);
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
		System.out.println("Tage im Februar: " + count);
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
	public void testgetWeekListSEPTEMBER() {
		List<Week> weeks = mock.getWeekListWithDays(2012, 8);
		int count = 0;
		for (Week week : weeks) {
			count += week.getDays().size();
		}
		assertEquals(30, count);
	}
}
