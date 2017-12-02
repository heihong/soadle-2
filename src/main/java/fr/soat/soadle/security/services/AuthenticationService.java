package fr.soat.soadle.security.services;

import fr.soat.soadle.security.model.SoadleAuthentication;

/**
 * @author hakim
 *
 */
public interface AuthenticationService {
	
	/**
	 * @return
	 */
	SoadleAuthentication getAuthentication();

}
