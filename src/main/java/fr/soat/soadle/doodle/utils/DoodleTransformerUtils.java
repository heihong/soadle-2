package fr.soat.soadle.doodle.utils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import fr.soat.soadle.doodle.dto.DoodleDto;
import fr.soat.soadle.doodle.dto.DoodleLocationDto;
import fr.soat.soadle.doodle.dto.DoodleOptionDto;
import fr.soat.soadle.doodle.dto.DoodleParticipantDto;
import fr.soat.soadle.model.Location;
import fr.soat.soadle.model.Option;
import fr.soat.soadle.model.Participant;
import fr.soat.soadle.model.Soadle;

/**
 * @author hakim
 *
 */
public class DoodleTransformerUtils {

	/**
	 * @param doodle
	 * @return
	 */
	public static Soadle from(DoodleDto doodle) {
		
		if(doodle == null) return null;

		Soadle soadle = new Soadle();

		soadle.setId(doodle.getId());
		soadle.setLatestChange(doodle.getLatestChange());
		soadle.setInitiated(doodle.getInitiated());
		soadle.setParticipantsCount(doodle.getParticipantsCount());
		soadle.setInviteesCount(doodle.getInviteesCount());
		soadle.setType(doodle.getType());
		soadle.setPreferencesType(doodle.getPreferencesType());
		soadle.setState(doodle.getState());
		soadle.setLocale(doodle.getLocale());
		soadle.setDevice(doodle.getDevice());
		soadle.setLevels(doodle.getLevels());
		soadle.setTitle(doodle.getTitle());
		soadle.setLocation(from(doodle.getLocation()));
		
		soadle.setParticipants(fromParticipants(doodle.getParticipants()));
		soadle.setOptions(fromOptions(doodle.getOptions()));

		return soadle;

	}

	/**
	 * @param doodleLocation
	 * @return
	 */
	private static Location from(DoodleLocationDto doodleLocation) {
		if(doodleLocation == null) return null;
		
		Location location = new Location();
		
		location.setName(doodleLocation.getName());
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
		
		return participant;
	}
		
		
}
