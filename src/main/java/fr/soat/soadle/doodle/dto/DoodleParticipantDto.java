package fr.soat.soadle.doodle.dto;

import java.util.List;

/**
 * @author hakim
 *
 */
public class DoodleParticipantDto {

	private String id;
	
	private String name;
	
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

	public List<Integer> getPreferences() {
		return preferences;
	}

	public void setPreferences(List<Integer> preferences) {
		this.preferences = preferences;
	}	
	
}
