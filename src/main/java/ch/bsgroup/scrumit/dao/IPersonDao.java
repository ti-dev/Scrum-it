package ch.bsgroup.scrumit.dao;

import ch.bsgroup.scrumit.domain.Person;

import java.util.Set;

/**
 * IPerson Dao
 */
public interface IPersonDao {
	public Person addPerson(Person p);
	public void updatePerson(Person p);
	public void removePerson(int personId);
	public Set<Person> getAllPersons();
	public Person findPersonById(int personId);
	public Set<Person> getAllPersonsByProjectId(int projectId);
}