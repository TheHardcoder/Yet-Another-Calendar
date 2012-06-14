package de.yetanothercalendar.model.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.yetanothercalendar.model.dao.UserDAO;
import de.yetanothercalendar.model.database.User;
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
					+ "(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
					+ "email varchar(100) NOT NULL UNIQUE,  "
					+ "forename varchar(100),  " + "lastname varchar(100), "
					+ "password varchar(100) NOT NULL);";
			createStatement.executeUpdate(tablecreationString);
			createStatement.close();
			con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public boolean createUser(String email, String forename, String lastname,
			String password) {
		try {
			Connection con = manager.getConnection();
			Statement createStatement = con.createStatement();
			ResultSet rsUsers = createStatement
					.executeQuery("SELECT email From users"
							+ "WHERE email = \" " + email + "\" ;");
			String dbEmail = rsUsers.getString(0);
			if (dbEmail.equalsIgnoreCase(email)) {
				return false;
			} else {
				String usercreationString = "INSERT INTO users "
						+ "(email, forename, lastname, password)"
						+ "VALUES (\" " + email + "\", \" " + forename
						+ "\", \" " + lastname + "\", \" " + password + ");";
				createStatement.executeUpdate(usercreationString);
				createStatement.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public User createUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public User isUserDataCorrect(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}
}
