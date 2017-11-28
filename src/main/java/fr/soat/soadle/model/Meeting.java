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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private Date importationDate;
    
    private String description;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	private Location location;

	@OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	private Set<Option> options;

	@OneToMany
    private Set<Participant> participants;

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
        
}
