package de.yetanothercalendar.model.database;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Repraesentiert einen Event in der Datenbank.
 */
public class Event {
	private long id;
	private User user;
	private Date dtstamp;
	private String uid;
	private Date dtstart;
	private Date created;
	private String description;
	private Date lastmod;
	private String location;
	private String priority;
	private String summary;
	private String recurid;
	private String rrule;
	private Date dtend;
	// Duration in Minutes, this attribute should only be used, if dtend is not
	// set!
	private long duration;
	private String color;
	private List<String> categories;
	private String comment;
	private List<Date> exdate;
	private String rdate;

	public Event() {
	}

	public Event(long id, User user, Date dtstamp, String uid, Date dtstart,
			Date created, String description, Date lastmod, String location,
			String priority, String summary, String recurid, String rrule,
			Date dtend, long duration, String color, List<String> categories,
			String comment, List<Date> exdate, String rdate) {
		super();
		this.id = id;
		this.user = user;
		this.dtstamp = dtstamp;
		this.uid = uid;
		this.dtstart = dtstart;
		this.created = created;
		this.description = description;
		this.lastmod = lastmod;
		this.location = location;
		this.priority = priority;
		this.summary = summary;
		this.recurid = recurid;
		this.rrule = rrule;
		this.dtend = dtend;
		this.duration = duration;
		this.color = color;
		this.categories = categories;
		this.comment = comment;
		this.exdate = exdate;
		this.rdate = rdate;
	}

	public Event(User user, Date dtstamp, String uid, Date dtstart,
			Date created, String description, Date lastmod, String location,
			String priority, String summary, String recurid, String rrule,
			Date dtend, long duration, String color, List<String> categories,
			String comment, List<Date> exdate, String rdate) {
		super();

		this.user = user;
		this.dtstamp = dtstamp;
		this.uid = uid;
		this.dtstart = dtstart;
		this.created = created;
		this.description = description;
		this.lastmod = lastmod;
		this.location = location;
		this.priority = priority;
		this.summary = summary;
		this.recurid = recurid;
		this.rrule = rrule;
		this.dtend = dtend;
		this.duration = duration;
		this.color = color;
		this.categories = categories;
		this.comment = comment;
		this.exdate = exdate;
		this.rdate = rdate;
	}

	public Event getCopy() {
		return new Event(id, user, dtstamp, uid, dtstart, created, description,
				lastmod, location, priority, summary, recurid, rrule, dtend,
				duration, color, categories, comment, exdate, rdate);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDtstamp() {
		return dtstamp;
	}

	public void setDtstamp(Date dtstamp) {
		this.dtstamp = dtstamp;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Date getDtstart() {
		return dtstart;
	}

	public void setDtstart(Date dtstart) {
		this.dtstart = dtstart;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getLastmod() {
		return lastmod;
	}

	public void setLastmod(Date lastmod) {
		this.lastmod = lastmod;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getRecurid() {
		return recurid;
	}

	public void setRecurid(String recurid) {
		this.recurid = recurid;
	}

	public String getRrule() {
		return rrule;
	}

	public void setRrule(String rrule) {
		this.rrule = rrule;
	}

	public Date getDtend() {
		return dtend;
	}

	public void setDtend(Date dtend) {
		this.dtend = dtend;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<Date> getExdate() {
		return exdate;
	}

	public String getExdateString() {
		if (exdate.size() >= 1) {
//			String exdateString = "EXDATE:";
			String exdateString = "";
			for (Date date : exdate) {
				exdateString += converDateToICSDate(date) + ",";
			}
			// letztes ',' löschen
			exdateString = exdateString.substring(0, exdateString.length() - 1);
			return exdateString;
		}
		return "";
	}

	public void setExdate(List<Date> exdate) {
		this.exdate = exdate;
	}

	public void setExDateString(String dateICSString) {
		List<Date> convertICSSTringToICSDateList = convertICSSTringToICSDateList(dateICSString);
		this.exdate = convertICSSTringToICSDateList;
	}

	public String getRdate() {
		return rdate;
	}

	public void setRdate(String rdate) {
		this.rdate = rdate;
	}

	@Override
	public String toString() {
		return "Id: " + id + "\n" + " User: " + user.toString() + "\n"
				+ "dtstamp: " + parseDate(dtstamp) + " UID: " + uid
				+ " dtstart:" + parseDate(dtstart) + "\n" + "created:"
				+ parseDate(created) + " Description: " + description
				+ " Lastmod: " + parseDate(lastmod) + "\n" + "Location: "
				+ location + " Priority: " + priority + " Summary: " + summary
				+ "\n" + "Recurid:" + recurid + " RRule: " + rrule + " dtend: "
				+ parseDate(dtend) + "\n" + "duration: "
				+ Double.toString(duration) + " color: " + color
				+ " categories: " + categories + "\n" + "comment: " + comment
				+ " Exdate: " + exdate.toString() + " rdate: " + rdate;
	}

	/**
	 * Needed for the toString() method, because Dates can be empty
	 * 
	 * @return String representation of the given Date
	 */
	private String parseDate(Date d) {
		try {
			String s = d.toString();
			return s;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * Converts a Date to an iCal Date String 20120626T140000
	 * 
	 * @param d
	 *            Date to convert
	 * @return String representation of the Date
	 */
	public static String converDateToICSDate(Date d) {
		if (d != null) {
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(d);
			String month = getTwoCharacterString(cal.get(Calendar.MONTH) + 1);
			String day = getTwoCharacterString(cal.get(Calendar.DAY_OF_MONTH));
			String hour = getTwoCharacterString(cal.get(Calendar.HOUR_OF_DAY));
			String minutes = getTwoCharacterString(cal.get(Calendar.MINUTE));
			String seconds = getTwoCharacterString(cal.get(Calendar.SECOND));
			return cal.get(Calendar.YEAR) + month + day + "T" + hour + minutes
					+ seconds + "Z";
		} else {
			return "";
		}
	}

	public static List<Date> convertICSSTringToICSDateList(String list) {
		List<Date> resultList = new ArrayList<Date>();
		String[] split = list.split(",");
		for (String string : split) {
			try {
				Date parseIcsDate = parseIcsDate(string);
				if (parseIcsDate != null) {
					resultList.add(parseIcsDate);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultList;
	}

	/**
	 * Converts an Integer into at least two Chars
	 * 
	 * @param i
	 *            Integer to convert
	 * @return String representation of the Integer (with at least two Chars)
	 */
	private static String getTwoCharacterString(int i) {
		if (i < 9) {
			return "0" + i;
		} else {
			return Integer.toString(i);
		}
	}

	/**
	 * Parse an icsDateString ("yyyyMMddTHHmmssZ") to a Date
	 * 
	 * @param dateString
	 *            iCal Date String
	 * @return Date Representation of the iCal dateString
	 * @throws ParseException
	 *             is thrown in case something goes wrong :-(
	 */
	public static Date parseIcsDate(String dateString) throws ParseException {
		StringBuffer dateBuf = new StringBuffer(dateString);
		// Delete Object Description: e.g. "DTSTAMP:" from
		// "DTSTAMP:20120508T201446Z\r\n"

		while (!(Character.isDigit(dateBuf.charAt(0)))) {
			dateBuf.deleteCharAt(0);
		}
		// Delete the T in Date 20120508T201446Z\r\n
		dateString = dateBuf.toString();
		dateString = dateString.replace('T', ' ');

		DateFormat df = new SimpleDateFormat("yyyyMMdd HHmmss");
		Date date = df.parse(dateString.toString());
		// System.out.println(date.toString());
		return date;
	}

}
