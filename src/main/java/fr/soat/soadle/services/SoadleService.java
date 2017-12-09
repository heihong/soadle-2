package fr.soat.soadle.services;

import java.util.Date;
import java.util.List;

import fr.soat.soadle.model.Meeting;
import fr.soat.soadle.model.Participant;

/**
 * @author abenchabana
 *
 */
public interface SoadleService {
	
	/**
	 * @param id : id of sodale meeting 
	 * @return sodale meeting 
	 */
	Meeting find(String id);
	
	
	/**
	 * @return
	 */
	List<Meeting> findAll();
	
	
	/**
	 * @param tag
	 * @param dateFin 
	 * @param dateDebut 
	 * @return
	 */
	List<Meeting> findAll(String tag, Date dateDebut, Date dateFin);
	
	
    /**
     * @param meeting
     * @return
     */
    Meeting save(Meeting meeting);
    
    /**
     * @param meeting
     */
    void updateMeeting(Meeting meeting);
    
    
   
    /**
     * @param id
     */
    void delete(String id);


	
    /**
     * @param mettnigId
     * @param participant
     * @return
     */
    Participant participe(String mettnigId, Participant participant); 
}
