package ch.bsgroup.scrumit.service;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;

public class UtilityTest {
	/**
	 * Initialize a database connection
	 * @return DatabaseConnection
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	protected static IDatabaseConnection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		try {
			return new DatabaseConnection(DriverManager.getConnection("jdbc:mysql://localhost/scrumit2", "root", "bsgroup"));
		} catch (DatabaseUnitException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected static void databaseProperties() {
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.jdbc.Driver");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:mysql://localhost/scrumit2");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "root");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "");
	}
}