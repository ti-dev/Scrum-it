package ch.bsgroup.scrumit.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Class Project manages Projects
 * 		A Project consists of Sprints and Persons which are involved with a role
 * 		A Project is defined by the unique Id. It also has a Name and a Description, also a CreationDate
 */
@Entity
public class Project {
	/**
	 * Unique Id of the Project to identify it
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	/**
	 * Project has a Name
	 */
	@NotNull
	@Size(min = 1, max = 255)
	private String name;

	/**
	 * Project has a Description
	 */
	@NotNull
	@Size(min = 1, max = 255)
	private String description;

	/**
	 * Project has a CreationDate
	 */
	@DateTimeFormat(pattern = "dd.MM.yy HH:mm:ss")
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date creationDate;

	/**
	 * Project has a list of Persons - mapping owner
	 */
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
		@JoinTable(name = "Project_Person",
			joinColumns = {
				@JoinColumn(name="project_id", referencedColumnName="id")
			},
			inverseJoinColumns = {
				@JoinColumn(name="person_id", referencedColumnName="id")
			}
		)
	private Set<Person> persons = new HashSet<Person>();
	
	/**
	 * Project has a list of Sprints - mapping owner
	 */
	@JsonIgnore
	@OneToMany
	@JoinColumn(name="project_id", referencedColumnName="id")
	private Set<Sprint> sprints = new HashSet<Sprint>();
	
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
	 * @return the persons
	 */
	public Set<Person> getPersons() {
		return persons;
	}

	/**
	 * @param persons the persons to set
	 */
	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}
}