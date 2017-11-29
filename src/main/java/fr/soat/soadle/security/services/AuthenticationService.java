package fr.soat.soadle.security.services;

import org.springframework.security.core.Authentication;

/**
 * @author hakim
 *
 */
public interface AuthenticationService {
	
	/**
	 * @return
	 */
	Authentication getAuthentication();

}
