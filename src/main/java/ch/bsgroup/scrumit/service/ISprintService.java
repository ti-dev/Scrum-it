package ch.bsgroup.scrumit.service;

import java.util.Set;

import ch.bsgroup.scrumit.domain.Sprint;

/**
 * Sprint Service Interface
 */
public interface ISprintService {
	public Sprint addSprint(Sprint s);
	public void updateSprint(Sprint s);
	public void removeSprint(int sprintId);
	public Set<Sprint> getAllSprints();
	public Sprint findSprintById(int sprintId);
	public Set<Sprint> getAllSprintsByProjectId(int projectId);
}