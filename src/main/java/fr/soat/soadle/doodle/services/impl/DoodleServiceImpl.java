package fr.soat.soadle.doodle.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.soat.soadle.doodle.services.ClientDoodleService;
import fr.soat.soadle.doodle.services.DoodleService;
import fr.soat.soadle.doodle.utils.DoodleTransformer;
import fr.soat.soadle.model.Meeting;

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
	 * @see fr.soat.soadle.doodle.services.DoodleService#findDoodle(java.lang.String)
	 */
	@Override
	public Meeting findDoodle(String id)
	{
				 
		 return DoodleTransformer.from(clientDoodleService.findDoodle(id));
		 
	}



	@Override
	public Meeting createDoodle(Meeting pMeeting) {
		return DoodleTransformer.from(clientDoodleService.createDoodle(DoodleTransformer.to(pMeeting)));
	}

}
