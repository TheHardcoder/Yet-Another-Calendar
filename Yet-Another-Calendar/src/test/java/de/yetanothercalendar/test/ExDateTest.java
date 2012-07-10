package de.yetanothercalendar.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import de.yetanothercalendar.model.database.Event;
import de.yetanothercalendar.model.database.User;

public class ExDateTest extends TestCase {
	@Test
	public void testExDateParse() {
		User user = new User();
		Event createDummyEvent = createDummyEvent(user);
		@SuppressWarnings("deprecation")
		Date date1 = new Date(2012, 10, 01);
		@SuppressWarnings("deprecation")
		Date date2 = new Date(2212, 10, 22);
		List<Date> datelist = new ArrayList<Date>();
		datelist.add(date1);
		datelist.add(date2);
		createDummyEvent.setExdate(datelist);
		String exdateString = createDummyEvent.getExdateString();

		Event eventasser = createDummyEvent(user);
		eventasser.setExDateString(exdateString);
		assertEquals(date1, eventasser.getExdate().get(0));
		assertEquals(date2, eventasser.getExdate().get(1));
	}

	private Event createDummyEvent(User user) {
		Calendar instance = Calendar.getInstance();
		return new Event(new Long((int) (Math.random() * 100000)), user,
				new Date(), "uuid", instance.getTime(), instance.getTime(),
				"description", new Date(), "location", "very high",
				"what a great summary", "recurrid", null, instance.getTime(),
				0, "#fff", new ArrayList<String>(), "comment",
				new ArrayList<Date>(), "");
	}
}
