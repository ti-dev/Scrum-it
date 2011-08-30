package ch.bsgroup.scrumit.service.impl;

import java.util.Set;

import ch.bsgroup.scrumit.dao.IProjectDao;
import ch.bsgroup.scrumit.dao.impl.ProjectDaoImplHibernate;
import ch.bsgroup.scrumit.service.IProjectService;
import ch.bsgroup.scrumit.domain.Project;

/**
 * Project Service Implementation
 */
public class ProjectServiceImpl implements IProjectService {
	/**
	 * DAO binding
	 */
	private IProjectDao projectDao;

	public void setProjectDao(IProjectDao value) {
		projectDao = value;
	}

	/**
	 * Constructor
	 */
	public ProjectServiceImpl() {
		projectDao = new ProjectDaoImplHibernate();
	}

	/**
	 * Service calls (delegation)
	 */
	public Project addProject(Project p) {
		return projectDao.addProject(p);
	}

	public void updateProject(Project p) {
		projectDao.updateProject(p);
	}

	public void removeProject(int projectId) {
		projectDao.removeProject(projectId);
	}

	public Set<Project> getAllProjects() {
		return projectDao.getAllProjects();
	}

	public Project findProjectById(int projectId) {
		return projectDao.findProjectById(projectId);
	}
}