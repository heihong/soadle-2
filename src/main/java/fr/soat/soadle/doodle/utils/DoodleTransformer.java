package fr.soat.soadle.doodle.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import fr.soat.soadle.doodle.dto.DoodleDto;
import fr.soat.soadle.doodle.dto.DoodleInitiatorDto;
import fr.soat.soadle.doodle.dto.DoodleLocationDto;
import fr.soat.soadle.doodle.dto.DoodleOptionDto;
import fr.soat.soadle.doodle.dto.DoodleParticipantDto;
import fr.soat.soadle.model.EnumOrigine;
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
	 **********************************
	 * 	Doodle to Meeting             *
	 **********************************
	 */
	
	
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
		meeting.setOptionsHash(doodle.getOptionsHash());
		meeting.setDescription(doodle.getDescription());
		meeting.setLocation(from(doodle.getLocation()));
		meeting.setInitiator(from(doodle.getInitiator()));
		
		meeting.setParticipants(fromParticipants(doodle.getParticipants()));
		meeting.setOptions(fromOptions(doodle.getOptions()));
		meeting.setOrigine(EnumOrigine.DOODLE.toString());

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
	 * @param initiatorDto
	 * @return
	 */
	private static Participant from(DoodleInitiatorDto initiatorDto) {
		
		if(initiatorDto == null) return null;

		Participant participant = new Participant();
		
		participant.setName(initiatorDto.getName());	
		participant.setEmail(initiatorDto.getEmail());
		participant.setSmallAvatarUrl(initiatorDto.getAvatarSmallUrl());
		participant.setLargeAvatarUrl(initiatorDto.getAvatarLargeUrl());
		participant.setUserId(initiatorDto.getUserId());
		
		return participant;
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
	public static Participant from(DoodleParticipantDto doodleParticipantDto) {
		
		if(doodleParticipantDto == null) return null;
		
		Participant participant = new Participant();
		
		participant.setDoodleId(doodleParticipantDto.getId());
		participant.setName(doodleParticipantDto.getName());		
		participant.setSmallAvatarUrl(doodleParticipantDto.getSmallAvatarUrl());
		participant.setLargeAvatarUrl(doodleParticipantDto.getLargeAvatarUrl());
		participant.setUserId(doodleParticipantDto.getUserId());
		participant.setOptionsHash(doodleParticipantDto.getOptionsHash());
		participant.setPreference(CollectionUtils.isNotEmpty(doodleParticipantDto.getPreferences()) && doodleParticipantDto.getPreferences().get(0) ==1 );
		
		return participant;
	}
		
	
	/**
	 **********************************
	 * 	Meeting to Doodle             *
	 **********************************
	 */
	
	/**
	 * @param doodle
	 * @return
	 */
	public static DoodleDto to(Meeting meeting) {
		
		if(meeting == null) return null;

		DoodleDto doodleDto = new DoodleDto();

		doodleDto.setId(meeting.getId());
		doodleDto.setLatestChange(meeting.getLatestChange());
		doodleDto.setInitiated(meeting.getInitiated());
		doodleDto.setParticipantsCount(meeting.getParticipantsCount());
		doodleDto.setInviteesCount(meeting.getInviteesCount());
		doodleDto.setType(meeting.getType());
		doodleDto.setPreferencesType(meeting.getPreferencesType());
		doodleDto.setState(meeting.getState());
		doodleDto.setLocale(meeting.getLocale());
		doodleDto.setDevice(meeting.getDevice());
		doodleDto.setLevels(meeting.getLevels());
		doodleDto.setTitle(meeting.getTitle());
		doodleDto.setOptionsHash(meeting.getOptionsHash());
		doodleDto.setDescription(meeting.getDescription());
		doodleDto.setLocation(to(meeting.getLocation()));
		doodleDto.setInitiator(from(meeting.getInitiator()));
		
		doodleDto.setParticipants(toParticipants(meeting.getParticipants()));
		doodleDto.setOptions(toOptions(meeting.getOptions()));

		return doodleDto;

	}

	/**
	 * @param doodleLocation
	 * @return
	 */
	private static DoodleLocationDto to(Location location) {
		if(location == null) return null;
		
		DoodleLocationDto doodleLocation= new DoodleLocationDto();
		
		doodleLocation.setName(location.getName());
		doodleLocation.setAddress(location.getAddress());
		doodleLocation.setCategory(location.getCategory());
		
		return doodleLocation;
	}

	/**
	 * @param options
	 * @return
	 */
	private static List<DoodleOptionDto> toOptions(Set<Option> options) {
		
		if(options!= null) return options.stream().map(o -> to(o)).collect(Collectors.toList());
		
		return null;
	}
	
	
	/**
	 * @param option
	 * @return
	 */
	private static DoodleOptionDto to(Option option) {
		
		DoodleOptionDto doodleOptionDto = new DoodleOptionDto();
		
		doodleOptionDto.setAllday(option.getAllday());
		doodleOptionDto.setAvailable(option.getAvailable());
		doodleOptionDto.setDate(option.getDate());
		doodleOptionDto.setStart(option.getStart());
		
		return doodleOptionDto;
	}

	

	/**
	 * @param initiator
	 * @return
	 */
	private static DoodleInitiatorDto from(Participant initiator) {
		
		if(initiator == null) return null;

		DoodleInitiatorDto doodleInitiatorDto = new DoodleInitiatorDto();
		
		doodleInitiatorDto.setName(initiator.getName());	
		doodleInitiatorDto.setEmail(initiator.getEmail());
		doodleInitiatorDto.setAvatarSmallUrl(initiator.getSmallAvatarUrl());
		doodleInitiatorDto.setAvatarLargeUrl(initiator.getLargeAvatarUrl());
		doodleInitiatorDto.setUserId(initiator.getUserId());
		doodleInitiatorDto.setNotify(true);
		
		return doodleInitiatorDto;
	}
	
	
	/**
	 * @param participants
	 * @return
	 */
	private static List<DoodleParticipantDto> toParticipants(Set<Participant> participants) {
		
		if(participants!= null) return participants.stream().map(p -> to(p)).collect(Collectors.toList());
		
		
		return null;
	}

	
	/**
	 * @param participant
	 * @return
	 */
	public static DoodleParticipantDto to(Participant participant) {
		
		DoodleParticipantDto doodleParticipantDto = new DoodleParticipantDto();
		
		doodleParticipantDto.setId(participant.getDoodleId());
		doodleParticipantDto.setName(participant.getName());		
		doodleParticipantDto.setSmallAvatarUrl(participant.getSmallAvatarUrl());
		doodleParticipantDto.setLargeAvatarUrl(participant.getLargeAvatarUrl());
		doodleParticipantDto.setUserId(participant.getUserId());
		doodleParticipantDto.setOptionsHash(participant.getOptionsHash());
		doodleParticipantDto.setPreferences(Arrays.asList(participant.getPreference() ? 1 : 0));
		
		return doodleParticipantDto;
	}
		
}
