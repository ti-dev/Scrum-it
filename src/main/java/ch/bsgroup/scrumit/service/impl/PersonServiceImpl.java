package ch.bsgroup.scrumit.service.impl;

import java.util.Set;

import ch.bsgroup.scrumit.dao.IPersonDao;
import ch.bsgroup.scrumit.dao.impl.PersonDaoImplHibernate;
import ch.bsgroup.scrumit.service.IPersonService;
import ch.bsgroup.scrumit.domain.Person;

/**
 * Person Service Implementation
 */
public class PersonServiceImpl implements IPersonService {
	/**
	 * DAO binding
	 */
	private IPersonDao personDao;

	public void setPersonDao(IPersonDao value) {
		personDao = value;
	}

	/**
	 * Constructor
	 */
	public PersonServiceImpl() {
		personDao = new PersonDaoImplHibernate();
	}

	/**
	 * Service calls (delegation)
	 */
	public Person addPerson(Person p) {
		return personDao.addPerson(p);
	}

	public void updatePerson(Person p) {
		/*Set<Project> projects = p.getProjects();
		Iterator<Project> iterator = projects.iterator();
		while (iterator.hasNext()) {
			Project project = iterator.next();
			boolean update = false;
			if (project.getPersons().contains(p)) {
				if (!p.getProjects().contains(project)) {
					// project has this person, but person does not have this project anymore
					// -> delete project from person
					project.getPersons().remove(p);
					update = true;
				}
			} else {
				// project does not have this person, should be added
				project.getPersons().add(p);
				update = true;
			}

			if (update) {
				projectDao.updateProject(project);
			}
		}*/
		personDao.updatePerson(p);
	}

	public void removePerson(int personId) {
		/*Person person = findPersonById(personId);
		Set<Project> projects = person.getProjects();

		// delete all entries in Project_Person for this person
		Iterator<Project> iterator = projects.iterator();
		while (iterator.hasNext()) {
			Project project = iterator.next();

			if (project.getPersons().contains(person)) {
				project.getPersons().remove(person);
				projectDao.updateProject(project);
			}
		}*/
		personDao.removePerson(personId);
	}

	public Set<Person> getAllPersons() {
		return personDao.getAllPersons();
	}

	public Person findPersonById(int personId) {
		return personDao.findPersonById(personId);
	}

	public Set<Person> getAllPersonsByProjectId(int projectId) {
		return personDao.getAllPersonsByProjectId(projectId);
	}
}