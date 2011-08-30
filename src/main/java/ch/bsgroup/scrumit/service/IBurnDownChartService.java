package ch.bsgroup.scrumit.service;

import ch.bsgroup.scrumit.domain.BurnDownChart;
import ch.bsgroup.scrumit.domain.BurnDown;

import java.util.List;

/**
 * Burn Down Chart Service Interface
 */
public interface IBurnDownChartService {
	public List<BurnDownChart> getAllBurnDownCharts(int sprintId);
	public void addBurnDownForSprint(List<BurnDown> burnDownList);
	public void updateBurnDown(int openDuration, int doneDuration, int sprintId);
	public List<BurnDown> getBurnDown(int sprintId);
	public void removeBurnDown(int sprintId);
}