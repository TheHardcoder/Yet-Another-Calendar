package de.yetanothercalendar.model.dao;

import de.yetanothercalendar.model.database.Event;
import de.yetanothercalendar.model.database.User;

public interface UserDAO {

	/**
	 * Speichert die Userdaten in die Tabelle USERS
	 * 
	 * @param user
	 * 			  welcher gespeichert werden soll
	 * @return  ein {@link User}, vorher gespeichert wurde.
	 */
	public abstract User createUser(User user);

	/** 
	 * @param email
	 * 			   nach welcher geprueft wird
	 * @param password
	 * 			   nach welchem geprueft wird
	 * @return true wenn die Email-Adresse des Users in der Tabelle 
	 * 		   USERS vorhanden ist und ob das eingegebene Passwort richtig ist.
	 *		   Ansonsten false.	 
	 */
	public abstract boolean checkUser(String email, String password);

	/**
	 * Hollt sich die Userdaten aus der Tabelle USERS und gibt ein Objekt vom
	 * Typ User zurueck
	 * 
	 * @param email
	 * 				nach welcher in der Tabelle USERS gesucht wird
	 * @return {@link User} fals die Email-Adresse vorhanden ist,
	 * 		   ansonsten NULL.
	 */
	public abstract User returnUser(String email);

}