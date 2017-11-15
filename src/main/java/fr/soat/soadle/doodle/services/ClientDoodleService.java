package fr.soat.soadle.doodle.services;

import fr.soat.soadle.doodle.dto.DoodleDto;

/**
 * @author hakim
 *
 */
public interface ClientDoodleService {
	
	
	/**
	 * @param id
	 * @return
	 */
	DoodleDto findDoodle(String id); 
}
