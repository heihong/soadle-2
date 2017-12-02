package fr.soat.soadle.services;


import fr.soat.soadle.model.Meeting;
import fr.soat.soadle.model.Participant;

/**
 * @author hakim
 *
 */
public interface DoodleRepositorieService {
	

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
	 * @param doodleReference: id of doodle meeting
	 * @return Soadle Meeting
	 */
	Meeting addDoodleMeeting(String id);
	
	/**
	 * @param id
	 */
	void delete(String id);

	/**
	 * @param mettnigId
	 * @param pParticipant
	 * @return
	 */
	public Participant participe(String mettnigId, Participant pParticipant);

}
