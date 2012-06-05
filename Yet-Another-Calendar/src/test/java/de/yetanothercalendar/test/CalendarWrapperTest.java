package de.yetanothercalendar.test;

import junit.framework.TestCase;

import org.junit.Test;

import de.yetanothercalendar.model.dao.EventDAO;
import de.yetanothercalendar.model.impl.CalendarImpl;

public class CalendarWrapperTest extends TestCase {

	@Test
	public void test() {
		EventDAO eventDAO = new EventDAOMock();
		CalendarImpl calendar = new CalendarImpl(null);
		calendar.setEventDAO(eventDAO);
		calendar.getEntriesByYear(2012);
	}
}
