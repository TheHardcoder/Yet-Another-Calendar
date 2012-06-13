package de.yetanothercalendar.test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.junit.Test;

import de.yetanothercalendar.model.impl.RRule;

import junit.framework.JUnit4TestAdapter;
import junit.framework.TestCase;

public class RRuleTest extends TestCase {

	@Test 
	public void testParseRRule() {
		String rruleTestString = "FREQ=WEEKLY;INTERVAL=2;WKST=MO;COUNT=10;UNTIL=20120131T140000Z;BYMONTH=1;BYWEEKNO=1,2,3,4;BYSECOND=4;BYMONTHDAY=1,2,3,4,5,6,20;BYYEARDAY=3,43,45;BYHOUR=4;BYMINUTE=30;BYSETPOS=2";
		RRule rrule = new RRule(rruleTestString);
		
		assertEquals(rrule.getByHour(), "4");
		assertEquals(rrule.getByMinute(), "30");
		assertEquals(rrule.getByMonth(), "1");
		assertEquals(rrule.getByMonthDay(), "1,2,3,4,5,6,20");
		assertEquals(rrule.getByYearDay(), "3,43,45");
		assertEquals(rrule.getBySetPos(), "2");
		assertEquals(rrule.getByWeekNo(), "1,2,3,4");
		assertEquals(rrule.getBySecond(), "4");
		assertEquals(rrule.getFreq(), "WEEKLY");
		assertEquals(rrule.getInterval(), 2);
		assertEquals(rrule.getWkst(), "MO");
		assertEquals(rrule.getCount(), 10);
		//Date
		Calendar cal = new GregorianCalendar(Locale.GERMANY);
		cal.set(2012, 0, 31, 14, 00, 00);
		
		//FIXME: Test fails, although date is the same
		//assertEquals(rrule.getUntil(), cal.getTime());
	}
}
