package de.yetanothercalendar.db.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import de.yetanothercalendar.db.helper.DatabaseConnectionManager;

/**
 * Über die Klasse {@link UserDAO} erfolgt der Zugriff auf die Datenbank
 * (Tabelle users).
 */
public class UserDAO {

	private DatabaseConnectionManager manager;

	public UserDAO(DatabaseConnectionManager manager) {
		super();
		this.manager = manager;
	}

	/**
	 * Erstellt die Tabelle USERS in der die Benutzer ({@link User})
	 * abgespeichert werden sollen.
	 */
	public void createUserTable() {
		try {
			Connection con = manager.getConnection();
			Statement createStatement = con.createStatement();
			String tablecreationString = "CREATE TABLE users "
					+ "(id INT PRIMARY KEY, "
					+ "email varchar(500) NOT NULL UNIQUE,  "
					+ "forename varchar(500),  " + "lastname varchar(500), "
					+ "password varchar(500) NOT NULL);";
			createStatement.executeUpdate(tablecreationString);
			createStatement.close();
			con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
