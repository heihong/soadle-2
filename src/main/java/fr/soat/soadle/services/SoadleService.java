package fr.soat.soadle.services;

import java.util.List;

import fr.soat.soadle.model.Meeting;

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
     * @param id
     * @param tags
     */
    void saveTags(String id, String tags);
    
    
   
    /**
     * @param id
     */
    void delete(String id);

}
