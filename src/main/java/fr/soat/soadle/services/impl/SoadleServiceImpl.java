package fr.soat.soadle.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.soat.soadle.model.Soadle;
import fr.soat.soadle.repositories.SoadleRepository;
import fr.soat.soadle.services.SoadleService;

/**
 * @author hakim
 *
 */
@Service
public class SoadleServiceImpl implements SoadleService {
	
	
	 /**
	 * 
	 */
	@Autowired
	 private SoadleRepository soadleRepository;
	

	/**
	 * @see fr.soat.soadle.services.SoadleService#findSoadle(java.lang.String)
	 */
	@Override
	public Soadle findSoadle(String id) {
		return soadleRepository.getOne(id);
	}

}
