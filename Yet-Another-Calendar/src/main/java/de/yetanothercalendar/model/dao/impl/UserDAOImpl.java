package de.yetanothercalendar.model.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

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

				String email = user.getEmail();
				String forename = user.getForename();
				String lastname = user.getLastname();
				String password = user.getPasswordSHA1();

				String sqlString = "INSERT INTO users "
						+ "(email, forename, lastname, password)"
						+ "VALUES ( ?, ?, " + " ?, ? );";
				java.sql.PreparedStatement pstmt = con
						.prepareStatement(sqlString);
				pstmt.setString(1, email);
				pstmt.setString(2, forename);
				pstmt.setString(3, lastname);
				pstmt.setString(4, password);
				pstmt.executeUpdate();
				pstmt.close();
				con.close();
				result = returnUser(email);
				return result;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}

	public boolean checkUser(String email, String password) {
		try {
			Connection con = manager.getConnection();

			java.sql.PreparedStatement pstmt = con.prepareStatement("select password from users where email = ? ;");
			pstmt.setString(1, email);
			
			ResultSet rsUser = pstmt.executeQuery();
			String dbPassword;
			while (rsUser.next()) {
				dbPassword = rsUser.getString(1);
				System.out.println(dbPassword + " = " + password);
				if (dbPassword.equals(password)) {
					pstmt.close();
					rsUser.close();
					con.close();
					return true;
				} else {
					pstmt.close();
					rsUser.close();
					con.close();
					return false;
				}
			}
			pstmt.close();
			rsUser.close();
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
			
			String userSurch = "select * from users where email = ? ;";
					
			java.sql.PreparedStatement pstmt = con.prepareStatement(userSurch);
			pstmt.setString(1, email);
			
			ResultSet rsUsers = pstmt.executeQuery();
			if (rsUsers.next()) {
				int dbId = rsUsers.getInt(1);
				String dbEmail = rsUsers.getString(2);
				String dbForename = rsUsers.getString(3);
				String dbLastname = rsUsers.getString(4);
				String dbPassword = rsUsers.getString(5);
				result = new User(dbId, dbEmail, dbForename, dbLastname,
						dbPassword);
			}
			pstmt.close();
			rsUsers.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
}
