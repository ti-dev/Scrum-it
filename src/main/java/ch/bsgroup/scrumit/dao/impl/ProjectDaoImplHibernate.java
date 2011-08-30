package ch.bsgroup.scrumit.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ch.bsgroup.scrumit.domain.Project;
import ch.bsgroup.scrumit.dao.IProjectDao;
import ch.bsgroup.scrumit.utils.HibernateUtil;

/**
 * Project Dao Hibernate Implementation
 */
public class ProjectDaoImplHibernate implements IProjectDao {
	/**
	 * Add Project
	 */
	public Project addProject(Project p) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session sess = sessionFactory.getCurrentSession();

		Transaction tx = sess.beginTransaction();
		sess.save(p);
		sess.flush();
		tx.commit();

		return p;
	}

	/**
	 * Update Project
	 */
	public void updateProject(Project p) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session sess = sessionFactory.getCurrentSession();

		Transaction tx = sess.beginTransaction();
		sess.update(p);
		tx.commit();
	}

	/**
	 * Delete Project
	 */
	public void removeProject(int projectId){
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session sess = sessionFactory.getCurrentSession();

		Transaction tx = sess.beginTransaction();
		try {
			Project p = (Project)sess.createQuery("from Project where id = "+projectId).list().get(0);
			p.setPersons(null);
			sess.delete(p);
			tx.commit();
		}
		catch (IndexOutOfBoundsException ex) {
			System.out.println("exception: "+ex);
		}
	}

	/**
	 * Get all Projects
	 */
	public Set<Project> getAllProjects() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session sess = sessionFactory.getCurrentSession();

		Transaction tx = sess.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Project> list = sess.createQuery("from Project").list();
		Set<Project> projects = new HashSet<Project>(list);
		tx.commit();

		return projects;
	}

	/**
	 * Find Project by ID
	 */
	public Project findProjectById(int projectId) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session sess = sessionFactory.getCurrentSession();

		Transaction tx = sess.beginTransaction();
		try {
			Project project = (Project)sess.createQuery("from Project where id = "+projectId).list().get(0);
			tx.commit();
			return project;
		}
		catch (IndexOutOfBoundsException ex) {
			return null;
		}
	}
}