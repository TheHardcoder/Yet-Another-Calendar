package de.yetanothercalendar.test;

import java.sql.Connection;
import java.sql.SQLException;

import junit.framework.TestCase;

import org.junit.Test;

import de.yetanothercalendar.db.helper.DatabaseConnectionManager;

public class DatabaseConnectionManaterTest extends TestCase {

	@Test
	public void testManager() throws SQLException {
		DatabaseConnectionManager manager = new DatabaseConnectionManager();
		Connection connection = manager.getConnection();
		assertNotNull(connection);
	}

}
