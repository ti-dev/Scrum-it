package ch.bsgroup.scrumit.service;

import ch.bsgroup.scrumit.domain.Person;
import ch.bsgroup.scrumit.domain.Project;
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
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * JUnit/DbUnit test for the Project
 */
public class ProjectTest extends DBTestCase {
	/**
	 * ProjectServiceImpl via Dependency Injection
	 */
	static ProjectServiceImpl service;

	/**
	 * Rows of project table
	 */
	private int projectRows = 3;

	/**
	 * Constructor of ProjectTest
	 */
	public ProjectTest() {
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
		service = (ProjectServiceImpl) context.getBean("projectServiceImpl");
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
	 * Test case: Create a new project and add it
	 */
	@Test
	public void testAddProject() {
		// fetch all projects from database
		Set<Project> projects = service.getAllProjects();

		// create new project
		Project project = new Project();
		project.setName("Scrum-it");
		project.setDescription("Scrum zum Anfassen");
		project.setCreationDate(new Date());

		Person userTest = new Person();
		userTest.setEmail("test@test.ab.cd");
		userTest.setFirstName("Fabian");
		userTest.setLastName("Doe");

		project.getPersons().add(userTest);
		service.addProject(project);

		// fetch now all projects from database
		Set<Project> newProjects = service.getAllProjects();

		// check the new amount of projects
		assertEquals(newProjects.size(), projects.size()+1);
	}

	/**
	 * Test case: Update one project
	 */
	@Test
	public void testUpdateProject() {
		// fetching the first project
		Set<Project> projects = service.getAllProjects();
		Iterator<Project> iterator = projects.iterator();
		if (iterator.hasNext()) {
			Project project = iterator.next();

			// change & save project
			project.setDescription("Scrum to touch");
			project.setName("Scrum-it reloaded");
			service.updateProject(project);

			// search project via id
			Project newProject = service.findProjectById(project.getId());

			// check the description and name
			assertEquals(newProject.getDescription(), project.getDescription());
			assertEquals(newProject.getName(), project.getName());
		}
	}

	/**
	 * Test case: Get all projects
	 */
	@Test
	public void testGetAllProjects() {
		// fetch all projects from database
		Set<Project> projects = service.getAllProjects();

		// check the number of projects
		assertEquals(projectRows, projects.size());
	}

	/**
	 * Test case: Find project by id
	 */
	@Test
	public void testFindProjectById() {
		// fetching the first project
		Set<Project> projects = service.getAllProjects();
		Iterator<Project> iterator = projects.iterator();
		if (iterator.hasNext()) {
			Project project = iterator.next();

			// search the equivalent project via id
			Project newProject = service.findProjectById(project.getId());

			// check the name
			assertEquals(project.getName(), newProject.getName());
		}
	}

	/**
	 * Test case: Remove all projects
	 */
	@Test
	public void testRemoveProject() {
		// get all projects from database
		Set<Project> projects = service.getAllProjects();
		Iterator<Project> iterator = projects.iterator();

		while (iterator.hasNext()) {
			Project project = iterator.next();
			service.removeProject(project.getId());
		}

		// check if there are not any projects there
		projects = service.getAllProjects();
		assertEquals(0, projects.size());
	}
}