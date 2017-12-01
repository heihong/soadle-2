package fr.soat.soadle.doodle.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import fr.soat.soadle.doodle.services.ClientDoodleService;
import fr.soat.soadle.doodle.services.DoodleService;
import fr.soat.soadle.model.EnumOrigine;
import fr.soat.soadle.model.Meeting;
import fr.soat.soadle.security.services.AuthenticationService;
import fr.soat.soadle.utils.InitiatorfromAuthentication;

/**
 * @author hakim
 *
 */
@Service
public class DoodleServiceImpl implements DoodleService {

	/**
	 * 
	 */
	@Autowired
	private ClientDoodleService clientDoodleService;
	
	/**
	 * 
	 */
	@Autowired
	private AuthenticationService authenticationService; 
	
	
	/**
	 * @see fr.soat.soadle.doodle.services.DoodleService#findDoodle(java.lang.String)
	 */
	@Override
	public Meeting findDoodle(String id) {

		return clientDoodleService.findDoodle(id);		

	}

	@Override
	public Meeting createDoodle(Meeting meeting) {
		meeting.setOrigine(EnumOrigine.DOODLE.toString());

		meeting.setInitiator(InitiatorfromAuthentication
				.from((OAuth2Authentication) authenticationService.getAuthentication()));

		meeting.setLocale("fr_FR");
		meeting.setType("DATE");
		
		return clientDoodleService.createDoodle(meeting);
	}

}
