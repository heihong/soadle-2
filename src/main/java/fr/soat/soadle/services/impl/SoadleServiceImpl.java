package fr.soat.soadle.services.impl;

import java.util.List;
import java.util.UUID;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.soat.soadle.model.EnumOrigine;
import fr.soat.soadle.model.Meeting;
import fr.soat.soadle.model.Participant;
import fr.soat.soadle.repositories.MeetingRepository;
import fr.soat.soadle.repositories.ParticipantRepository;
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
   private ParticipantRepository participantRepository;
    
    
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
     * @see fr.soat.soadle.services.SoadleService#findByTag(java.lang.String)
     */
    @Override
    public List<Meeting> findByTag(String tag) {
        return meetingRepository.findByTag(tag);
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
    

	/**
	 * @see fr.soat.soadle.services.SoadleService#saveTags(java.lang.String, java.lang.String)
	 */
	@Override
	public void saveTags(String id, String tags) {
		meetingRepository.saveTags(id,tags);
	}
    
    
    
    /**
     * @see fr.soat.soadle.services.SoadleService#delete(java.lang.String)
     */
    @Override
    public void delete(String id) {
        meetingRepository.deleteById(id);
    }


	/**
	 * @see fr.soat.soadle.services.SoadleService#participe(java.lang.String, fr.soat.soadle.model.Participant)
	 */
	@Override
	public Participant participe(String mettnigId, Participant pParticipant) {
		
		List<Participant> participants = participantRepository.findByEmail(mettnigId, pParticipant.getEmail());
		
		Participant participant = null;
		
		if(CollectionUtils.isNotEmpty(participants))
		{
			participant = participants.get(0);
		}  else
		{
			participant = new Participant();
			participant.setMettnigId(mettnigId);
		}
		
		participant.setName(pParticipant.getName());
		participant.setEmail(pParticipant.getEmail());
		participant.setPreference(pParticipant.getPreference());
		
		participantRepository.save(participant);
	
		
		return participant;
		
	}




}
