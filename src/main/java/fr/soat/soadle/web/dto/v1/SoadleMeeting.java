package fr.soat.soadle.web.dto.v1;

import java.util.Date;
import java.util.List;

/**
 * @author hakim
 *
 */
public class SoadleMeeting {
	
	private String id;

	private Date latestChange;

	private Date initiated;

	private Integer participantsCount;

	private Integer inviteesCount;

	private String type;

	private String preferencesType;

	private String state;

	private String locale;

	private String device;

	private String levels;

	private String title;
	
	private SoadleLocation location;

	private List<SoadleOption> options;

	private List<SoadleParticipant> participants;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getLatestChange() {
		return latestChange;
	}

	public void setLatestChange(Date latestChange) {
		this.latestChange = latestChange;
	}

	public Date getInitiated() {
		return initiated;
	}

	public void setInitiated(Date initiated) {
		this.initiated = initiated;
	}

	public Integer getParticipantsCount() {
		return participantsCount;
	}

	public void setParticipantsCount(Integer participantsCount) {
		this.participantsCount = participantsCount;
	}

	public Integer getInviteesCount() {
		return inviteesCount;
	}

	public void setInviteesCount(Integer inviteesCount) {
		this.inviteesCount = inviteesCount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPreferencesType() {
		return preferencesType;
	}

	public void setPreferencesType(String preferencesType) {
		this.preferencesType = preferencesType;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getLevels() {
		return levels;
	}

	public void setLevels(String levels) {
		this.levels = levels;
	}
		

	public SoadleLocation getLocation() {
		return location;
	}

	public void setLocation(SoadleLocation location) {
		this.location = location;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<SoadleOption> getOptions() {
		return options;
	}

	public void setOptions(List<SoadleOption> soadleOptions) {
		this.options = soadleOptions;
	}

	public List<SoadleParticipant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<SoadleParticipant> soadleParticipants) {
		this.participants = soadleParticipants;
	}

}
