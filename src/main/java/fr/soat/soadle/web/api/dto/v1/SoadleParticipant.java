package fr.soat.soadle.web.api.dto.v1;

/**
 * @author hakim
 *
 */
public class SoadleParticipant implements Comparable<SoadleParticipant> {

	private Long id;

	private String name;
	
	private String email;

	private String userId;

	private String smallAvatarUrl;

	private String largeAvatarUrl;

	private Boolean preference;
	
	private String doodleId;

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
		

	public String getDoodleId() {
		return doodleId;
	}

	public void setDoodleId(String doodleId) {
		this.doodleId = doodleId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SoadleParticipant other = (SoadleParticipant) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public Boolean getPreference() {
		return preference;
	}

	public void setPreference(Boolean preference) {
		this.preference = preference;
	}

	@Override
	public int compareTo(SoadleParticipant arg0) {
		if (arg0 == null)
			return -1;

		int compare = this.name.compareTo(arg0.getName());

		if (compare != 0)
			return compare;

		if (this.id == null && arg0.getId() != null) {
			return 1;
		} else if (this.id != null && arg0.getId() == null) {
			return -1;
		} else if (this.id == null && arg0.getId() == null) {
			return 0;
		}

		return this.id.compareTo(arg0.getId());
	}

}
