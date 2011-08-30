package ch.bsgroup.scrumit.pojo;

import java.util.Date;

/**
 * Class BurnDownChart manages the data of the Burn Down Chart 
 */
public class SerializableBurnDownChart {
	/**
	 * BurnDownChart has a day as a string
	 * 	for example " Sun 18.04.1982"
	 */
	private String day;

	/**
	 * BurnDownChart has a real value for each day
	 * 	time for the sum of open tasks
	 */
	private double real;

	/**
	 * BurnDownChart has a optimal value for each day
	 * 	time for the sum for tasks for a optimal burn down chart
	 */
	private double optimal;

	/**
	 * BurnDownChart has a date  
	 *  which will be used for the day attribute and calculations for the BurnDownChart
	 */
	private Date date;

	/**
	 * @desc Constructor
	 */
	public SerializableBurnDownChart(Date date, double real, double optimal) {
		this.setDate(date);
		this.setReal(real);
		this.setOptimal(optimal);
	}

	/**
	 * @return the day
	 */
	public String getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(String day) {
		this.day = day;
	}

	/**
	 * @return the real
	 */
	public double getReal() {
		return real;
	}

	/**
	 * @param real the real to set
	 */
	public void setReal(double real) {
		this.real = real;
	}

	/**
	 * @return the optimal
	 */
	public double getOptimal() {
		return optimal;
	}

	/**
	 * @param optimal the optimal to set
	 */
	public void setOptimal(double optimal) {
		this.optimal = optimal;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
}