package ch.bsgroup.scrumit.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Class Sprint manages Sprint within a specific Project
 * 		Every Sprint is defined by a unique Id and has a Slogan
 * 		The StartDate and EndDate is the time frame
 */
public class SerializableSprint {
	/**
	 * Unique Id of the Sprint to identify it
	 */
	private int id;

	/**
	 * Every Sprint has a Slogan
	 */
	private String slogan;

	/**
	 * Sprint has a StartDate
	 */
	private Date startDate;

	/**
	 * Sprint has an EndDate
	 */
	private Date endDate;

	/**
	 * Sprint is joined with a Project
	 */
	private SerializableProject project;

	/**
	 * Sprint has UserStorys
	 */
	private Set<SerializableUserStory> userStories = new HashSet<SerializableUserStory>();

	/**
	 * @desc Constructor
	 */
	public SerializableSprint(int id, String slogan, Date startDate, Date endDate/*, SerializableProject project, Set<SerializableUserStory> userStories*/) {
		this.setId(id);
		this.setSlogan(slogan);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
		//this.setProject(project);
		//this.setUserStories(userStories);
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the slogan
	 */
	public String getSlogan() {
		return slogan;
	}

	/**
	 * @param slogan the slogan to set
	 */
	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the project
	 */
	public SerializableProject getProject() {
		return project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(SerializableProject project) {
		this.project = project;
	}

	/**
	 * @return the userStories
	 */
	public Set<SerializableUserStory> getUserStories() {
		return userStories;
	}

	/**
	 * @param userStories the userStories to set
	 */
	public void setUserStories(Set<SerializableUserStory> userStories) {
		this.userStories = userStories;
	}
}