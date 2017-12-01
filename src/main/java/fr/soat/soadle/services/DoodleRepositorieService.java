package fr.soat.soadle.services;

import java.util.Optional;

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
	
	/**
	 * @param id
	 * @return
	 */
	Optional<Meeting> findDoodle(String id);

}
