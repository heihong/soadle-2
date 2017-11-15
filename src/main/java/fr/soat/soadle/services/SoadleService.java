package fr.soat.soadle.services;

import java.util.List;

import fr.soat.soadle.model.Meeting;

/**
 * @author abenchabana
 *
 */
public interface SoadleService {
	
	/**
	 * @param id
	 * @return
	 */
	Meeting find(String id);
	
	
	List<Meeting> findAll();

}
