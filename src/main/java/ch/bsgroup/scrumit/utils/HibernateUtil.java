package ch.bsgroup.scrumit.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class
 */
public class HibernateUtil {
	// SessionFactory of Hibernate
	private static final SessionFactory sessionFactory;
	static {
		try {
			// create new SessionFactory from hibernate.cfg.xml
			sessionFactory = new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
		}
		catch (Throwable ex) {
			// If something goes wrong, it will throw an exception
			System.err.println("Initial SessionFactory creation failed. " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * Get the SessionFacotry of Hibernate
	 * @return SessionFactory
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}