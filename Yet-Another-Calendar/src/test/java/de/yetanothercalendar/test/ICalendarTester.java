package de.yetanothercalendar.test;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.print.attribute.standard.OutputDeviceAssigned;

import junit.framework.Assert;
import junit.framework.TestCase;

import net.fortuna.ical4j.model.Calendar;

import org.junit.Test;

import de.yetanothercalendar.model.database.Event;
import de.yetanothercalendar.model.database.User;
import de.yetanothercalendar.model.iCal.ICalendarExporter;
import de.yetanothercalendar.model.iCal.ICalendarImporter;

public class ICalendarTester extends TestCase {

	@Test
	public void testImportToIcal4J() {
		// TODO: more complex Test with _all_ properties set
		File f = new File("resources/simple.ics");
		InputStream in = null;
		try {
			in = new FileInputStream(f);
			ICalendarImporter importer = new ICalendarImporter();
			Calendar test = importer.importToIcal4J(in);
			List<Event> events = new ArrayList<Event>();
			User user = new User("test@test.de", "test", "test", "123456");
			events = importer.parseIcal4JToEventList(test, user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail("Error parsing sample .ics File");
		}
	}

	@Test
	public void testExportToIcal4J() {
		// TODO: more complex Test with _all_ properties set
		File f = new File("resources/complex.ics");
		InputStream in = null;
		List<Event> events = null;
		try {
			in = new FileInputStream(f);
			ICalendarImporter importer = new ICalendarImporter();
			Calendar test = importer.importToIcal4J(in);
			events = new ArrayList<Event>();
			User user = new User("test@test.de", "test", "test", "123456");
			events = importer.parseIcal4JToEventList(test, user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail("Error parsing sample .ics File");
		}
		
		ICalendarExporter export = new ICalendarExporter();
		List<String> ics = export.exportToIcal(events);
		File outputFile = new File("resources/exportTest.ics");
		if (outputFile.exists()) {
			outputFile.delete();
		}
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(outputFile));
			Iterator<String> it = ics.iterator();
			while (it.hasNext()) {
				out.write(it.next() + System.lineSeparator());
			}
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Event> eventsReImported = null;
		try {
			in = new FileInputStream(outputFile);
			ICalendarImporter importer = new ICalendarImporter();
			Calendar test = importer.importToIcal4J(in);
			eventsReImported = new ArrayList<Event>();
			User user = new User("test@test.de", "test", "test", "123456");
			eventsReImported = importer.parseIcal4JToEventList(test, user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail("Error parsing sample .ics File");
		}
		
		for (int i = 0; i < events.size(); i++) {
			System.out.println("Comparing Event "+i);
			System.out.println("-------- Original Event --------");
			System.out.println(events.get(i).toString());

			System.out.println("-------- Reimported Event --------");
			System.out.println(eventsReImported.get(i).toString());
			assertEquals(events.get(i).toString(), eventsReImported.get(i).toString());
		}
		
		//assertEquals(events, eventsReImported);
	}
}
