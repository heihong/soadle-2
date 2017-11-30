package fr.soat.soadle.web.api.dto.v1;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * @author hakim
 *
 */
public class SoadleOption {

	private Long id;

	private Date start;

	private Date date;

	private Boolean allday;

	private Boolean available;
	

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

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm")
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

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SoadleOption other = (SoadleOption) obj;
		if (allday == null) {
			if (other.allday != null)
				return false;
		} else if (!allday.equals(other.allday))
			return false;
		if (available == null) {
			if (other.available != null)
				return false;
		} else if (!available.equals(other.available))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}
	
	

}
