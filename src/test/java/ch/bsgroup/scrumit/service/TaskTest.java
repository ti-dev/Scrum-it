package ch.bsgroup.scrumit.service;

import ch.bsgroup.scrumit.domain.Task;
import ch.bsgroup.scrumit.service.impl.TaskServiceImpl;

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
 * JUnit/DbUnit test for the Task
 */
public class TaskTest extends DBTestCase {
	/**
	 * TaskServiceImpl via Dependency Injection
	 */
	static TaskServiceImpl service;

	/**
	 * Rows of task table
	 */
	private int taskRows = 5;

	/**
	 * Constructor of TaskTest
	 */
	public TaskTest() {
		UtilityTest.databaseProperties();
		try {
			setUpBeforeClass();
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
		service = (TaskServiceImpl) context.getBean("taskServiceImpl");
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
	 * Test case: Create a new Task and add it
	 */
	@Test
	public void testAddTask() {
		// fetch all tasks from database
		Set<Task> tasks = service.getAllTasks();

		// create new task
		Task task = new Task();
		task.setDescription("work");
		task.setDuration(8);
		task.setCreationDate(new Date());
		task.setxCoord(600);
		task.setyCoord(500);
		task.setStatus(2);
		service.addTask(task);

		// fetch now all tasks from database
		Set<Task> newTasks = service.getAllTasks();

		// check the new amount of tasks
		assertEquals(newTasks.size(), tasks.size()+1);
	}

	/**
	 * Test case: Update one task
	 */
	@Test
	public void testUpdateTask() {
		// fetching the first task
		Set<Task> tasks = service.getAllTasks();
		Iterator<Task> iterator = tasks.iterator();
		if (iterator.hasNext()) {
			Task task = iterator.next();

			// change & save task
			task.setDescription("work to do...");
			task.setyCoord(550);
			service.updateTask(task);

			// search task via id
			Task newTask = service.findTaskById(task.getId());

			// check the description
			assertEquals(newTask.getDescription(), task.getDescription());			
		}
	}

	/**
	 * Test case: Get all tasks
	 */
	@Test
	public void testGetAllTasks() {
		// fetch all tasks from database
		Set<Task> tasks = service.getAllTasks();

		// check the number of tasks
		assertEquals(taskRows, tasks.size());
	}

	/**
	 * Test case: Find task by id
	 */
	@Test
	public void testFindTaskById() {
		// fetching the first task
		Set<Task> tasks = service.getAllTasks();
		Iterator<Task> iterator = tasks.iterator();
		if (iterator.hasNext()) {
			Task task = iterator.next();

			// search the equivalent task via id
			Task newTask = service.findTaskById(task.getId());

			// check the duration
			assertEquals(task.getDuration(), newTask.getDuration());
		}
	}

	/**
	 * Test case: Remove all tasks
	 */
	@Test
	public void testRemoveTask() {
		// get all tasks from database
		Set<Task> tasks = service.getAllTasks();
		Iterator<Task> iterator = tasks.iterator();

		while (iterator.hasNext()) {
			Task task = iterator.next();
			service.removeTask(task.getId());
		}

		// check if there are not any tasks there
		tasks = service.getAllTasks();
		assertEquals(0, tasks.size());
	}
}