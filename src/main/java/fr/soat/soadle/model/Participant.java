package fr.soat.soadle.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author hakim
 *
 */
@Entity
public class Participant implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3308838324770542811L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String name;
	
	private String email;

	private String userId;

	private String smallAvatarUrl;

	private String largeAvatarUrl;

	private Boolean preference;
	
	private String doodleId;
	
	@Column(name="MEETING_ID")
	private String mettnigId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSmallAvatarUrl() {
		return smallAvatarUrl;
	}

	public void setSmallAvatarUrl(String smallAvatarUrl) {
		this.smallAvatarUrl = smallAvatarUrl;
	}

	public String getLargeAvatarUrl() {
		return largeAvatarUrl;
	}

	public void setLargeAvatarUrl(String largeAvatarUrl) {
		this.largeAvatarUrl = largeAvatarUrl;
	}

	public Boolean getPreference() {
		return preference;
	}

	public void setPreference(Boolean preference) {
		this.preference = preference;
	}

	public String getDoodleId() {
		return doodleId;
	}

	public void setDoodleId(String doodleId) {
		this.doodleId = doodleId;
	}

	public String getMettnigId() {
		return mettnigId;
	}

	public void setMettnigId(String mettnigId) {
		this.mettnigId = mettnigId;
	}
		

}
