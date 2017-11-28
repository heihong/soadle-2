package fr.soat.soadle.doodle.utils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import fr.soat.soadle.doodle.dto.DoodleDto;
import fr.soat.soadle.doodle.dto.DoodleLocationDto;
import fr.soat.soadle.doodle.dto.DoodleOptionDto;
import fr.soat.soadle.doodle.dto.DoodleParticipantDto;
import fr.soat.soadle.model.Location;
import fr.soat.soadle.model.Option;
import fr.soat.soadle.model.Participant;
import fr.soat.soadle.model.Meeting;

/**
 * Transformer doodle meeting to model meeting 
 * 
 * @author hakim
 *
 */
public class DoodleTransformer {

	/**
	 * @param doodle
	 * @return
	 */
	public static Meeting from(DoodleDto doodle) {
		
		if(doodle == null) return null;

		Meeting meeting = new Meeting();

		meeting.setId(doodle.getId());
		meeting.setLatestChange(doodle.getLatestChange());
		meeting.setInitiated(doodle.getInitiated());
		meeting.setParticipantsCount(doodle.getParticipantsCount());
		meeting.setInviteesCount(doodle.getInviteesCount());
		meeting.setType(doodle.getType());
		meeting.setPreferencesType(doodle.getPreferencesType());
		meeting.setState(doodle.getState());
		meeting.setLocale(doodle.getLocale());
		meeting.setDevice(doodle.getDevice());
		meeting.setLevels(doodle.getLevels());
		meeting.setTitle(doodle.getTitle());
		meeting.setDescription(doodle.getDescription());
		meeting.setLocation(from(doodle.getLocation()));
		
		meeting.setParticipants(fromParticipants(doodle.getParticipants()));
		meeting.setOptions(fromOptions(doodle.getOptions()));

		return meeting;

	}

	/**
	 * @param doodleLocation
	 * @return
	 */
	private static Location from(DoodleLocationDto doodleLocation) {
		if(doodleLocation == null) return null;
		
		Location location = new Location();
		
		location.setName(doodleLocation.getName());
		location.setAddress(doodleLocation.getAddress());
		location.setCategory(doodleLocation.getCategory());
		
		return location;
	}

	/**
	 * @param options
	 * @return
	 */
	private static Set<Option> fromOptions(List<DoodleOptionDto> options) {
		
		if(options!= null) return options.stream().map(o -> from(o)).collect(Collectors.toSet());
		
		return null;
	}
	
	
	/**
	 * @param optionDto
	 * @return
	 */
	private static Option from(DoodleOptionDto optionDto) {
		
		Option option = new Option();
		
		option.setAllday(optionDto.getAllday());
		option.setAvailable(optionDto.getAvailable());
		option.setDate(optionDto.getDate());
		option.setStart(optionDto.getStart());
		
		return option;
	}

	/**
	 * @param participants
	 * @return
	 */
	private static Set<Participant> fromParticipants(List<DoodleParticipantDto> participants) {
		
		if(participants!= null) return participants.stream().map(p -> from(p)).collect(Collectors.toSet());
		
		
		return null;
	}

	
	/**
	 * @param doodleParticipantDto
	 * @return
	 */
	private static Participant from(DoodleParticipantDto doodleParticipantDto) {
		
		Participant participant = new Participant();
		
		participant.setId(doodleParticipantDto.getId());
		participant.setName(doodleParticipantDto.getName());		
		participant.setSmallAvatarUrl(doodleParticipantDto.getSmallAvatarUrl());
		participant.setLargeAvatarUrl(doodleParticipantDto.getLargeAvatarUrl());
		participant.setUserId(doodleParticipantDto.getUserId());
		participant.setPreference(CollectionUtils.isNotEmpty(doodleParticipantDto.getPreferences()) && doodleParticipantDto.getPreferences().get(0) ==1 );
		
		return participant;
	}
		
		
}
