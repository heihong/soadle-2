package fr.soat.soadle.services.impl;

import java.util.Date;

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
        meetingImportedFromDoodle.setDoodleReference(id);
        meetingImportedFromDoodle.setImportationDate(new Date());

        return meetingRepository.save(meetingImportedFromDoodle);
	}
	
	
    /**
     * @see fr.soat.soadle.services.DoodleRepositorieService#delete(java.lang.String)
     */
    @Override
    public void delete(String id) {
        meetingRepository.deleteById(id);
    }


}
