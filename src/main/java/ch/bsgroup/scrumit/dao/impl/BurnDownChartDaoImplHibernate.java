package ch.bsgroup.scrumit.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.type.DoubleType;
import org.hibernate.type.DateType;
import org.hibernate.transform.Transformers;

import ch.bsgroup.scrumit.domain.BurnDown;
import ch.bsgroup.scrumit.domain.BurnDownChart;
import ch.bsgroup.scrumit.dao.IBurnDownChartDao;
import ch.bsgroup.scrumit.utils.HibernateUtil;

/**
 * Burn Down Chart Dao Hibernate Implementation
 */
public class BurnDownChartDaoImplHibernate implements IBurnDownChartDao {
	// static final variables, because in Hibernate  > 3.5.x Hibernate.TYPE is deprecated
	private static final DoubleType DOUBLE = new DoubleType();
	private static final DateType DATE = new DateType();

	/**
	 * Get all Burn Down Chart data
	 */
	public List<BurnDownChart> getAllBurnDownCharts(int sprintId) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session sess = sessionFactory.getCurrentSession();

		Transaction tx = sess.beginTransaction();

		List<BurnDownChart> burnDownCharts = new ArrayList<BurnDownChart>();
		// SQL statement to get Burn Down Chart objects
		@SuppressWarnings("unchecked")
		List resultWithAliasedBean = sess.createSQLQuery(
        		"SELECT " +
        			"COALESCE(`resultData`.`real`, 0) as 'real', " +
        			"0 as 'optimal', " +
        			"`DIMDATES`.`ddDate` as 'date'" +
        		"FROM `dimdates` AS DIMDATES " +
        		"LEFT JOIN ( " +
        			"SELECT SUM(DURATION) AS 'real', 0 AS 'optimal', date " +
        			"FROM ( " +
        				"SELECT " +
        					"`task`.`id` AS 'ID', " +
        					"`task`.`duration` AS DURATION, " +
        					"DATE(FROM_UNIXTIME(`revinfo`.`REVTSTMP` / 1000)) AS 'date', " +
        					"`task_aud`.`REV` AS 'REV', " +
        					"`task_aud`.`status` AS 'STATUS', " +
        					"STARTDATE, " +
        					"ENDDATE " +
        				"FROM `sprint_userstory` " +
        					"INNER JOIN `userstory` ON `sprint_userstory`.`userstory_id` = `userstory`.`id` " +
        					"INNER JOIN `task` ON `userstory`.`id` = `task`.`userstory_id` " +
        					"INNER JOIN `task_aud` ON `task`.`id` = `task_aud`.`id` " +
        					"INNER JOIN `revinfo` ON `task_aud`.`REV` = `revinfo`.`REV` " +
        					"INNER JOIN ( " +
        						"SELECT " +
        							"`task`.`id` AS 'ID', " +
        							"DATE(FROM_UNIXTIME(`revinfo`.`REVTSTMP` / 1000)) AS 'date', " +
        							"MAX(`task_aud`.`REV`) AS 'MAX_REV', " +
        							"`sprint`.`startDate` AS 'STARTDATE', " +
        							"`sprint`.`endDate` AS 'ENDDATE' " +
        						"FROM `sprint_userstory` " +
        							"INNER JOIN `userstory` ON `sprint_userstory`.`userstory_id` = `userstory`.`id` " +
        							"INNER JOIN `task` on `userstory`.`id` = `task`.`userstory_id` " +
        							"INNER JOIN `task_aud` ON `task`.`id` = `task_aud`.`id` " +
        							"INNER JOIN `revinfo` ON `task_aud`.`REV` = `revinfo`.`REV` " +
        							"INNER JOIN `sprint` ON `sprint_userstory`.`sprint_id` = `sprint`.`id` " +
        						"WHERE " +
        							"`sprint_userstory`.`sprint_id` = "+sprintId+" " +
        						"GROUP BY ID, date" +
        						") AS `SubSel` " +
        						"ON " +
        							"`SubSel`.`id` = `task`.`id` " +
        							"AND `SubSel`.`date` = date " +
        							"AND `SubSel`.`MAX_REV` = `task_aud`.`REV` " +
        						"WHERE `task_aud`.`status` != 2 " +
        						"GROUP BY ID, `SubSel`.`date`" +
        						") AS `SubTable` " +
        					"WHERE date BETWEEN STARTDATE AND ENDDATE " +
        					"GROUP BY date" +
        				") AS `resultData` " +
        				"ON `DIMDATES`.`ddDate` = `resultData`.`date` " +
        				"WHERE `DIMDATES`.`ddDate` BETWEEN " +
        					"(SELECT DATE(`sprint`.`startDate`) FROM `sprint` WHERE `sprint`.`id` = "+sprintId+") " +
        					"AND (SELECT DATE(`sprint`.`endDate`) FROM `sprint` WHERE `sprint`.`id` = "+sprintId+");")
				.addScalar("real", DOUBLE)
				.addScalar("optimal", DOUBLE)
				.addScalar("date", DATE)
				.setResultTransformer(Transformers.aliasToBean(BurnDownChart.class))
				.list();
        BurnDownChart dto;
        for (int i=0; i < resultWithAliasedBean.size(); i++) {
        	dto = (BurnDownChart) resultWithAliasedBean.get(i);
        	burnDownCharts.add((BurnDownChart)dto);
        }
        tx.commit();

		return burnDownCharts;
	}

	public void addBurnDownForSprint(List<BurnDown> burnDownList) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session sess = sessionFactory.getCurrentSession();

		Transaction tx = sess.beginTransaction();
		for (Iterator<BurnDown> i = burnDownList.iterator(); i.hasNext();) {
			sess.save(i.next());
		}
		sess.flush();
		tx.commit();
	}

	public List<BurnDown> getBurnDown(int sprintId, Date startDate) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session sess = sessionFactory.getCurrentSession();

		Transaction tx = sess.beginTransaction();
		@SuppressWarnings("unchecked")
		List<BurnDown> list = sess.createQuery("select b from BurnDown b where sprintid = :sprintid and date >= :startdate").setParameter("sprintid", sprintId).setParameter("startdate", startDate).list();  
		tx.commit();

		return list;
	}

	public List<BurnDown> getBurnDown(int sprintId) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session sess = sessionFactory.getCurrentSession();

		Transaction tx = sess.beginTransaction();
		@SuppressWarnings("unchecked")
		List<BurnDown> list = sess.createQuery("select b from BurnDown b where sprintid = :sprintid").setParameter("sprintid", sprintId).list();  
		tx.commit();

		return list;
	}

	public void updateBurnDown(List<BurnDown> burnDownList) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session sess = sessionFactory.getCurrentSession();

		Transaction tx = sess.beginTransaction();
		for (Iterator<BurnDown> i = burnDownList.iterator(); i.hasNext();) {
			sess.update(i.next());
		}
		sess.flush();
		tx.commit();
	}

	public void removeBurnDown(int sprintId) {
		// ToDo
	}
}