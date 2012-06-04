package de.yetanothercalendar.model.iCal;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * @author Jan
 * 
 * Compare to:	http://macdevcenter.com/pub/a/mac/2003/05/20/osx_java.html?page=2
 */

public class ICalendarToString {

	private static FileReader calendarReader;

	/**
	 * 
	 * @param calendarFile
	 * @return	String representation of calendarFile
	 * @throws CalendarNotFoundException
	 */
	public static String getCalendarAsString(File calendarFile)
			throws CalendarNotFoundException {
		try {
			calendarReader = new FileReader(calendarFile);
		} catch (FileNotFoundException e) {
			throw new CalendarNotFoundException(e.getMessage(), e.getCause());
		}
		
		try {
			char[] contentCharacterArray = new char[(int) calendarFile.length()];
			calendarReader.read(contentCharacterArray);
			calendarReader.close();
			return new String(contentCharacterArray);
		} catch (IOException e) {
			throw new CalendarNotFoundException(e.getMessage(), e.getCause());
		}	
	}
}