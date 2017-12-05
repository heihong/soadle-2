package fr.soat.soadle.services.impl;

import java.util.List;
import java.util.UUID;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.soat.soadle.model.EnumOrigine;
import fr.soat.soadle.model.Meeting;
import fr.soat.soadle.model.Participant;
import fr.soat.soadle.repositories.MeetingRepository;
import fr.soat.soadle.repositories.ParticipantRepository;
import fr.soat.soadle.security.model.SoadleAuthentication;
import fr.soat.soadle.security.services.AuthenticationService;
import fr.soat.soadle.services.SoadleService;
import fr.soat.soadle.utils.MeetingUtils;

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
    	
	    SoadleAuthentication authentication = authenticationService.getAuthentication();
		
		Participant initiator = new Participant();		
		initiator.setName(authentication.getName());
		initiator.setEmail(authentication.getMail());
		
		meeting.setInitiator(initiator);		
		meeting.setId(UUID.randomUUID().toString());
		meeting.setOrigine(EnumOrigine.SOADLE.toString());
        return meetingRepository.save(meeting);
    }
    

	
	/**
	 * @see fr.soat.soadle.services.SoadleService#updateMeeting(fr.soat.soadle.model.Meeting)
	 */
	@Override
	public void updateMeeting(Meeting meeting) {
		Meeting lMeeting = meetingRepository.getOne(meeting.getId());		
		MeetingUtils.mergeMeetingInMeetingDb(meeting, lMeeting);		
		meetingRepository.save(lMeeting);
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
		
		if(StringUtils.isNoneBlank(pParticipant.getSmallAvatarUrl()))
		{
		  participant.setSmallAvatarUrl(pParticipant.getSmallAvatarUrl());
		}
		
		participant.setPreference(pParticipant.getPreference());
		
		participantRepository.save(participant);
		
		meetingRepository.updateParticipantsCount(mettnigId);
	
		
		return participant;
		
	}




}
