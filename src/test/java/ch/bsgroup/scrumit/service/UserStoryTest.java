package ch.bsgroup.scrumit.service;

import ch.bsgroup.scrumit.domain.UserStory;
import ch.bsgroup.scrumit.service.impl.UserStoryServiceImpl;

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
 * JUnit/DbUnit test for the UserStory
 */
public class UserStoryTest extends DBTestCase {
	/**
	 * UserStoryServiceImpl via Dependency Injection
	 */
	static UserStoryServiceImpl service;

	/**
	 * Rows of userstory table
	 */
	private int userStoryRows = 4;

	/**
	 * Constructor of UserStoryTest
	 */
	public UserStoryTest() {
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
		service = (UserStoryServiceImpl) context.getBean("userStoryServiceImpl");
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
	 * Test case: Create a new UserStory and add it
	 */
	@Test
	public void testAddUserStory() {
		// fetch all userstorys from database
		Set<UserStory> userstorys = service.getAllUserStorys();

		// create new userstory
		UserStory userstory = new UserStory();
		userstory.setName("Drinks");
		userstory.setEstimatedSize(40);
		userstory.setCreationDate(new Date());
		userstory.setPriority(2);
		userstory.setAcceptanceTest("Should taste good!");

		service.addUserStory(userstory);

		// fetch now all userstorys from database
		Set<UserStory> newUserstorys = service.getAllUserStorys();

		// check the new amount of userstorys
		assertEquals(newUserstorys.size(), userstorys.size()+1);
	}

	/**
	 * Test case: Update one userstory
	 */
	@Test
	public void testUpdateUserStory() {
		// fetching the first userstory
		Set<UserStory> userstorys = service.getAllUserStorys();
		Iterator<UserStory> iterator = userstorys.iterator();
		if (iterator.hasNext()) {
			UserStory userstory = iterator.next();

			// change & save userstory
			userstory.setAcceptanceTest("Should taste even better than...");
			userstory.setEstimatedSize(100);
			service.updateUserStory(userstory);

			// search userstory via id
			UserStory newUserstory = service.findUserStoryById(userstory.getId());

			// check the acceptance test and the estimated size
			assertEquals(newUserstory.getAcceptanceTest(), userstory.getAcceptanceTest());
			assertEquals(newUserstory.getEstimatedSize(), userstory.getEstimatedSize());
		}		
	}

	/**
	 * Test case: Get all userstorys
	 */
	@Test
	public void testGetAllUserStorys() {
		// fetch all userstorys from database
		Set<UserStory> userstorys = service.getAllUserStorys();

		// check the number of userstorys
		assertEquals(userStoryRows, userstorys.size());
	}

	/**
	 * Test case: Find userstory by id
	 */
	@Test
	public void testFindUserStoryById() {
		// fetching the first userstory
		Set<UserStory> userstorys = service.getAllUserStorys();
		Iterator<UserStory> iterator = userstorys.iterator();
		if (iterator.hasNext()) {
			UserStory userstory = iterator.next();

			// search the equivalent userstory via id
			UserStory newUserstory = service.findUserStoryById(userstory.getId());

			// check the acceptance test
			assertEquals(newUserstory.getAcceptanceTest(), userstory.getAcceptanceTest());
		}
	}

	/**
	 * Test case: Remove all userstorys
	 */
	@Test
	public void testRemoveUserstory() {		
		// get all userstorys from database
		Set<UserStory> userstorys = service.getAllUserStorys();
		Iterator<UserStory> iterator = userstorys.iterator();

		if (iterator.hasNext()) {
			UserStory userstory = iterator.next();
			service.removeUserStory(userstory.getId());

			// check if there are not any userstorys there
			Set<UserStory> newUserstorys = service.getAllUserStorys();
			assertEquals(newUserstorys.size(), userstorys.size()-1);
		}
	}
}