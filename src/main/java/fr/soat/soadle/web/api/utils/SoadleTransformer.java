package fr.soat.soadle.web.api.utils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import fr.soat.soadle.model.EnumOrigine;
import fr.soat.soadle.model.Location;
import fr.soat.soadle.model.Option;
import fr.soat.soadle.model.Participant;
import fr.soat.soadle.model.Meeting;
import fr.soat.soadle.web.api.dto.v1.SoadleLocation;
import fr.soat.soadle.web.api.dto.v1.SoadleMeeting;
import fr.soat.soadle.web.api.dto.v1.SoadleOption;
import fr.soat.soadle.web.api.dto.v1.SoadleParticipant;

/**
 * Transformer meeting to Soadle web service
 * 
 * @author hakim
 *
 */
public class SoadleTransformer {
	

	/**
	 **********************************
	 * 	Meeting to SoadleMeeting      *
	 **********************************
	 */

	/**
	 * @param findAll
	 *           
	 * @return List SoadleMeetings
	 */
	public static List<SoadleMeeting> to(List<Meeting> meetings) {

		if (meetings != null)
			return meetings.stream().map(m -> to(m)).collect(Collectors.toList());

		return null;
	}

	/**
	 * @param meeting
	 * @return soadle meeting webservice
	 */
	public static SoadleMeeting to(Meeting meeting) {

		if (meeting == null)
			return null;

		SoadleMeeting response = new SoadleMeeting();

		response.setId(meeting.getId());
		response.setLatestChange(meeting.getLatestChange());
		response.setInitiated(meeting.getInitiated());
		response.setParticipantsCount(meeting.getParticipantsCount());
		response.setInviteesCount(meeting.getInviteesCount());
		response.setType(meeting.getType());
		response.setPreferencesType(meeting.getPreferencesType());
		response.setState(meeting.getState());
		response.setLocale(meeting.getLocale());
		response.setDevice(meeting.getDevice());
		response.setLevels(meeting.getLevels());
		response.setTitle(meeting.getTitle());
		response.setOptionsHash(meeting.getOptionsHash());
		response.setDescription(meeting.getDescription());
		response.setLocation(to(meeting.getLocation()));
		response.setDoodleReference(meeting.getDoodleReference());
		response.setOrigine(meeting.getOrigine());

		response.setParticipants(toParticipants(meeting.getParticipants()));
		response.setOptions(toOptions(meeting.getOptions()));

		return response;

	}

	/**
	 * @param location
	 * @return
	 */
	private static SoadleLocation to(Location location) {
		if (location == null)
			return null;

		SoadleLocation soadleLocation = new SoadleLocation();

		soadleLocation.setId(location.getId());
		soadleLocation.setName(location.getName());
		soadleLocation.setAddress(location.getAddress());
		soadleLocation.setCategory(location.getCategory());

		return soadleLocation;
	}

	/**
	 * @param options
	 * @return
	 */
	private static List<SoadleOption> toOptions(Set<Option> options) {

		if (options != null)
			return options.stream().map(o -> to(o)).collect(Collectors.toList());

		return null;
	}

	/**
	 * @param option
	 * @return
	 */
	private static SoadleOption to(Option option) {

		SoadleOption response = new SoadleOption();

		response.setId(option.getId());
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

		if (participants != null)
			return participants.stream().map(p -> to(p)).sorted().collect(Collectors.toList());

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
		response.setUserId(participant.getUserId());
		response.setSmallAvatarUrl(participant.getSmallAvatarUrl());
		response.setLargeAvatarUrl(participant.getLargeAvatarUrl());
		response.setPreference(participant.getPreference());

		return response;
	}
	
	
	/**
	 **********************************
	 * 	SoadleMeeting to Meeting      *
	 **********************************
	 */
	
	
	/**
	 * @param findAll
	 *            : meeting
	 * @return
	 */
	public static List<Meeting> from(List<SoadleMeeting> soadleMeetings) {

		if (soadleMeetings != null)
			return soadleMeetings.stream().map(m -> from(m)).collect(Collectors.toList());

		return null;
	}

	/**
	 * @param meeting
	 * @return soadle meeting webservice
	 */
	public static Meeting from(SoadleMeeting soadleMeeting) {

		if (soadleMeeting == null)
			return null;

		Meeting meeting = new Meeting();

		meeting.setId(soadleMeeting.getId());
		meeting.setLatestChange(soadleMeeting.getLatestChange());
		meeting.setInitiated(soadleMeeting.getInitiated());
		meeting.setParticipantsCount(soadleMeeting.getParticipantsCount());
		meeting.setInviteesCount(soadleMeeting.getInviteesCount());
		meeting.setType(soadleMeeting.getType());
		meeting.setPreferencesType(soadleMeeting.getPreferencesType());
		meeting.setState(soadleMeeting.getState());
		meeting.setLocale(soadleMeeting.getLocale());
		meeting.setDevice(soadleMeeting.getDevice());
		meeting.setLevels(soadleMeeting.getLevels());
		meeting.setTitle(soadleMeeting.getTitle());
		meeting.setOptionsHash(soadleMeeting.getOptionsHash());
		meeting.setDescription(soadleMeeting.getDescription());
		meeting.setLocation(from(soadleMeeting.getLocation()));
		meeting.setDoodleReference(soadleMeeting.getDoodleReference());
		meeting.setOrigine(soadleMeeting.getOrigine());

		meeting.setParticipants(fromParticipants(soadleMeeting.getParticipants()));
		meeting.setOptions(fromOptions(soadleMeeting.getOptions()));

		return meeting;

	}

	/**
	 * @param location
	 * @return
	 */
	private static Location from(SoadleLocation soadleLocation) {
		if (soadleLocation == null)
			return null;

		Location location = new Location();

		location.setId(soadleLocation.getId());
		location.setName(soadleLocation.getName());
		location.setAddress(soadleLocation.getAddress());
		location.setCategory(soadleLocation.getCategory());

		return location;
	}

	/**
	 * @param options
	 * @return
	 */
	private static Set<Option> fromOptions(List<SoadleOption> options) {

		if (options != null)
			return options.stream().map(o -> from(o)).collect(Collectors.toSet());

		return null;
	}

	/**
	 * @param option
	 * @return
	 */
	private static Option from(SoadleOption soadleOption) {

		Option option = new Option();

		option.setId(soadleOption.getId());
		option.setAllday(soadleOption.getAllday());
		option.setAvailable(soadleOption.getAvailable());
		option.setDate(soadleOption.getDate());
		option.setStart(soadleOption.getStart());

		return option;
	}

	/**
	 * @param participants
	 * @return
	 */
	private static Set<Participant> fromParticipants(List<SoadleParticipant> soadleParticipants) {

		if (soadleParticipants != null)
			return soadleParticipants.stream().map(p -> from(p)).collect(Collectors.toSet());

		return null;
	}

	/**
	 * @param participant
	 * @return
	 */
	private static Participant from(SoadleParticipant soadleParticipant) {

		Participant participant = new Participant();

		participant.setId(soadleParticipant.getId());
		participant.setName(soadleParticipant.getName());
		participant.setUserId(soadleParticipant.getUserId());
		participant.setSmallAvatarUrl(soadleParticipant.getSmallAvatarUrl());
		participant.setLargeAvatarUrl(soadleParticipant.getLargeAvatarUrl());
		participant.setPreference(soadleParticipant.getPreference());

		return participant;
	}
	

}
