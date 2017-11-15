package fr.soat.soadle.doodle.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.soat.soadle.doodle.services.ClientDoodleService;
import fr.soat.soadle.doodle.services.DoodleService;
import fr.soat.soadle.doodle.utils.DoodleTransformerUtils;
import fr.soat.soadle.model.Soadle;

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
	public Soadle findDoodle(String id)
	{
				 
		 return DoodleTransformerUtils.from(clientDoodleService.findDoodle(id));
		 
	}

}
