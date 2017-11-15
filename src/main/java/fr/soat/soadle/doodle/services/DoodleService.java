package fr.soat.soadle.doodle.services;

import fr.soat.soadle.model.Soadle;

/**
 * @author hakim
 *
 */
public interface DoodleService {
	
	
	/**
	 * @param id
	 * @return
	 */
	Soadle findDoodle(String id);

}
