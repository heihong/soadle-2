package fr.soat.soadle.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author hakim
 *
 */
@Entity
@Table(name="soaoption")
public class Option implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1675462543228573168L;

	@Id
	private String id;

	private Date start;

	private Date date;

	private Boolean allday;

	private Boolean available;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getAllday() {
		return allday;
	}

	public void setAllday(Boolean allday) {
		this.allday = allday;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}
	

}
