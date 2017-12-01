package fr.soat.soadle.services.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.soat.soadle.doodle.services.DoodleService;
import fr.soat.soadle.model.Meeting;
import fr.soat.soadle.repositories.MeetingRepository;
import fr.soat.soadle.services.DoodleRepositorieService;

/**
 * Class import a doodle into sodal 
 * @author hakim
 *
 */
@Service
public class DoodleRepositorieServiceImpl implements DoodleRepositorieService  {


    /**
     *
     */
    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private DoodleService doodleService;

	/**
	 * @see fr.soat.soadle.services.DoodleRepositorieService#addDoodleMeeting(java.lang.String)
	 */
	@Override
	public Meeting addDoodleMeeting(String id) {
        Meeting meetingImportedFromDoodle = doodleService.findDoodle(id);
        Optional<Meeting>  Optionalmeeting = meetingRepository.findById(id);
        
        meetingImportedFromDoodle.setDoodleReference(id);
        meetingImportedFromDoodle.setImportationDate(new Date());
        
        if(Optionalmeeting.isPresent())
        {
        	meetingImportedFromDoodle.setTags(Optionalmeeting.get().getTags());
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


	@Override
	public Optional<Meeting> findDoodle(String id) {
		return meetingRepository.findById(id);
	}


}
