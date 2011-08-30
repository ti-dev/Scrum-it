package ch.bsgroup.scrumit.service.impl;

import java.util.Set;

import ch.bsgroup.scrumit.dao.ISprintDao;
import ch.bsgroup.scrumit.dao.impl.SprintDaoImplHibernate;
import ch.bsgroup.scrumit.service.ISprintService;
import ch.bsgroup.scrumit.domain.Sprint;

/**
 * Sprint Service Implementation
 */
public class SprintServiceImpl implements ISprintService {
	/**
	 * DAO binding
	 */
	private ISprintDao sprintDao;

	public void setSprintDao(ISprintDao value) {
		sprintDao = value;
	}

	/**
	 * Constructor
	 */
	public SprintServiceImpl() {
		sprintDao = new SprintDaoImplHibernate();
	}

	/**
	 * Service calls (delegation)
	 */
	public Sprint addSprint(Sprint s) {
		return sprintDao.addSprint(s);
	}

	public void updateSprint(Sprint s) {
		sprintDao.updateSprint(s);
	}

	public void removeSprint(int sprintId) {
		sprintDao.removeSprint(sprintId);
	}

	public Set<Sprint> getAllSprints() {
		return sprintDao.getAllSprints();
	}

	public Sprint findSprintById(int sprintId) {
		return sprintDao.findSprintById(sprintId);
	}

	public Set<Sprint> getAllSprintsByProjectId(int projectId) {
		return sprintDao.getAllSprintsByProjectId(projectId);
	}
}