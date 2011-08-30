package ch.bsgroup.scrumit.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import ch.bsgroup.scrumit.dao.IBurnDownChartDao;
import ch.bsgroup.scrumit.dao.impl.BurnDownChartDaoImplHibernate;
import ch.bsgroup.scrumit.service.IBurnDownChartService;
import ch.bsgroup.scrumit.domain.BurnDown;
import ch.bsgroup.scrumit.domain.BurnDownChart;

/**
 * Burn Down Chart Service Implementation
 */
public class BurnDownChartServiceImpl implements IBurnDownChartService {
	/**
	 * DAO binding
	 */
	private IBurnDownChartDao burnDownChartDao;

	public void setBurnDownChartDao(IBurnDownChartDao value) {
		burnDownChartDao = value;
	}

	/**
	 * Constructor
	 */
	public BurnDownChartServiceImpl() {
		burnDownChartDao = new BurnDownChartDaoImplHibernate();
	}

	/**
	 * Service calls (delegation)
	 */
	public List<BurnDownChart> getAllBurnDownCharts(int sprintId) {
		return burnDownChartDao.getAllBurnDownCharts(sprintId);
	}

	public void addBurnDownForSprint(List<BurnDown> burnDownList) {
		burnDownChartDao.addBurnDownForSprint(burnDownList);
	}

	public void updateBurnDown(int openDuration, int doneDuration, int sprintId) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<BurnDown> bdList;
		try {
			bdList = burnDownChartDao.getBurnDown(sprintId, df.parse(df.format(new Date())));
			for (Iterator<BurnDown> iterator = bdList.iterator(); iterator.hasNext();) {
				BurnDown bd = iterator.next();
				bd.setOpen(bd.getOpen()+openDuration);
				bd.setDone(bd.getDone()+doneDuration);
			}
			burnDownChartDao.updateBurnDown(bdList);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public List<BurnDown> getBurnDown(int sprintId) {
		return burnDownChartDao.getBurnDown(sprintId);
	}

	public void removeBurnDown(int sprintId) {
		burnDownChartDao.removeBurnDown(sprintId);
	}
}