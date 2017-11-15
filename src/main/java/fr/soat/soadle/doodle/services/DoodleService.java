package fr.soat.soadle.doodle.services;

import fr.soat.soadle.model.Meeting;

/**
 * @author hakim
 *
 */
public interface DoodleService {
	
	
	/**
	 * @param id
	 * @return
	 */
	Meeting findDoodle(String id);

}
