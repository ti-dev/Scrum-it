package ch.bsgroup.scrumit.service.impl;

import java.util.Set;

import ch.bsgroup.scrumit.dao.ITaskDao;
import ch.bsgroup.scrumit.dao.impl.TaskDaoImplHibernate;
import ch.bsgroup.scrumit.service.ITaskService;
import ch.bsgroup.scrumit.domain.Task;

/**
 * Task Service Implementation
 */
public class TaskServiceImpl implements ITaskService {
	/**
	 * DAO binding
	 */
	private ITaskDao taskDao;

	public void setTaskDao(ITaskDao value) {
		taskDao = value;
	}

	/**
	 * Constructor
	 */
	public TaskServiceImpl() {
		taskDao = new TaskDaoImplHibernate();
	}

	/**
	 * Service calls (delegation)
	 */
	public Task addTask(Task t) {
		return taskDao.addTask(t);
	}

	public void updateTask(Task t) {
		taskDao.updateTask(t);
	}

	public void removeTask(int taskId) {
		taskDao.removeTask(taskId);
	}

	public Set<Task> getAllTasks() {
		return taskDao.getAllTasks();
	}

	public Task findTaskById(int taskId) {
		return taskDao.findTaskById(taskId);
	}

	public Set<Task> getAllTasksByUserstoryId(int userstoryId) {
		return taskDao.getAllTasksByUserstoryId(userstoryId);
	}
}