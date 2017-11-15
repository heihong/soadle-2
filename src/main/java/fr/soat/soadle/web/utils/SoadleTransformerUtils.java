package fr.soat.soadle.web.utils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import fr.soat.soadle.model.Location;
import fr.soat.soadle.model.Option;
import fr.soat.soadle.model.Participant;
import fr.soat.soadle.model.Soadle;
import fr.soat.soadle.web.dto.v1.SoadleOption;
import fr.soat.soadle.web.dto.v1.SoadleParticipant;
import fr.soat.soadle.web.dto.v1.SoadleLocation;
import fr.soat.soadle.web.dto.v1.SoadleMeeting;

/**
 * @author hakim
 *
 */
public class SoadleTransformerUtils {

	/**
	 * @param doodle
	 * @return
	 */
	public static SoadleMeeting to(Soadle soadle) {
		
		if(soadle == null) return null;

		SoadleMeeting response = new SoadleMeeting();

		response.setId(soadle.getId());
		response.setLatestChange(soadle.getLatestChange());
		response.setInitiated(soadle.getInitiated());
		response.setParticipantsCount(soadle.getParticipantsCount());
		response.setInviteesCount(soadle.getInviteesCount());
		response.setType(soadle.getType());
		response.setPreferencesType(soadle.getPreferencesType());
		response.setState(soadle.getState());
		response.setLocale(soadle.getLocale());
		response.setDevice(soadle.getDevice());
		response.setLevels(soadle.getLevels());
		response.setTitle(soadle.getTitle());
		response.setLocation(from(soadle.getLocation()));
		
		response.setParticipants(toParticipants(soadle.getParticipants()));
		response.setOptions(toOptions(soadle.getOptions()));

		return response;

	}

	

	/**
	 * @param location
	 * @return
	 */
	private static SoadleLocation from(Location location) {
		if(location == null) return null;
		
		SoadleLocation soadleLocation = new SoadleLocation();
		
		soadleLocation.setName(location.getName());
		soadleLocation.setCategory(location.getCategory());
		
		return soadleLocation;
	}
	
	
	/**
	 * @param options
	 * @return
	 */
	private static List<SoadleOption> toOptions(Set<Option> options) {
		
		if(options!= null) return options.stream().map(o -> to(o)).collect(Collectors.toList());
		
		return null;
	}
	
	
	/**
	 * @param option
	 * @return
	 */
	private static SoadleOption to(Option option) {
		
		SoadleOption response = new SoadleOption();
		
		response.setAllday(option.getAllday());
		response.setAvailable(option.getAvailable());
		response.setDate(option.getDate());
		response.setStart(option.getStart());
		
		return response;
	}

	/**
	 * @param participants
	 * @return
	 */
	private static List<SoadleParticipant> toParticipants(Set<Participant> participants) {
		
		if(participants!= null) return participants.stream().map(p -> to(p)).collect(Collectors.toList());
		
		
		return null;
	}

	
	
	/**
	 * @param participant
	 * @return
	 */
	private static SoadleParticipant to(Participant participant) {
		
		SoadleParticipant response = new SoadleParticipant();
		
		response.setId(participant.getId());
		response.setName(participant.getName());
		
		return response;
	}
		
		
}
