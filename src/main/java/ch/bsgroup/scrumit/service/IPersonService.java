package ch.bsgroup.scrumit.service;

import ch.bsgroup.scrumit.domain.Person;

import java.util.Set;

/**
 * Person Service Interface
 */
public interface IPersonService {
	public Person addPerson(Person p);
	public void updatePerson(Person p);
	public void removePerson(int personId);
	public Set<Person> getAllPersons();
	public Person findPersonById(int personId);
	public Set<Person> getAllPersonsByProjectId(int projectId);
}