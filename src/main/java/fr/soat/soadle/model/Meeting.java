package fr.soat.soadle.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

/**
 * @author hakim
 */
@Entity
public class Meeting implements Serializable {

	private static final long serialVersionUID = -3009157732242241606L;

	@Id
	@Column(name="MEETING_ID")
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

	private String doodleReference;

	private String origine;

	private Date importationDate;

	private String description;

	private String tags;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	private Location location;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	private Participant initiator;

	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name="MEETING_ID", referencedColumnName="MEETING_ID")
	private Set<Option> options;

	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name="MEETING_ID", referencedColumnName="MEETING_ID")
	private Set<Participant> participants;

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

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Participant getInitiator() {
		return initiator;
	}

	public void setInitiator(Participant initiator) {
		this.initiator = initiator;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Set<Option> getOptions() {
		return options;
	}

	public void setOptions(Set<Option> options) {
		this.options = options;
	}

	public Set<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<Participant> participants) {
		this.participants = participants;
	}

	public String getDoodleReference() {
		return doodleReference;
	}

	public void setDoodleReference(String doodleReference) {
		this.doodleReference = doodleReference;
	}

	public String getOrigine() {
		return origine;
	}

	public void setOrigine(String origine) {
		this.origine = origine;
	}

	public Date getImportationDate() {
		return importationDate;
	}

	public void setImportationDate(Date importationDate) {
		this.importationDate = importationDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOptionsHash() {
		return optionsHash;
	}

	public void setOptionsHash(String optionsHash) {
		this.optionsHash = optionsHash;
	}

}
