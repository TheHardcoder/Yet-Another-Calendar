package de.yetanothercalendar.test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;

import de.yetanothercalendar.model.iCal.ICalendarParser;

import junit.framework.TestCase;

public class ICalendarParserTest extends TestCase{

	public ICalendarParserTest (String name){
		super(name);
	}
	
	@Test
	public void testGetEvents(){
		StringBuffer testICS = new StringBuffer("BEGIN:VCALENDAR PRODID:-//Rapla//iCal Plugin//EN VERSION:2.0 METHOD:PUBLISH BEGIN:VTIMEZONE " +
				"TZID:Europe/Berlin TZURL:http://tzurl.org/zoneinfo/Europe/Berlin X-LIC-LOCATION:Europe/Berlin BEGIN:DAYLIGHT " +
				"TZOFFSETFROM:+0100 TZOFFSETTO:+0200 TZNAME:CEST DTSTART:19810329T020000 RRULE:FREQ=YEARLY;BYMONTH=3;BYDAY=-1SU " +
				"END:DAYLIGHT BEGIN:STANDARD TZOFFSETFROM:+0200 TZOFFSETTO:+0100 TZNAME:CET DTSTART:19961027T030000 " +
				"RRULE:FREQ=YEARLY;BYMONTH=10;BYDAY=-1SU END:STANDARD BEGIN:STANDARD TZOFFSETFROM:+0100 TZOFFSETTO:+0100 TZNAME:CET" +
				"DTSTART:19460101T000000 RDATE: RDATE: END:STANDARD END:VTIMEZONE BEGIN:VEVENT LAST-MODIFIED:20120509T220000Z " +
				"CREATED:20120509T220000Z DTSTART:20120620T123000Z DTSTAMP:20120509T220000Z DTEND:20120620T140000Z " +
				"SUMMARY:TINF11B2 - Intercultural Communication UID:reservation_37086appointment_15410 LOCATION:421 MLZ Labor , TINF11B2 " +
				"CATEGORIES:Lehrveranstaltung END:VEVENT BEGIN:VEVENT LAST-MODIFIED:20120509T220000Z CREATED:20120411T220000Z " +
				"DTSTART:20120510T074500Z DTSTAMP:20120509T220000Z DTEND:20120510T101500Z SUMMARY:Labor Webengineering [Röthig, Jürgen] " +
				"UID:reservation_36443appointment_14485 LOCATION:TINF11B2, 206 Hörsaal Technik/INF CATEGORIES:Lehrveranstaltung END:VEVENT");
		
		List<String> events = ICalendarParser.getEvents(testICS);
		String event1 = " LAST-MODIFIED:20120509T220000Z " +
				"CREATED:20120509T220000Z DTSTART:20120620T123000Z DTSTAMP:20120509T220000Z DTEND:20120620T140000Z " +
				"SUMMARY:TINF11B2 - Intercultural Communication UID:reservation_37086appointment_15410 LOCATION:421 MLZ Labor , TINF11B2 " +
				"CATEGORIES:Lehrveranstaltung ";
		String event2 = " LAST-MODIFIED:20120509T220000Z CREATED:20120411T220000Z " +
				"DTSTART:20120510T074500Z DTSTAMP:20120509T220000Z DTEND:20120510T101500Z SUMMARY:Labor Webengineering [Röthig, Jürgen] " +
				"UID:reservation_36443appointment_14485 LOCATION:TINF11B2, 206 Hörsaal Technik/INF CATEGORIES:Lehrveranstaltung ";
		
		assertEquals(events.get(0), event1);
		assertEquals(events.get(1), event2);
		
	}
	
	@Test
	public void testGetEventItem(){
		String event = "LAST-MODIFIED:20120509T220000Z " +
				"CREATED:20120509T220000Z DTSTART:20120620T123000Z DTSTAMP:20120509T220000Z DTEND:20120620T140000Z " +
				"SUMMARY:TINF11B2 - Intercultural Communication UID:reservation_37086appointment_15410 LOCATION:421 MLZ Labor , TINF11B2 " +
				"CATEGORIES:Lehrveranstaltung ";
		final int DATE_LENGTH = 15;
		String lastModified = ICalendarParser.getEventItem("LAST-MODFIFIED", DATE_LENGTH, event);
		assertEquals(lastModified, "20120509T220000");
	}
	
	@Test
	public void testParseIcsDate(){
		String icsDate = "20120509T220000";
		try {
			Date parsedDate = ICalendarParser.parseIcsDate(icsDate);
			Calendar c = new GregorianCalendar(2012, 4, 9, 22, 00, 00);
			Date actual = c.getTime();
			System.out.println("Actual"+actual.toString());
			assertEquals(parsedDate, actual);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("ParseException: Wrong Date Input");
		}
	}

}
