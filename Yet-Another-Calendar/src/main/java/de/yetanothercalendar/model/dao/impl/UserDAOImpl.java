package de.yetanothercalendar.model.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import de.yetanothercalendar.model.dao.UserDAO;
import de.yetanothercalendar.model.database.helper.DatabaseConnectionManager;

/**
 * Über die Klasse {@link UserDAOImpl} erfolgt der Zugriff auf die Datenbank
 * (Tabelle users).
 */
public class UserDAOImpl implements UserDAO {

	private DatabaseConnectionManager manager;

	public UserDAOImpl(DatabaseConnectionManager manager) {
		super();
		this.manager = manager;
	}

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
