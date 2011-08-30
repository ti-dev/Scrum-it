package ch.bsgroup.scrumit.dao;

import ch.bsgroup.scrumit.domain.Project;

import java.util.Set;

/**
 * IProject Dao
 */
public interface IProjectDao {
	public Project addProject(Project p);
	public void updateProject(Project p);
	public void removeProject(int projectId);
	public Set<Project> getAllProjects();
	public Project findProjectById(int projectId);
}