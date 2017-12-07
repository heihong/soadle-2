package fr.soat.soadle.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

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
	public Meeting findDoodle(final String id) {    	
    	return Observable.zip(
    			       Observable.fromCallable(() ->  doodleService.findDoodle(id))
    			                                      .subscribeOn(Schedulers.io()),
    			       Observable.fromCallable(() ->  meetingRepository.findById(id))
    			                                        .subscribeOn(Schedulers.io()),
    			       (meeting , optional)       -> {
    							
							if(optional.isPresent() && meeting !=null)
							{
								meeting.setTags(optional.get().getTags());
								meeting.setDoodleReference(optional.get().getDoodleReference());
								meeting.setImportationDate(optional.get().getImportationDate());
								//meeting.setPictures(optional.get().getPictures().stream().collect(Collectors.toSet()));
							};
							return meeting;
    			       }
    			      ).blockingSingle();		
	}
	
    /**
     * @see fr.soat.soadle.services.DoodleRepositorieService#createDoodle(fr.soat.soadle.model.Meeting)
     */
    @Override
	public  Meeting createDoodle(Meeting pMeeting) {
    	Meeting meeting = doodleService.createDoodle(pMeeting);
    	return addDoodleMeeting(meeting.getId(),pMeeting);
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
	private Meeting addDoodleMeeting(String id, Meeting meeting) {
		Meeting meetingImportedFromDoodle = 
				Observable.zip(
						       Observable.fromCallable(()       -> doodleService.findDoodle(id)),
						       Observable.fromCallable(()       -> meetingRepository.findById(id)),
						       (meetingDoodle,optionalmeeting)  -> {        
								   if(optionalmeeting.isPresent())
								   {
									 meetingDoodle.setTags(optionalmeeting.get().getTags());
									 meetingDoodle.setPictures(optionalmeeting.get().getPictures());
								   };
								   return meetingDoodle;
						       }
							 ).blockingSingle();
        
		meetingImportedFromDoodle.setDoodleReference(id);
		meetingImportedFromDoodle.setImportationDate(new Date());
			
		if(meeting != null)
		{
			meetingImportedFromDoodle.setTags(meeting.getTags());
			meetingImportedFromDoodle.setPictures(meeting.getPictures());
		}

       return meetingRepository.save(meetingImportedFromDoodle);
	}
			
	/**
	 * @see fr.soat.soadle.services.DoodleRepositorieService#updateMeeting(fr.soat.soadle.model.Meeting)
	 */
	@Override
	public void updateMeeting(Meeting meeting) {		
		addDoodleMeeting(meeting.getId(),meeting);
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
