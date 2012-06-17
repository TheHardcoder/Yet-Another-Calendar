package de.yetanothercalendar.model.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.yetanothercalendar.model.dao.UserDAO;
import de.yetanothercalendar.model.database.User;
import de.yetanothercalendar.model.database.helper.DatabaseConnectionManager;

/**
 * Ãœber die Klasse {@link UserDAOImpl} erfolgt der Zugriff auf die Datenbank
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

	public boolean createUser(User user) {
		try {
			Connection con = manager.getConnection();
			Statement createStatement = con.createStatement();
			String email = user.getEmail();
			String forename = user.getForename();
			String lastname = user.getLastname();
			String password = user.getPasswordSHA1();
			String userSurchString = "select email from users where email = \""
					+ email + "\";";

			ResultSet rsUsers = createStatement.executeQuery(userSurchString);
			String dbEmail = "";

			while (rsUsers.next()) {
				dbEmail = rsUsers.getString(1);
			}

			rsUsers.close();
			if (dbEmail.equalsIgnoreCase(email)) {
				// Wenn die Mail-Adresse schon existiert wird false
				// zurückgegeben
				return false;
			} else {
				String usercreationString = "INSERT INTO users "
						+ "(email, forename, lastname, password)"
						+ "VALUES (\"" + email + "\", \" " + forename
						+ "\", \" " + lastname + "\", \" " + password + "\");";

				createStatement.executeUpdate(usercreationString);

				createStatement.close();
				con.close();
				return true;

			}
		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}

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
		try {
			Connection con = manager.getConnection();
			Statement createStatement = con.createStatement();
			String userSurch = "select * from users where email = \"" + email
					+ "\";";

			ResultSet rsUsers = createStatement.executeQuery(userSurch);

			rsUsers.next();

			Long dbId = rsUsers.getLong(1);
			String dbEmail = rsUsers.getString(2);
			String dbForename = rsUsers.getString(3);
			String dbLastname = rsUsers.getString(4);
			String dbPassword = rsUsers.getString(5);

			createStatement.close();
			con.close();
			return new User(dbId, dbEmail, dbForename, dbLastname, dbPassword);

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
