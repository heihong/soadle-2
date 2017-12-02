package fr.soat.soadle.doodle.services;

import fr.soat.soadle.model.Meeting;
import fr.soat.soadle.model.Participant;

/**
 * Customer doodle web service
 * @author hakim
 *
 */
public interface ClientDoodleService {
	
	
	/**
	 * @param id : id of doodle meeting 
	 * @return   meeting
	 */
	Meeting findDoodle(String id); 
	
	/**
	 * @param Meeting
	 * @return
	 */
	Meeting createDoodle(Meeting meeting);
	
	/**
	 * @param mettnigId
	 * @param pParticipant
	 * @return
	 */
	Participant participe(String mettnigId, Participant pParticipant);
}
