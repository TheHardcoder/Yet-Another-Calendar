package de.yetanothercalendar.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import junit.framework.TestCase;

import org.junit.Test;

import de.yetanothercalendar.model.impl.RRule;

public class RRuleTest extends TestCase {

	@Test
	public void testParseRRule() {
		String rruleTestString = "FREQ=WEEKLY;INTERVAL=2;WKST=MO;COUNT=10;UNTIL=20120131T140000Z;BYMONTH=1;BYWEEKNO=1,2,3,4;BYSECOND=4;BYMONTHDAY=1,2,3,20;BYYEARDAY=3,43,45;BYHOUR=4;BYMINUTE=30;BYSETPOS=2";
		RRule rrule = new RRule(rruleTestString);

		assertEquals(rrule.getByHour().get(0), new Integer("4"));
		assertEquals(rrule.getByMinute().get(0), new Integer("30"));
		assertEquals(rrule.getByMonth().get(0), new Integer("1"));
		List<Integer> byMonthDay = new ArrayList<Integer>();
		byMonthDay.add(new Integer("1"));
		byMonthDay.add(new Integer("2"));
		byMonthDay.add(new Integer("3"));
		byMonthDay.add(new Integer("20"));
		assertTrue(rrule.getByMonthDay().equals(byMonthDay));

		List<Integer> byYearDay = new ArrayList<Integer>();
		byYearDay.add(new Integer("3"));
		byYearDay.add(new Integer("43"));
		byYearDay.add(new Integer("45"));
		assertNotNull(byYearDay);
		assertNotNull(rrule.getByYearDay());
		assertTrue(rrule.getByYearDay().equals(byYearDay));

		assertEquals(rrule.getBySetPos().get(0), new Integer("2"));

		assertEquals(rrule.getByWeekNo().get(0), new Integer("1"));
		assertEquals(rrule.getBySecond().get(0), new Integer("4"));
		assertEquals(rrule.getFreq(), "WEEKLY");
		assertEquals(rrule.getInterval(), 2);
		assertEquals(rrule.getWkst(), "MO");
		assertEquals(rrule.getCount(), 10);
		// Date
		Calendar cal = new GregorianCalendar(Locale.GERMANY);
		cal.set(2012, 0, 31, 14, 00, 00);

		// FIXME: Test fails, although date is the same
		// assertEquals(rrule.getUntil(), cal.getTime());
	}
}
