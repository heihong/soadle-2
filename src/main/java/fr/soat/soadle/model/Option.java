package fr.soat.soadle.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private Date start;

	private Date date;

	private Boolean allday;

	private Boolean available;
	
	@Column(name="MEETING_ID")
	private String mettnigId;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getMettnigId() {
		return mettnigId;
	}

	public void setMettnigId(String mettnigId) {
		this.mettnigId = mettnigId;
	}
	
}
