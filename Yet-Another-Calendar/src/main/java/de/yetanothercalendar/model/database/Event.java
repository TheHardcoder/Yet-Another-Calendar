package de.yetanothercalendar.model.database;

import java.util.Date;
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
	private Date exdate;
	private Date rdate;

	public Event() {
	}

	public Event(long id, User user, Date dtstamp, String uid, Date dtstart,
			Date created, String description, Date lastmod, String location,
			String priority, String summary, String recurid, String rrule,
			Date dtend, long duration, String color, List<String> categories,
			String comment, Date exdate, Date rdate) {
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
			String comment, Date exdate, Date rdate) {
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

	public Date getExdate() {
		return exdate;
	}

	public void setExdate(Date exdate) {
		this.exdate = exdate;
	}

	public Date getRdate() {
		return rdate;
	}

	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}

	@Override
	public String toString() {
		return "Id: " + id + "\n" + " User: " + user.toString() + "\n"
				+ "dtsatmp: " + parseDate(dtstamp) + " UID: " + uid
				+ " dtstart:" + parseDate(dtstart) + "\n" + "created:"
				+ parseDate(created) + " Description: " + description
				+ " Lastmod: " + parseDate(lastmod) + "\n" + "Location: "
				+ location + " Priority: " + priority + " Summary: " + summary
				+ "\n" + "Recurid:" + recurid + " RRule: " + rrule + " dtend: "
				+ parseDate(dtend) + "\n" + "duration: "
				+ Double.toString(duration) + " color: " + color
				+ " categories: " + categories + "\n" + "comment: " + comment
				+ " Exdate: " + parseDate(exdate) + " rdate: "
				+ parseDate(rdate);
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

}
