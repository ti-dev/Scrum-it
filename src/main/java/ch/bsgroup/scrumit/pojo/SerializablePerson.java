package ch.bsgroup.scrumit.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Class Person manages Persons
 * 		A Person is involved in Projects with a role
 * 		A Person is defined by the unique Id. It also has an Email, a Firstname and a Lastname
 */
public class SerializablePerson {
	/**
	 * Unique Id of the Person to identify it
	 */
	private int id;

	/**
	 * Person has a FirstName
	 */
	private String firstName;

	/**
	 * Person has a LastName
	 */
	private String lastName;

	/**
	 * Person has an Email
	 */
	private String email;

	/**
	 * Person has associated projects
	 */
	private Set<SerializableProject> projects = new HashSet<SerializableProject>();

	/**
	 * @desc Constructor
	 */
	public SerializablePerson(int id, String firstName, String lastName/*, String email, Set<SerializableProject> projects*/) {
		this.setId(id);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		//this.setEmail(email);
		//this.setProjects(projects);
	}

	public SerializablePerson(int id, String firstName, String lastName, String email/*, Set<SerializableProject> projects*/) {
		this.setId(id);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		//this.setProjects(projects);
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the projects
	 */
	public Set<SerializableProject> getProjects() {
		return projects;
	}

	/**
	 * @param projects the projects to set
	 */
	public void setProjects(Set<SerializableProject> projects) {
		this.projects = projects;
	}

	/**
	 * 
	 */
	public void addProject(SerializableProject project) {
		this.projects.add(project);			// weist Person dem Projekt-Set zu
		project.getPersons().add(this);		// weist Projekt dem Person-Set zu
	}
}