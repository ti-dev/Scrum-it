package ch.bsgroup.scrumit.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ch.bsgroup.scrumit.domain.Person;
import ch.bsgroup.scrumit.domain.Project;
import ch.bsgroup.scrumit.dao.IPersonDao;
import ch.bsgroup.scrumit.utils.HibernateUtil;

/**
 * Person Dao Hibernate Implementation
 */
public class PersonDaoImplHibernate implements IPersonDao {
	/**
	 * Add Person
	 */
	public Person addPerson(Person p) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session sess = sessionFactory.getCurrentSession();

		Transaction tx = sess.beginTransaction();
		sess.save(p);
		sess.flush();
		tx.commit();

		return p;
	}

	/**
	 * Update Person
	 */
	public void updatePerson(Person p) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session sess = sessionFactory.getCurrentSession();

		Transaction tx = sess.beginTransaction();
		sess.update(p);
		tx.commit();
	}

	/**
	 * Delete Person
	 */
	public void removePerson(int personId){		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session sess = sessionFactory.getCurrentSession();

		Transaction tx = sess.beginTransaction();

		try {
			Person p = (Person)sess.createQuery("from Person where id = "+personId).list().get(0);
			Set<Project> projects = p.getProjects();
			for (Project project : projects) {
				if (project.getPersons().contains(p)) {
					project.getPersons().remove(p);
			        sess.saveOrUpdate(project);
				}
			}
			sess.delete(p);
		    tx.commit();
		}
		catch (Exception ex) {
			System.err.println("Failed deleting Person: "+ex.getMessage());
		}
	}

	/**
	 * Get all Persons
	 */
	public Set<Person> getAllPersons() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session sess = sessionFactory.getCurrentSession();

		Transaction tx = sess.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Person> list = sess.createQuery("from Person").list();
		Set<Person> persons = new HashSet<Person>(list);
		tx.commit();

		return persons;
	}

	/**
	 * Find Person by ID
	 */
	public Person findPersonById(int personId) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session sess = sessionFactory.getCurrentSession();

		Transaction tx = sess.beginTransaction();
		try {
			Person person = (Person)sess.createQuery("from Person where id = "+personId).list().get(0);
			person.getProjects();
			tx.commit();
			return person;
		}
		catch (IndexOutOfBoundsException ex) {
			return null;
		}
	}

	/**
	 * Get all Persons which are associated to a given Project(Id)
	 */
	public Set<Person> getAllPersonsByProjectId(int projectId) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session sess = sessionFactory.getCurrentSession();

		Transaction tx = sess.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Person> list = sess.createQuery("select p from Person p join p.projects proj where proj.id = :id").setParameter("id", projectId).list();  
		Set<Person> persons = new HashSet<Person>(list);
		tx.commit();

		return persons;
	}
}