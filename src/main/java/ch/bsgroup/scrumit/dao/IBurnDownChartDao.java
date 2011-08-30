package ch.bsgroup.scrumit.dao;

import ch.bsgroup.scrumit.domain.BurnDownChart;
import ch.bsgroup.scrumit.domain.BurnDown;

import java.util.Date;
import java.util.List;

/**
 * IBurnDownChart Dao
 */
public interface IBurnDownChartDao {
	public List<BurnDownChart> getAllBurnDownCharts(int sprintId);
	public void addBurnDownForSprint(List<BurnDown> burnDownList);
	public List<BurnDown> getBurnDown(int sprintId, Date startDate);
	public List<BurnDown> getBurnDown(int sprintId);
	public void updateBurnDown(List<BurnDown> burnDownList);
	public void removeBurnDown(int sprintId);
}