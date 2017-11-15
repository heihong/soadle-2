package fr.soat.soadle.doodle.dto;

import java.util.Date;

/**
 * @author hakim
 *
 */
public class DoodleOptionDto {
	
	private Date  start;
	
	private Date date;
	
	private Boolean allday;
	
	private Boolean available;
	

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
