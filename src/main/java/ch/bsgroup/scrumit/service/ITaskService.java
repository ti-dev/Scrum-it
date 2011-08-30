package ch.bsgroup.scrumit.service;

import java.util.Set;

import ch.bsgroup.scrumit.domain.Task;

/**
 * Task Service Interface
 */
public interface ITaskService {
	public Task addTask(Task t);
	public void updateTask(Task t);
	public void removeTask(int taskId);
	public Set<Task> getAllTasks();
	public Task findTaskById(int taskId);
	public Set<Task> getAllTasksByUserstoryId(int userstoryId);
}