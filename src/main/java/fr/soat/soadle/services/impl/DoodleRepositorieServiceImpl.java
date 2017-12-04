package fr.soat.soadle.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.soat.soadle.doodle.services.DoodleService;
import fr.soat.soadle.model.Meeting;
import fr.soat.soadle.model.Participant;
import fr.soat.soadle.repositories.MeetingRepository;
import fr.soat.soadle.repositories.ParticipantRepository;
import fr.soat.soadle.services.DoodleRepositorieService;

/**
 * Class import a doodle into sodal 
 * @author hakim
 *
 */
@Service
@Transactional
public class DoodleRepositorieServiceImpl implements DoodleRepositorieService  {


    /**
     *
     */
    @Autowired
    private MeetingRepository meetingRepository;
	
	/**
	 * 
	 */
	@Autowired
	private ParticipantRepository  participantRepository;	

    /**
     * 
     */
    @Autowired
    private DoodleService doodleService;
      
    

    /**
     * @see fr.soat.soadle.services.DoodleRepositorieService#findDoodle(java.lang.String)
     */
    @Override
	public Meeting findDoodle(String id) {
		 Meeting meeting =  doodleService.findDoodle(id);
		
        Optional<Meeting> optional = meetingRepository.findById(id);
		
		if(optional.isPresent() && meeting !=null)
		{
			meeting.setTags(optional.get().getTags());
			meeting.setDoodleReference(optional.get().getDoodleReference());
			meeting.setImportationDate(optional.get().getImportationDate());
		}
		
		return meeting;
	}
	
    /**
     * @see fr.soat.soadle.services.DoodleRepositorieService#createDoodle(fr.soat.soadle.model.Meeting)
     */
    @Override
	public  Meeting createDoodle(Meeting pMeeting) {
    	Meeting meeting = doodleService.createDoodle(pMeeting);
    	return addDoodleMeeting(meeting.getId(),pMeeting.getTags());
	}		

	/**
	 * @see fr.soat.soadle.services.DoodleRepositorieService#addDoodleMeeting(java.lang.String)
	 */
	@Override
	public Meeting addDoodleMeeting(String id) {
		return addDoodleMeeting(id,null);
	}
	
	/**
	 * @param id
	 * @param tag
	 * @return
	 */
	private Meeting addDoodleMeeting(String id, String tag) {
        Meeting meetingImportedFromDoodle = doodleService.findDoodle(id);
        Optional<Meeting>  Optionalmeeting = meetingRepository.findById(id);
        
        meetingImportedFromDoodle.setDoodleReference(id);
        meetingImportedFromDoodle.setImportationDate(new Date());
        
        if(Optionalmeeting.isPresent())
        {
        	meetingImportedFromDoodle.setTags(Optionalmeeting.get().getTags());
        } 
        
        if(tag != null)
        {
            meetingImportedFromDoodle.setTags(tag);
        }

        return meetingRepository.save(meetingImportedFromDoodle);
	}
	
	
    /**
     * @see fr.soat.soadle.services.DoodleRepositorieService#delete(java.lang.String)
     */
    @Override
    public void delete(String id) {
        meetingRepository.deleteById(id);
    }

	/**
	 * @see fr.soat.soadle.services.DoodleRepositorieService#participe(java.lang.String, fr.soat.soadle.model.Participant)
	 */
	@Override
	public Participant participe(String mettnigId, Participant pParticipant) {
		
		Meeting meeting = doodleService.findDoodle(mettnigId);
		
		pParticipant.setOptionsHash(meeting.getOptionsHash());
		
		Participant participantDoodle = doodleService.participe(mettnigId, pParticipant);
		
		List<Participant> participants = participantRepository.findByDoodleId(mettnigId, participantDoodle.getDoodleId());
		
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
		
		if(StringUtils.isNoneBlank(pParticipant.getEmail()))
		{
		  participant.setEmail(pParticipant.getEmail());	
		}
		
		participant.setOptionsHash(meeting.getOptionsHash());
		participant.setDoodleId(participantDoodle.getDoodleId());
		participant.setUserId(participantDoodle.getUserId());		
		participant.setPreference(pParticipant.getPreference());
		
		participantRepository.save(participant);
		
		meetingRepository.updateParticipantsCount(mettnigId);
	
		
		return participant;		
	}
	
}
