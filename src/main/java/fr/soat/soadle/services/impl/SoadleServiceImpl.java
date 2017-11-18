package fr.soat.soadle.services.impl;

import java.util.Date;
import java.util.List;

import fr.soat.soadle.doodle.services.DoodleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.soat.soadle.model.Meeting;
import fr.soat.soadle.repositories.MeetingRepository;
import fr.soat.soadle.services.SoadleService;

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

    @Autowired
    private DoodleService doodleService;


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
        return meetingRepository.save(meeting);
    }


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
