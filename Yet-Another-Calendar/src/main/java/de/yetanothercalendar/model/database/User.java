package de.yetanothercalendar.model.database;

import javax.servlet.http.HttpSession;

/**
 * Repräsentiert einen Benutzer im System. Instanzen diese Klasse {@link User}
 * werden in der {@link HttpSession} gespeichert.
 */
public class User {
	private Long id;
	private String email;
	private String forename;
	private String lastname;
	private byte[] passwordSHA1;

	public User(String email, String forename, String lastname,
			byte[] passwordSHA1) {
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

	public byte[] getPasswordSHA1() {
		return passwordSHA1;
	}

	public void setPasswordSHA1(byte[] passwordSHA1) {
		this.passwordSHA1 = passwordSHA1;
	}

}