package fr.soat.soadle.services;

import fr.soat.soadle.model.Meeting;

/**
 * @author hakim
 *
 */
public interface DoodleRepositorieService {
	
	

	/**
	 * @param doodleReference: id of doodle meeting
	 * @return Soadle Meeting
	 */
	Meeting addDoodleMeeting(String id);
	
	/**
	 * @param id
	 */
	void delete(String id);

}
