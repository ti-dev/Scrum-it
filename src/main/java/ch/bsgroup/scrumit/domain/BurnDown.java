package ch.bsgroup.scrumit.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Class BurnDown manages BurnDownRows
 */
@Entity
public class BurnDown {
	/**
	 * Unique Id of the BurnDown to identify it
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private int open;

	private int done;

	private int sprintid;

	@DateTimeFormat(pattern = "dd.MM.yyyy")
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date date;

	public BurnDown() {
		
	}

	public BurnDown(int open, int done, int sprintid, Date date) {
		this.setOpen(open);
		this.setDone(done);
		this.setSprintid(sprintid);
		this.setDate(date);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOpen() {
		return open;
	}

	public void setOpen(int open) {
		this.open = open;
	}

	public int getDone() {
		return done;
	}

	public void setDone(int done) {
		this.done = done;
	}

	public int getSprintid() {
		return sprintid;
	}

	public void setSprintid(int sprintid) {
		this.sprintid = sprintid;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}