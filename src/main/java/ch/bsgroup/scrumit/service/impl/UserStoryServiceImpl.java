package ch.bsgroup.scrumit.service.impl;

import java.util.Set;

import ch.bsgroup.scrumit.dao.IUserStoryDao;
import ch.bsgroup.scrumit.dao.impl.UserStoryDaoImplHibernate;
import ch.bsgroup.scrumit.service.IUserStoryService;
import ch.bsgroup.scrumit.domain.UserStory;

/**
 * UserStory Service Implementation
 */
public class UserStoryServiceImpl implements IUserStoryService {
	/**
	 * DAO binding
	 */
	private IUserStoryDao userstoryDao;

	public void setUserStoryDao(IUserStoryDao value) {
		userstoryDao = value;
	}

	/**
	 * Constructor
	 */
	public UserStoryServiceImpl() {
		userstoryDao = new UserStoryDaoImplHibernate();
	}

	/**
	 * Service calls (delegation)
	 */
	public UserStory addUserStory(UserStory u) {
		return userstoryDao.addUserStory(u);
	}

	public void updateUserStory(UserStory u) {
		/*Set<Sprint> sprints = u.getSprints();
		Iterator<Sprint> iterator = sprints.iterator();
		while (iterator.hasNext()) {
			Sprint sprint = iterator.next();
			boolean update = false;
			if (sprint.getUserStories().contains(u)) {
				if (!u.getSprints().contains(sprint)) {
					// sprint has this userstory, but userstory does not have this sprint anymore
					// -> delete sprint from userstory
					sprint.getUserStories().remove(u);
					update = true;
				}
			} else {
				// sprint does not have this userstory, should be added
				sprint.getUserStories().add(u);
				update = true;
			}

			if (update) {
				sprintDao.updateSprint(sprint);
			}
		}*/
		userstoryDao.updateUserStory(u);
	}

	public void removeUserStory(int userstoryId) {
		/*UserStory userstory = findUserStoryById(userstoryId);
		Set<Sprint> sprints = userstory.getSprints();

		// delete all entries in Sprint_UserStory for this userstory
		Iterator<Sprint> iterator = sprints.iterator();
		while (iterator.hasNext()) {
			Sprint sprint = iterator.next();

			if (sprint.getUserStories().contains(userstory)) {
				sprint.getUserStories().remove(userstory);
				sprintDao.updateSprint(sprint);
			}
		}*/
		userstoryDao.removeUserStory(userstoryId);
	}

	public Set<UserStory> getAllUserStorys() {
		return userstoryDao.getAllUserStorys();
	}

	public UserStory findUserStoryById(int userstoryId) {
		return userstoryDao.findUserStoryById(userstoryId);
	}

	public Set<UserStory> getAllUserStorysBySprintId(int sprintId) {
		return userstoryDao.getAllUserStorysBySprintId(sprintId);
	}
}