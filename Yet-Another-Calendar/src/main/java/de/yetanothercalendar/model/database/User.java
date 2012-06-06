package de.yetanothercalendar.model.database;

import javax.servlet.http.HttpSession;

/**
 * Repraesentiert einen Benutzer im System. Instanzen diese Klasse {@link User}
 * werden in der {@link HttpSession} zur loginverwaltung gespeichert.
 */
public class User {


	private Long id;
	private String email;
	private String forename;
	private String lastname;
	private String passwordSHA1;

	@Override
	public String toString() {
		return "User ID: "+id+" email: "+email+" forename: "+forename+" lastname: "+lastname+" passwordSHA1: "+passwordSHA1;
	}
	
	public User(String email, String forename, String lastname,
			String passwordSHA1) {
		super();
		this.email = email;
		this.forename = forename;
		this.lastname = lastname;
		this.passwordSHA1 = passwordSHA1;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPasswordSHA1() {
		return passwordSHA1;
	}

	public void setPasswordSHA1(String passwordSHA1) {
		this.passwordSHA1 = passwordSHA1;
	}
}
