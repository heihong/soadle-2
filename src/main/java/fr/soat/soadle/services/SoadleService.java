package fr.soat.soadle.services;

import fr.soat.soadle.model.Soadle;

/**
 * @author abenchabana
 *
 */
public interface SoadleService {
	
	/**
	 * @param id
	 * @return
	 */
	Soadle findSoadle(String id);

}
