package ch.bsgroup.scrumit.service;

import ch.bsgroup.scrumit.domain.Sprint;
import ch.bsgroup.scrumit.service.impl.SprintServiceImpl;

import org.dbunit.DBTestCase;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * JUnit/DbUnit test for the Sprint
 */
public class SprintTest extends DBTestCase {
	/**
	 * SprintServiceImpl via Dependency Injection
	 */
	static SprintServiceImpl service;

	/**
	 * Rows of sprint table
	 */
	private int sprintRows = 4;

	/**
	 * Constructor of SprintTest
	 */
	public SprintTest() {
		UtilityTest.databaseProperties();
		try {
			this.setUpBeforeClass();
		} catch (Exception ex) {
			System.out.println("Constructor: "+ex.getMessage());
		}
	}

	/**
	 * Fixture logic runs once - Initialization Spring, Reference obtaining from Spring, Invoking the DbUnit
	 * @throws Exception
	 */
	public void setUpBeforeClass() throws Exception {
		final ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		service = (SprintServiceImpl) context.getBean("sprintServiceImpl");
	}

	/**
	 * Connection to the database and performing a clean data insert
	 * @throws Exception
	 */
	protected void handleSetUpOperation() throws Exception {
		final IDatabaseConnection connection = UtilityTest.getConnection();
		final IDataSet data = getDataSet();
		try {
			DatabaseOperation.CLEAN_INSERT.execute(connection, data);
		} finally {
			connection.close();
		}
	}

	/**
	 * Build the xml dataset file
	 * @return IDataSet - XML dataset
	 * @throws IOException
	 * @throws DataSetException
	 */
	protected IDataSet getDataSet() throws IOException, DataSetException {
		return new FlatXmlDataSetBuilder().build(new FileInputStream("full.xml"));
	}

	/**
	 * Initialization before test method
	 * @throws Exception
	 */
	@Before
	public void setUpBefore() throws Exception {
		handleSetUpOperation();
	}

	/**
	 * must be implemented, otherwise warnings
	 */
	protected void setUpDatabaseConfig(DatabaseConfig config) {
		config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
	}

	/**
	 * Test case: Create a new Sprint and add it
	 */
	@Test
	public void testAddSprint() {
		// fetch all sprints from database
		Set<Sprint> sprints = service.getAllSprints();

		// create new sprint
		Sprint sprint = new Sprint();
		sprint.setSlogan("Pizza and Pasta");
		sprint.setStartDate(new Date());
		sprint.setEndDate(new Date());

		service.addSprint(sprint);

		// fetch now all sprints from database
		Set<Sprint> newSprints = service.getAllSprints();

		// check the new amount of sprints
		assertEquals(newSprints.size(), sprints.size()+1);
	}

	/**
	 * Test case: Update one sprint
	 */
	@Test
	public void testUpdateSprint() {
		// fetching the first sprint
		Set<Sprint> sprints = service.getAllSprints();
		Iterator<Sprint> iterator = sprints.iterator();
		if (iterator.hasNext()) {
			Sprint sprint = iterator.next();

			// change & save sprint
			sprint.setSlogan("All the good things");
			sprint.setEndDate(new Date());
			service.updateSprint(sprint);

			// search sprint via id
			Sprint newSprint = service.findSprintById(sprint.getId());

			// check the slogan
			assertEquals(newSprint.getSlogan(), sprint.getSlogan());
		}
	}

	/**
	 * Test case: Get all sprints
	 */
	@Test
	public void testGetAllSprints() {
		// fetch all sprints from database
		Set<Sprint> sprints = service.getAllSprints();

		// check the number of sprints
		assertEquals(sprintRows, sprints.size());
	}

	/**
	 * Test case: Find sprint by id
	 */
	@Test
	public void testFindSprintById() {
		// fetching the first sprint
		Set<Sprint> sprints = service.getAllSprints();
		Iterator<Sprint> iterator = sprints.iterator();
		if (iterator.hasNext()) {
			Sprint sprint = iterator.next();

			// search the equivalent sprint via id
			Sprint newSprint = service.findSprintById(sprint.getId());

			// check the slogan
			assertEquals(sprint.getSlogan(), newSprint.getSlogan());
		}
	}

	/**
	 * Test case: Remove all sprints
	 */
	@Test
	public void testRemoveSprint() {
		// get all sprints from database
		Set<Sprint> sprints = service.getAllSprints();
		Iterator<Sprint> iterator = sprints.iterator();

		while (iterator.hasNext()) {
			Sprint sprint = iterator.next();
			service.removeSprint(sprint.getId());
		}

		// check if there are not any sprints there
		sprints = service.getAllSprints();
		assertEquals(0, sprints.size());
	}
}