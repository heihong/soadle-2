package fr.soat.soadle.services;

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
	 * @return all sodale meetings 
	 */
	List<Meeting> findAll();
	
	
	List<Meeting> findByTag(String tag);
	
	
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
