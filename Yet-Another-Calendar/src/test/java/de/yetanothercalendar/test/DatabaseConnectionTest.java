package de.yetanothercalendar.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import junit.framework.TestCase;

import org.junit.Test;

public class DatabaseConnectionTest extends TestCase {

	private static final String URL = "jdbc:mysql://localhost:3306/";
	private static final String DB_NAME = "yetanothercalendar";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String USER_NAME = "";
	private static final String PASSWORD = "";

	private static final String CREATION_STATEMENT = "CREATE DATABASE "
			+ DB_NAME;
	private static final String DROP_STATEMENT = "DROP DATABASE " + DB_NAME;

	public DatabaseConnectionTest() {
		dropDatabase();
	}

	private void dropDatabase() {
		Connection connection;
		try {
			connection = getConnection();
			Statement createStatement = connection.createStatement();
			createStatement.execute(DROP_STATEMENT);
			createStatement.close();
			connection.close();
		} catch (Exception e) {
		}
	}

	private Connection getConnection() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {
		return getConnection("");
	}

	private Connection getConnection(String databaseName)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException {
		Class.forName(DRIVER).newInstance();
		return DriverManager.getConnection(URL + databaseName, USER_NAME,
				PASSWORD);
	}

	@Test
	public void testDatabaseConnection() {
		try {
			Connection conn = getConnection();
			assertFalse("Connection is Read only", conn.isReadOnly());
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			fail("An Exception " + e.getMessage()
					+ " was thrown. Stacktrace is " + e.getStackTrace());
		}
	}

	@Test
	public void testDatabaseCreation() {
		try {
			Connection connection = getConnection();
			assertFalse("Connection is Read only", connection.isReadOnly());
			Statement tableCreationStatement = connection.createStatement();
			tableCreationStatement.executeUpdate(CREATION_STATEMENT);
			tableCreationStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			fail("An Exception " + e.getMessage() + " was thrown.");
		}
	}
}
