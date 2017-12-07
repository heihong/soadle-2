package fr.soat.soadle.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "soapicture")
public class Picture implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4706268569414613941L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String url;

	@Column(name = "MEETING_ID")
	private String mettnigId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMettnigId() {
		return mettnigId;
	}

	public void setMettnigId(String mettnigId) {
		this.mettnigId = mettnigId;
	}

}
