package fr.soat.soadle.services;

import fr.soat.soadle.model.Meeting;

/**
 * @author hakim
 *
 */
public interface DoodleImportService {
	
	

	/**
	 * @param doodleReference: id of doodle meeting
	 * @return Soadle Meeting
	 */
	Meeting addDoodleMeeting(String doodleReference);

}
