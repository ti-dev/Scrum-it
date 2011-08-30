package ch.bsgroup.scrumit.service;

import ch.bsgroup.scrumit.domain.Project;

import java.util.Set;

/**
 * Project Service Interface
 */
public interface IProjectService {
	public Project addProject(Project p);
	public void updateProject(Project p);
	public void removeProject(int projectId);
	public Set<Project> getAllProjects();
	public Project findProjectById(int projectId);
}