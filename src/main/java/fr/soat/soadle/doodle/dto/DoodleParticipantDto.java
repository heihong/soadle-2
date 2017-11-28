package fr.soat.soadle.doodle.dto;

import java.util.List;

/**
 * @author hakim
 *
 */
public class DoodleParticipantDto {

	private String id;

	private String name;

	private String userId;

	private String smallAvatarUrl;

	private String largeAvatarUrl;

	private List<Integer> preferences;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<Integer> getPreferences() {
		return preferences;
	}

	public void setPreferences(List<Integer> preferences) {
		this.preferences = preferences;
	}

}
