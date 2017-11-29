package fr.soat.soadle.web.api.utils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
	 * @param findAll
	 *            : Soadle meetings
	 * @return
	 */
	public static List<SoadleMeeting> to(List<Meeting> findAll) {

		if (findAll != null)
			return findAll.stream().map(m -> to(m)).collect(Collectors.toList());

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
		response.setLocation(from(meeting.getLocation()));		

		response.setParticipants(toParticipants(meeting.getParticipants()));
		response.setOptions(toOptions(meeting.getOptions()));

		return response;

	}

	/**
	 * @param location
	 * @return
	 */
	private static SoadleLocation from(Location location) {
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

}
