package fr.soat.soadle.doodle.services;

import fr.soat.soadle.model.Meeting;
import fr.soat.soadle.model.Participant;

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
		

	/**
	 * @param mettnigId
	 * @param pParticipant
	 * @return
	 */
	Participant participe(String mettnigId, Participant pParticipant);
		
}
