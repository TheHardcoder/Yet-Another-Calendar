package de.yetanothercalendar.model.database.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Statement;

/**
 * Ueber die einfache Klasse {@link DatabaseConnectionManager} kann man sich
 * eine Verbindung zur Datenbank erstellen
 */
public class DatabaseConnectionManager {

	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";

	private String username;
	private String password;
	private String host;
	private int port;
	private String dbname;

	/**
	 * Erstellt einen neuen {@link DatabaseConnectionManager} mit den Standard
	 * Verbindungseinstellungen
	 */
	public DatabaseConnectionManager() {
		username = "yac";
		password = "yacyac";
		host = "localhost";
		dbname = "yetanothercalendar";
		port = 3306;
	}

	/**
	 * Neuen {@link DatabaseConnectionManager} mit den folgenden Verbindungs
	 * properties
	 * 
	 * @param username
	 *            Benutzername zur Datenbank
	 * @param password
	 *            Passwort zur Datenbank
	 * @param host
	 *            Der Host zur Datenbank
	 * @param port
	 *            Der Port zur Datenbank
	 */
	public DatabaseConnectionManager(String username, String password,
			String host, int port, String dbname) {
		super();
		this.username = username;
		this.password = password;
		this.host = host;
		this.port = port;
		this.dbname = dbname;
	}

	/**
	 * Ueber diese Methode kann man eine Verbindungsinstanzu zur DB erhalten.
	 * 
	 * @return Eine Verbindung zur Datenbank mit den festgelegten
	 *         Verbindungseigenschaften
	 * @throws SQLException
	 *             bei fehlerhaftem Verbindungsaufbau
	 */
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.username);
		connectionProps.put("password", this.password);
		try {
			Class.forName(DRIVER_NAME).newInstance();
		} catch (InstantiationException e) {
			System.err
					.println("Der Mysql Treiber konnte nicht instanziiert werden");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.err.println("Der Zugriff wurde verweigert");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err
					.println("Die Treiberklasse konnte nicht gefunden werden");
			e.printStackTrace();
		}
		String conUrl = "jdbc:mysql://" + host + ":" + port + "/" + dbname;
		conn = DriverManager.getConnection(conUrl, connectionProps);
		return conn;
	}
	
	public Connection getConnectionWithoutDBName() throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.username);
		connectionProps.put("password", this.password);
		
		try {
			Class.forName(DRIVER_NAME).newInstance();
		} catch (InstantiationException e) {
			System.err
					.println("Der Mysql Treiber konnte nicht instanziiert werden");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.err.println("Der Zugriff wurde verweigert");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err
					.println("Die Treiberklasse konnte nicht gefunden werden");
			e.printStackTrace();
		}
		String conUrl = "jdbc:mysql://" + host + ":" + port;
		conn = DriverManager.getConnection(conUrl, connectionProps);
		
		return conn;
	}
	
	public void executeQuery(String query)throws SQLException{
		java.sql.Statement stmt = null;
			Connection con = getConnectionWithoutDBName();
			stmt = con.createStatement();	      
		    stmt.executeUpdate(query);
		    stmt.close();
		    con.close();
			
			
	}

}
