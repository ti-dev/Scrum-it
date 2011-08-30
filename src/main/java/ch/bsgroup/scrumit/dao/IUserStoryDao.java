package ch.bsgroup.scrumit.dao;

import java.util.Set;

import ch.bsgroup.scrumit.domain.UserStory;

/**
 * IUserStory Dao
 */
public interface IUserStoryDao {
	public UserStory addUserStory(UserStory u);
	public void updateUserStory(UserStory u);
	public void removeUserStory(int userstoryId);
	public Set<UserStory> getAllUserStorys();
	public UserStory findUserStoryById(int userstoryId);
	public Set<UserStory> getAllUserStorysBySprintId(int sprintId);
}