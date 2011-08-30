package ch.bsgroup.scrumit.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ch.bsgroup.scrumit.domain.Sprint;
import ch.bsgroup.scrumit.dao.ISprintDao;
import ch.bsgroup.scrumit.utils.HibernateUtil;

/**
 * Sprint Dao Hibernate Implementation
 */
public class SprintDaoImplHibernate implements ISprintDao {
	/**
	 * Add Sprint
	 */
	public Sprint addSprint(Sprint s) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session sess = sessionFactory.getCurrentSession();

		Transaction tx = sess.beginTransaction();
		sess.save(s);
		sess.flush();
		tx.commit();

		return s;
	}

	/**
	 * Update Sprint
	 */
	public void updateSprint(Sprint s) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session sess = sessionFactory.getCurrentSession();

		Transaction tx = sess.beginTransaction();
		sess.update(s);
		tx.commit();
	}

	/**
	 * Delete Sprint
	 */
	public void removeSprint(int sprintId){
		Sprint s = findSprintById(sprintId);

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session sess = sessionFactory.getCurrentSession();

		Transaction tx = sess.beginTransaction();
		sess.delete(s);
		sess.flush();
		tx.commit();
	}

	/**
	 * Get all Sprints
	 */
	public Set<Sprint> getAllSprints() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session sess = sessionFactory.getCurrentSession();

		Transaction tx = sess.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Sprint> list = sess.createQuery("from Sprint").list();
		Set<Sprint> sprints = new HashSet<Sprint>(list);
		tx.commit();

		return sprints;
	}

	/**
	 * Find Sprint by ID
	 */
	public Sprint findSprintById(int sprintId) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session sess = sessionFactory.getCurrentSession();

		Transaction tx = sess.beginTransaction();
		try {
			Sprint sprint = (Sprint)sess.createQuery("from Sprint where id = "+sprintId).list().get(0);
			tx.commit();
			return sprint;
		}
		catch (IndexOutOfBoundsException ex) {
			return null;
		}
	}

	/**
	 * Get all Sprints which are associated to a given Project(Id)
	 */
	public Set<Sprint> getAllSprintsByProjectId(int projectId) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session sess = sessionFactory.getCurrentSession();

		Transaction tx = sess.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Sprint> list = sess.createQuery("from Sprint where project_id = :id").setParameter("id", projectId).list();
		Set<Sprint> sprints = new HashSet<Sprint>(list);
		tx.commit();

		return sprints;
	}
}