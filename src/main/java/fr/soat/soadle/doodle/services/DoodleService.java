package fr.soat.soadle.doodle.services;

import fr.soat.soadle.model.Meeting;

/**
 * @author hakim
 *
 */
public interface DoodleService {
	
	
	/**
	 * @param id : id of doodle meeting 
	 * @return  doodle meeting
	 */
	Meeting findDoodle(String id);
	
	/**
	 * @param meeting
	 * @return
	 */
	Meeting createDoodle(Meeting meeting);

}
