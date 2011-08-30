package ch.bsgroup.scrumit.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Class UserStory manages UserStory
 * 		Every UserStory is defined by a unique Id
 * 		It also has a Name, a Priority, CreationDate, EstimatedSize, an AcceptanceTest and a xCoord/YCoord pair
 */
public class SerializableUserStory {
	/**
	 * Unique Id of the UserStory to identify it
	 */
	private int id;

	/**
	 * UserStory has a Name
	 */
	private String name;

	/**
	 * UserStory has a priority
	 */
	private int priority;

	/**
	 * UserStory has a CreationDate
	 */
	private Date creationDate;

	/**
	 * UserStory has an EstimatedSize
	 */
	private int estimatedSize;

	/**
	 * UserStory has an AcceptanceTest
	 */
	private String acceptanceTest;

	/**
	 * Task has a X coordinate
	 */
	private int xCoord;

	/**
	 * Task has a Y coordinate
	 */
	private int yCoord;

	/**
	 * UserStory has Sprints
	 */
	private Set<SerializableSprint> sprints = new HashSet<SerializableSprint>();

	/**
	 * UserStory has Tasks
	 */
	private Set<SerializableTask> tasks = new HashSet<SerializableTask>();

	/**
	 * @desc Constructor
	 */
	public SerializableUserStory(int id, String name/*, int priority, Date creationDate, int estimatedSize, 
			String acceptanceTest, int xCoord, int yCoord, 
			Set<SerializableSprint> sprints, Set<SerializableTask> tasks*/) {
		this.setId(id);
		this.setName(name);
		//this.setPriority(priority);
		//this.setCreationDate(creationDate);
		//this.setEstimatedSize(estimatedSize);
		//this.setAcceptanceTest(acceptanceTest);
		//this.setxCoord(xCoord);
		//this.setyCoord(yCoord);
		//this.setSprints(sprints);
		//this.setTasks(tasks);
	}

	public SerializableUserStory(int id, String name, int priority, Date creationDate, int estimatedSize, 
			String acceptanceTest/*, int xCoord, int yCoord, 
			Set<SerializableSprint> sprints, Set<SerializableTask> tasks*/) {
		this.setId(id);
		this.setName(name);
		this.setPriority(priority);
		this.setCreationDate(creationDate);
		this.setEstimatedSize(estimatedSize);
		this.setAcceptanceTest(acceptanceTest);
	}

	public SerializableUserStory(int id, String name, int xCoord, int yCoord) {
		this.setId(id);
		this.setName(name);
		this.setxCoord(xCoord);
		this.setyCoord(yCoord);
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the estimatedSize
	 */
	public int getEstimatedSize() {
		return estimatedSize;
	}

	/**
	 * @param estimatedSize the estimatedSize to set
	 */
	public void setEstimatedSize(int estimatedSize) {
		this.estimatedSize = estimatedSize;
	}

	/**
	 * @return the acceptanceTest
	 */
	public String getAcceptanceTest() {
		return acceptanceTest;
	}

	/**
	 * @param acceptanceTest the acceptanceTest to set
	 */
	public void setAcceptanceTest(String acceptanceTest) {
		this.acceptanceTest = acceptanceTest;
	}

	/**
	 * @return the xCoord
	 */
	public int getxCoord() {
		return xCoord;
	}

	/**
	 * @param xCoord the xCoord to set
	 */
	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	/**
	 * @return the yCoord
	 */
	public int getyCoord() {
		return yCoord;
	}

	/**
	 * @param yCoord the yCoord to set
	 */
	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}

	/**
	 * @return the sprints
	 */
	public Set<SerializableSprint> getSprints() {
		return sprints;
	}

	/**
	 * @param sprints the sprints to set
	 */
	public void setSprints(Set<SerializableSprint> sprints) {
		this.sprints = sprints;
	}

	/**
	 * @return the tasks
	 */
	public Set<SerializableTask> getTasks() {
		return tasks;
	}

	/**
	 * @param tasks the tasks to set
	 */
	public void setTasks(Set<SerializableTask> tasks) {
		this.tasks = tasks;
	}
}