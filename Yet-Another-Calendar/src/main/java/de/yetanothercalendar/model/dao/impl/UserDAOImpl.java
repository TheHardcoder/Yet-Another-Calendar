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

	/**
	 * Erstellt die Tabelle USERS in der die Benutzer ({@link Users})
	 * abgespeichert werden sollen.
	 */
	public void createUserTable() {
		try {
			Connection con = manager.getConnection();
			Statement createStatement = con.createStatement();
			String tablecreationString = "CREATE TABLE IF NOT EXISTS users "
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

	public User createUser(User user) {
		User result = returnUser(user.getEmail());
		if (result == null) {
			try {
				Connection con = manager.getConnection();
				Statement createStatement = con.createStatement();
				String email = user.getEmail();
				String forename = user.getForename();
				String lastname = user.getLastname();
				String password = user.getPasswordSHA1();

				String usercreationString = "INSERT INTO users "
						+ "(email, forename, lastname, password)"
						+ "VALUES (\"" + email + "\", \"" + forename + "\", \""
						+ lastname + "\", \"" + password + "\");";
				createStatement.executeUpdate(usercreationString);
				createStatement.close();
				con.close();
				result = returnUser(email);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public boolean checkUser(String email, String password) {
		try {
			Connection con = manager.getConnection();
			Statement createStatement = con.createStatement();
			String userSurch = "select password from users where email = \""
					+ email + "\";";
			ResultSet rsUser = createStatement.executeQuery(userSurch);
			String dbPassword;
			while (rsUser.next()) {
				dbPassword = rsUser.getString(1);
				System.out.println(dbPassword + "= " + password);
				if (dbPassword.equals(password)) {
					createStatement.close();
					con.close();
					return true;
				} else {
					createStatement.close();
					con.close();
					return false;
				}
			}
			createStatement.close();
			con.close();
			return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public User returnUser(String email) {
		User result = null;
		try {
			Connection con = manager.getConnection();
			Statement createStatement = con.createStatement();
			String userSurch = "select * from users where email = \"" + email
					+ "\";";

			ResultSet rsUsers = createStatement.executeQuery(userSurch);
			if (rsUsers.next()) {
				int dbId = rsUsers.getInt(1);
				String dbEmail = rsUsers.getString(2);
				String dbForename = rsUsers.getString(3);
				String dbLastname = rsUsers.getString(4);
				String dbPassword = rsUsers.getString(5);
				result = new User(dbId, dbEmail, dbForename, dbLastname,
						dbPassword);
			}
			createStatement.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
