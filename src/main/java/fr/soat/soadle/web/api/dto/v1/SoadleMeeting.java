package fr.soat.soadle.web.api.dto.v1;

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
	
	private String origine;

	private String preferencesType;

	private String state;

	private String locale;

	private String device;

	private String levels;

	private String title;
	
	private String description;
	
	private SoadleLocation location;	

	private List<SoadleOption> options;

	private List<SoadleParticipant> participants;

	private String doodleReference;

	private Date importationDate;
	
	private String optionsHash;


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

	public String getOrigine() {
		return origine;
	}

	public void setOrigine(String origine) {
		this.origine = origine;
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
	
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getDoodleReference() {
		return doodleReference;
	}

	public void setDoodleReference(String doodleReference) {
		this.doodleReference = doodleReference;
	}

	public Date getImportationDate() {
		return importationDate;
	}

	public void setImportationDate(Date importationDate) {
		this.importationDate = importationDate;
	}
		

	public String getOptionsHash() {
		return optionsHash;
	}

	public void setOptionsHash(String optionsHash) {
		this.optionsHash = optionsHash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SoadleMeeting other = (SoadleMeeting) obj;
		if (device == null) {
			if (other.device != null)
				return false;
		} else if (!device.equals(other.device))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (initiated == null) {
			if (other.initiated != null)
				return false;
		} else if (!initiated.equals(other.initiated))
			return false;
		if (inviteesCount == null) {
			if (other.inviteesCount != null)
				return false;
		} else if (!inviteesCount.equals(other.inviteesCount))
			return false;
		if (latestChange == null) {
			if (other.latestChange != null)
				return false;
		} else if (!latestChange.equals(other.latestChange))
			return false;
		if (levels == null) {
			if (other.levels != null)
				return false;
		} else if (!levels.equals(other.levels))
			return false;
		if (locale == null) {
			if (other.locale != null)
				return false;
		} else if (!locale.equals(other.locale))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (options == null) {
			if (other.options != null)
				return false;
		} else if (!options.equals(other.options))
			return false;
		if (participants == null) {
			if (other.participants != null)
				return false;
		} else if (!participants.equals(other.participants))
			return false;
		if (participantsCount == null) {
			if (other.participantsCount != null)
				return false;
		} else if (!participantsCount.equals(other.participantsCount))
			return false;
		if (preferencesType == null) {
			if (other.preferencesType != null)
				return false;
		} else if (!preferencesType.equals(other.preferencesType))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	

}
