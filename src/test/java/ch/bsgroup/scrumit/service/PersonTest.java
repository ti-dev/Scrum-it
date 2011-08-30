package ch.bsgroup.scrumit.service;

import ch.bsgroup.scrumit.domain.Person;
import ch.bsgroup.scrumit.service.impl.PersonServiceImpl;
import ch.bsgroup.scrumit.service.impl.ProjectServiceImpl;

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
import java.util.Iterator;
import java.util.Set;

/**
 * JUnit/DbUnit test for the Person
 */
public class PersonTest extends DBTestCase{
	/**
	 * PersonServiceImpl & ProjectServiceImpl via Dependency Injection
	 */
	static PersonServiceImpl service;
	static ProjectServiceImpl serviceProj;

	/**
	 * Rows of person table
	 */
	private int personRows = 3;

	/**
	 * Constructor of PersonTest
	 */
	public PersonTest() {
		UtilityTest.databaseProperties();
		try {
			this.setUpBeforeClass();
		} catch (Exception ex) {
			System.out.println("Constructor: "+ex.getMessage());
		}
	}

	/**
	 * Fixture logic runs once - Initialization Spring, Reference obtaining from Spring, Invoking DbUnit
	 * @throws Exception
	 */
	public void setUpBeforeClass() throws Exception {
		final ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		service = (PersonServiceImpl) context.getBean("personServiceImpl");
		serviceProj = (ProjectServiceImpl) context.getBean("projectServiceImpl");
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
	 * Test case: Create a new person and add it
	 */
	@Test
	public void testAddPerson() {
		// fetch all persons from database
		Set<Person> persons = service.getAllPersons();

		// create new person
		Person person = new Person();
		person.setEmail("newMail@test.de");
		person.setFirstName("new firstName");
		person.setLastName("new lastName");

		service.addPerson(person);

		// fetch now all persons from database
		Set<Person> newPersons = service.getAllPersons();

		// check the new amount of persons
		assertEquals(newPersons.size(), persons.size()+1);
	}

	/**
	 * Test case: Update one person
	 */
	@Test
	public void testUpdatePerson() {
		// fetching the first person
		Set<Person> persons = service.getAllPersons();
		Iterator<Person> iterator = persons.iterator();
		if (iterator.hasNext()) {
			Person person = iterator.next();

			// change & save person
			person.setEmail("updateMail@test.de");
			person.setFirstName("update firstname");
			service.updatePerson(person);

			// search person via id
			Person newPerson = service.findPersonById(person.getId());

			// check the email and firstName
			assertEquals(newPerson.getEmail(), person.getEmail());
			assertEquals(newPerson.getFirstName(), person.getFirstName());
		}
	}

	/**
	 * Test case: Get all persons
	 */
	@Test
	public void testGetAllPersons() {
		// fetch all persons from database
		Set<Person> persons = service.getAllPersons();

		// check the number of persons
		assertEquals(personRows, persons.size());
	}

	/**
	 * Test case: Find person by id
	 */
	@Test
	public void testFindPersonById() {
		// fetching the first person
		Set<Person> persons = service.getAllPersons();
		Iterator<Person> iterator = persons.iterator();
		if (iterator.hasNext()) {
			Person person = iterator.next();
			// search the equivalent person via id
			Person newPerson = service.findPersonById(person.getId());

			// check the firstName
			assertEquals(person.getFirstName(), newPerson.getFirstName());
		}		
	}

	/**
	 * Test case: Remove all persons
	 */
	@Test
	public void testRemovePerson() {
		// get all persons from database
		Set<Person> persons = service.getAllPersons();
		Iterator<Person> iterator = persons.iterator();

		if (iterator.hasNext()) {
			Person person = iterator.next();
			service.removePerson(person.getId());

			// check if there are not any persons there
			Set<Person> newPersons = service.getAllPersons();
			assertEquals(newPersons.size(), persons.size()-1);
		}
	}
}