package ch.bsgroup.scrumit.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ch.bsgroup.scrumit.domain.Sprint;
import ch.bsgroup.scrumit.domain.UserStory;
import ch.bsgroup.scrumit.dao.IUserStoryDao;
import ch.bsgroup.scrumit.utils.HibernateUtil;

/**
 * UserStory Dao Hibernate Implementation
 */
public class UserStoryDaoImplHibernate implements IUserStoryDao {
	/**
	 * Add UserStory
	 */
	public UserStory addUserStory(UserStory u) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session sess = sessionFactory.getCurrentSession();

		Transaction tx = sess.beginTransaction();
		sess.save(u);
		sess.flush();
		tx.commit();

		return u;
	}

	/**
	 * Update UserStory
	 */
	public void updateUserStory(UserStory u) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session sess = sessionFactory.getCurrentSession();

		Transaction tx = sess.beginTransaction();
		sess.update(u);
		tx.commit();
	}

	/**
	 * Delete UserStory
	 */
	public void removeUserStory(int userstoryId){
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session sess = sessionFactory.getCurrentSession();

		Transaction tx = sess.beginTransaction();

		try {
			UserStory u = (UserStory)sess.createQuery("from UserStory where id = "+userstoryId).list().get(0);
			Set<Sprint> sprints = u.getSprints();
			for (Sprint sprint : sprints) {
				if (sprint.getUserStories().contains(u)) {
					sprint.getUserStories().remove(u);
			        sess.saveOrUpdate(sprint);
				}
			}
			sess.delete(u);
		    tx.commit();
		}
		catch (Exception ex) {
			System.err.println("Failed deleting Person: "+ex.getMessage());
		}
	}

	/**
	 * Get all UserStories
	 */
	public Set<UserStory> getAllUserStorys() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session sess = sessionFactory.getCurrentSession();

		Transaction tx = sess.beginTransaction();
		@SuppressWarnings("unchecked")
		List<UserStory> list = sess.createQuery("from UserStory").list();
		Set<UserStory> userstorys = new HashSet<UserStory>(list);
		tx.commit();

		return userstorys;
	}

	/**
	 * Find UserStory by ID
	 */
	public UserStory findUserStoryById(int userstoryId) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session sess = sessionFactory.getCurrentSession();

		Transaction tx = sess.beginTransaction();
		try {
			UserStory userstory = (UserStory)sess.createQuery("from UserStory where id = "+userstoryId).list().get(0);
			tx.commit();
			return userstory;
		}
		catch (IndexOutOfBoundsException ex) {
			return null;
		}
	}

	/**
	 * Get all UserStories by Sprint(Id)
	 */
	public Set<UserStory> getAllUserStorysBySprintId(int sprintId) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session sess = sessionFactory.getCurrentSession();

		Transaction tx = sess.beginTransaction();
		@SuppressWarnings("unchecked")
		List<UserStory> list = sess.createQuery("select u from UserStory u join u.sprints spri where spri.id = :id").setParameter("id", sprintId).list();  
		
		Set<UserStory> userstorys = new HashSet<UserStory>(list);
		tx.commit();

		return userstorys;
	}
}