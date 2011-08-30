package ch.bsgroup.scrumit.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;

/**
 * Class Task manages Tasks
 * 		Every Task is defined by a unique Id
 * 		It also has a Description, a xCoord/YCoord pair, Status, Duration, CreationDate
 */
@Entity
public class Task {
	/**
	 * Unique Id of the UserStory to identify it
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	/**
	 * Task has a Description
	 */
	private String description;

	/**
	 * Task has a X coordinate
	 */
	private int xCoord;

	/**
	 * Task has a Y coordinate
	 */
	private int yCoord;

	/**
	 * Task has a Status
	 */
	@Audited
	private int status;

	/**
	 * Task has a Duration
	 */
	private int duration;

	/**
	 * Project has a CreationDate
	 */
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date creationDate;

	/**
	 * Task is part of a UserStory
	 */
	@ManyToOne
	@JoinColumn(name="userstory_id", referencedColumnName="id", nullable=true, updatable=false, insertable=true)
	private UserStory userStory;

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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
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
	 * @return the userStory
	 */
	public UserStory getUserStory() {
		return userStory;
	}

	/**
	 * @param userStory the userStory to set
	 */
	public void setUserStory(UserStory userStory) {
		this.userStory = userStory;
	}
}