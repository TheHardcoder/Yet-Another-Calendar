package de.yetanothercalendar.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import de.yetanothercalendar.model.dao.impl.UserDAOImpl;
import de.yetanothercalendar.model.database.User;
import de.yetanothercalendar.model.database.helper.DatabaseConnectionManager;

import junit.framework.TestCase;

public class UserDAOTest extends TestCase {
	
	static UserDAOImpl user;
	
	@BeforeClass
	public static void prepaireClas() {
		user = new UserDAOImpl(new DatabaseConnectionManager(
				"admin", "admin", "localhost", 3306, "yetanothercalendar"));
	}

	@AfterClass
	public static void cleanUp(){
		
	}
	
	@Test // Schlaegt auf jedenfall fehl weil ein NullPointerException erwartet wird(expected = NullPointerException.class)
	public void testCreateUserTable() {

		/** wenn Test fehlschlägt, dann ist das Anlegen des UserDAOs fehlgeschlagen*/
		assertNotNull("Anlegen des UserDAOs fehlgeschlagen!", user);
		user.createUserTable();
	}

	@Test
	public void testCreateUser() {
				
		User testUser = user.createUser(new User("zeller4@yahoo.de", "Paull",
				"Sulzer", "test"));
		assertNotNull(testUser);
		
	}

	@Test
	public void testReturnUser() {
		UserDAOImpl userImp = new UserDAOImpl(new DatabaseConnectionManager(
				"admin", "admin", "localhost", 3306, "yetanothercalendar"));

		if (userImp.checkUser("zeller@yahoo.de", "test")) {
			User user = userImp.returnUser("zeller@yahoo.de");
			System.out.println("Email: " + user.getEmail() + " Vorname: "
					+ user.getForename() + " ID: " + user.getId()
					+ " Nachname: " + user.getLastname() + " Passwort: "
					+ user.getPasswordSHA1());
		} else {
			System.out.println("Email-Adresse oder Passwort sind falsch! "
					+ "Bitte ueberpruefen sie beides");
		}

	}

}
