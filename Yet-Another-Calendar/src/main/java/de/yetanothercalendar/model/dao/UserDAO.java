package de.yetanothercalendar.model.dao;

import de.yetanothercalendar.model.database.User;

public interface UserDAO {

	/**
	 * Speichert die Userdaten in die Tabelle USERS
	 * 
	 * @param user
	 * @return
	 */
	public abstract User createUser(User user);

	/**
	 * �berpr�ft ob die Email-Adresse des Users in der Tabelle USERS vorhanden
	 * ist und �berpr�ft anschlie�end ob das eingegeben Passwort mit dem
	 * Passwort in der Tabelle USERS �bereinstimmt, falls dies der Fall ist wird
	 * true zur�ckgegeben, ansonsten false
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
	public abstract boolean checkUser(String email, String password);

	/**
	 * Hollt sich die Userdaten aus der Tabelle USERS und gibt ein Objekt vom
	 * Typ User zur�ck
	 * 
	 * @param email
	 * @return
	 */
	public abstract User returnUser(String email);

}