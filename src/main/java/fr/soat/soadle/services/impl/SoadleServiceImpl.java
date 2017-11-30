package fr.soat.soadle.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.soat.soadle.model.EnumOrigine;
import fr.soat.soadle.model.Meeting;
import fr.soat.soadle.repositories.MeetingRepository;
import fr.soat.soadle.security.services.AuthenticationService;
import fr.soat.soadle.services.SoadleService;
import fr.soat.soadle.utils.InitiatorfromAuthentication;

/**
 * @author hakim
 */
@Service
@Transactional
public class SoadleServiceImpl implements SoadleService {


    /**
     *
     */
    @Autowired
    private MeetingRepository meetingRepository;
    
    
	/**
	 * 
	 */
	@Autowired
	private AuthenticationService authenticationService;
	

    /**
     * @see fr.soat.soadle.services.SoadleService#find(java.lang.String)
     */
    @Override
    public Meeting find(String id) {
        return meetingRepository.getOne(id);
    }


    /**
     * @see fr.soat.soadle.services.SoadleService#findAll()
     */
    @Override
    public List<Meeting> findAll() {
        return meetingRepository.findAll();
    }


    /**
     * @see fr.soat.soadle.services.SoadleService#save(fr.soat.soadle.model.Meeting)
     */
    @Override
    public Meeting save(Meeting meeting) {		
		meeting.setInitiator(InitiatorfromAuthentication.from((OAuth2Authentication) authenticationService.getAuthentication()));
		meeting.setId(UUID.randomUUID().toString());
		meeting.setOrigine(EnumOrigine.SOADLE.toString());
        return meetingRepository.save(meeting);
    }

}
