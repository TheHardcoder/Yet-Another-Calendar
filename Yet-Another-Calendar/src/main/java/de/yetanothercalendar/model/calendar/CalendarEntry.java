package de.yetanothercalendar.model.calendar;

import java.util.Date;
import java.util.List;

/**
 * Repraesentiert einen Kalendereintrag im XML format. (<entry)
 */
public class CalendarEntry {

	/**
	 * Repraesentiert die Id im XML für das Attribut id im Element <entry
	 */
	private long id;

	/**
	 * Repraesentiert die Priorität im XML für das Attribut im Element <entry
	 */
	private String priority;

	/**
	 * Repraesentiert die Farbe als #FARBWERT String im XML für das Attribut im
	 * Element <color
	 */
	private String colorString;

	/**
	 * Repraesentiert die Zusammenfassung im XML für das Element <summary
	 */
	private String summary;

	/**
	 * Repraesentiert hours="xx" und minutes="xx" im XML für das Element
	 * <starttime
	 */
	private Date startTime;

	/**
	 * Repraesentiert hours="xx" und minutes="xx" im XML für das Element
	 * <endtime
	 */
	private Date endTime;

	/**
	 * Repraesentiert hours="xx" und minutes="xx" im XML für das Element
	 * <duration
	 */
	private Date durationTime;

	/** Repraesentiert die Location im XML für das Element <location */
	private String location;

	/** Repraesentiert die Beschreibung im XML für das Element <description */
	private String description;

	/**
	 * Repraesentiert day="xx" month="xx" hours="xx" und minutes="xx" im XML für
	 * das Element <created
	 */
	private Date created;

	/**
	 * Repraesentiert day="xx" month="xx" hours="xx" und minutes="xx" im XML für
	 * das Element <modified
	 */
	private Date modified;

	/**
	 * Repraesentiert den Kommentar im XML für das Element <comment
	 */
	private String comment;

	/**
	 * Repraesentiert die Kategorie im XML für das Element <categories Jeder
	 * enthaltene String der Liste {@link #category} repraesentiert eine
	 * Kategory (xml: <category)
	 */
	private List<String> category;

	private int column;

	public CalendarEntry(long id, String priority, String colorString,
			String summary, Date startTime, Date endTime, Date durationTime,
			String location, String description, Date created, Date modified,
			String comment, List<String> category, int column) {
		super();
		this.id = id;
		this.priority = priority;
		this.colorString = colorString;
		this.summary = summary;
		this.startTime = startTime;
		this.endTime = endTime;
		this.durationTime = durationTime;
		this.location = location;
		this.description = description;
		this.created = created;
		this.modified = modified;
		this.comment = comment;
		this.category = category;
		this.column = column;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getColorString() {
		return colorString;
	}

	public void setColorString(String colorString) {
		this.colorString = colorString;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getDurationTime() {
		return durationTime;
	}

	public void setDurationTime(Date durationTime) {
		this.durationTime = durationTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<String> getCategory() {
		return category;
	}

	public void setCategory(List<String> category) {
		this.category = category;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	@Override
	public String toString() {
		return "CalendarEntry [id=" + id + ", priority=" + priority
				+ ", colorString=" + colorString + ", summary=" + summary
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", durationTime=" + durationTime + ", location=" + location
				+ ", description=" + description + ", created=" + created
				+ ", modified=" + modified + ", comment=" + comment
				+ ", category=" + category + "]";
	}

}
