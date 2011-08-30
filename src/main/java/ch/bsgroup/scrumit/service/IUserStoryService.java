package ch.bsgroup.scrumit.service;

import java.util.Set;

import ch.bsgroup.scrumit.domain.UserStory;

/**
 * UserStory Service Interface
 */
public interface IUserStoryService {
	public UserStory addUserStory(UserStory u);
	public void updateUserStory(UserStory u);
	public void removeUserStory(int userstoryId);
	public Set<UserStory> getAllUserStorys();
	public UserStory findUserStoryById(int userstoryId);
	public Set<UserStory> getAllUserStorysBySprintId(int sprintId);
}