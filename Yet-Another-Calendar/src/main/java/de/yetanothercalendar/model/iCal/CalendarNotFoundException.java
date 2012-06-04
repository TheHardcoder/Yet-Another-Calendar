package de.yetanothercalendar.model.iCal;

@SuppressWarnings("serial")
public class CalendarNotFoundException extends Exception {
	public CalendarNotFoundException(String description) {
		super(description);
	}

	public CalendarNotFoundException(String description, Throwable cause) {
		super(description, cause);
	}
}