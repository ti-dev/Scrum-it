package ch.bsgroup.scrumit.service;

import java.io.FileOutputStream;
import java.sql.DriverManager;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
//import org.dbunit.database.QueryDataSet;
//import org.dbunit.database.search.TablesDependencyHelper;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

import com.mysql.jdbc.*;

/**
 * DatabaseExport to a XML file - DbUnit
 */
public class DatabaseExport {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		// database connection
		@SuppressWarnings("unused")
		Class driverClass = Class.forName("com.mysql.jdbc.Driver");
		Connection jdbcConnection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/scrumit2", "root", "bsgroup");
		IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

		// partial database export
		//QueryDataSet partialDataSet = new QueryDataSet(connection);
		//partialDataSet.addTable("FOO", "SELECT * FROM TABLE WHERE COL='VALUE'");
		//partialDataSet.addTable("BAR");
		//FlatXmlDataSet.write(partialDataSet, new FileOutputStream("partial.xml"));

		// full database export
		IDataSet fullDataSet = connection.createDataSet();
		FlatXmlDataSet.write(fullDataSet, new FileOutputStream("full.xml"));

		// dependent tables database export: export table X and all tables that
		// have a PK which is a FK on X, in the right order for insertion
		//String[] depTableNames = TablesDependencyHelper.getAllDependentTables(connection, "X");
		//IDataSet depDataset = connection.createDataSet(depTableNames);
		//FlatXmlDataSet.write(depDataset, new FileOutputStream("dependents.xml"));
	}
}