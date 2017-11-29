package fr.soat.soadle.services.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.soat.soadle.doodle.services.DoodleService;
import fr.soat.soadle.model.Meeting;
import fr.soat.soadle.repositories.MeetingRepository;
import fr.soat.soadle.services.DoodleImportService;

/**
 * Class import a doodle into sodal 
 * @author hakim
 *
 */
@Service
public class DoodleImportServiceImpl implements DoodleImportService  {


    /**
     *
     */
    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private DoodleService doodleService;

	/**
	 * @see fr.soat.soadle.services.DoodleImportService#addDoodleMeeting(java.lang.String)
	 */
	@Override
	public Meeting addDoodleMeeting(String doodleReference) {
        Meeting meetingImportedFromDoodle = doodleService.findDoodle(doodleReference);
        meetingImportedFromDoodle.setDoodleReference(doodleReference);
        meetingImportedFromDoodle.setImportationDate(new Date());
        //TODO
        //add the current user who imported the doodle meeting
        return meetingRepository.save(meetingImportedFromDoodle);
	}

}
