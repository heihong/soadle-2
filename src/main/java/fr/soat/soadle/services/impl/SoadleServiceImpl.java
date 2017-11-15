package fr.soat.soadle.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.soat.soadle.model.Meeting;
import fr.soat.soadle.repositories.MeetingRepository;
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
	 private MeetingRepository meetingRepository;
	

	/**
	 * @see fr.soat.soadle.services.SoadleService#find(java.lang.String)
	 */
	@Override
	public Meeting find(String id) {
		return meetingRepository.getOne(id);
	}


	@Override
	public List<Meeting> findAll() {
		return meetingRepository.findAll();
	}
	
	

}
