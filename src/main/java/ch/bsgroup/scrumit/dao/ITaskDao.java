package ch.bsgroup.scrumit.dao;

import java.util.Set;

import ch.bsgroup.scrumit.domain.Task;

/**
 * ITask Dao
 */
public interface ITaskDao {
	public Task addTask(Task t);
	public void updateTask(Task t);
	public void removeTask(int taskId);
	public Set<Task> getAllTasks();
	public Task findTaskById(int taskId);
	public Set<Task> getAllTasksByUserstoryId(int userstoryId);
}