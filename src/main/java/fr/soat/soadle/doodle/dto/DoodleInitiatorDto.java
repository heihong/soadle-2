package fr.soat.soadle.doodle.dto;

public class DoodleInitiatorDto {

	private String name;

	private Boolean notify;

	private String avatarLargeUrl;

	private String avatarSmallUrl;

	private String userId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getNotify() {
		return notify;
	}

	public void setNotify(Boolean notify) {
		this.notify = notify;
	}

	public String getAvatarLargeUrl() {
		return avatarLargeUrl;
	}

	public void setAvatarLargeUrl(String avatarLargeUrl) {
		this.avatarLargeUrl = avatarLargeUrl;
	}

	public String getAvatarSmallUrl() {
		return avatarSmallUrl;
	}

	public void setAvatarSmallUrl(String avatarSmallUrl) {
		this.avatarSmallUrl = avatarSmallUrl;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "DoodleInitiatorDto [name=" + name + ", notify=" + notify + ", avatarLargeUrl=" + avatarLargeUrl
				+ ", avatarSmallUrl=" + avatarSmallUrl + ", userId=" + userId + "]";
	}

	
}
