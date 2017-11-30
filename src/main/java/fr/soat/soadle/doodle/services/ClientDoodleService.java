package fr.soat.soadle.doodle.services;

import fr.soat.soadle.doodle.dto.DoodleDto;

/**
 * Customer doodle web service
 * @author hakim
 *
 */
public interface ClientDoodleService {
	
	
	/**
	 * @param id : id of doodle meeting 
	 * @return  doodle meeting
	 */
	DoodleDto findDoodle(String id); 
	
	/**
	 * @param doodleDto
	 * @return
	 */
	DoodleDto createDoodle(DoodleDto doodleDto);
}
