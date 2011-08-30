package ch.bsgroup.scrumit.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.envers.Audited;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Class UserStory manages UserStory
 * 		Every UserStory is defined by a unique Id
 * 		It also has a Name, a Priority, CreationDate, EstimatedSize, an AcceptanceTest and a xCoord/YCoord pair
 */
@Entity
public class UserStory {
	/**
	 * Unique Id of the UserStory to identify it
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	/**
	 * UserStory has a Name
	 */
	@NotNull
	@Size(min = 1, max = 255)
	@Audited
	private String name;

	/**
	 * UserStory has a priority
	 */
	@NotNull
	@Max(10000)
	@Min(1)
	@Audited
	private Integer priority;

	/**
	 * UserStory has a CreationDate
	 */
	@DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date creationDate;

	/**
	 * UserStory has an EstimatedSize
	 */
	@NotNull
	@Max(10000)
	@Min(1)
	@Audited
	private int estimatedSize;

	/**
	 * UserStory has an AcceptanceTest
	 */
	@NotNull
	@Size(min = 1, max = 255)
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
	@JsonIgnore
	@ManyToMany(mappedBy="userStories")
	private Set<Sprint> sprints = new HashSet<Sprint>();

	/**
	 * UserStory has Tasks
	 */
	@JsonIgnore
	@OneToMany
	@JoinColumn(name="userstory_id", referencedColumnName="id", updatable=true)
	private Set<Task> tasks = new HashSet<Task>();

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
	public Set<Sprint> getSprints() {
		return sprints;
	}

	/**
	 * @param sprints the sprints to set
	 */
	public void setSprints(Set<Sprint> sprints) {
		this.sprints = sprints;
	}

	/**
	 * @return the tasks
	 */
	public Set<Task> getTasks() {
		return tasks;
	}

	/**
	 * @param tasks the tasks to set
	 */
	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}
}