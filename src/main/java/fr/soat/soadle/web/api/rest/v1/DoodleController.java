package fr.soat.soadle.web.api.rest.v1;

import fr.soat.soadle.services.DoodleQueryService;
import fr.soat.soadle.web.api.doodle.DoodleWebRepresentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import fr.soat.soadle.doodle.services.DoodleService;
import fr.soat.soadle.model.Meeting;
import fr.soat.soadle.services.DoodleRepositorieService;
import fr.soat.soadle.web.api.dto.v1.SoadleMeeting;
import fr.soat.soadle.web.api.utils.SoadleTransformer;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import static fr.soat.soadle.web.api.doodle.DoodleWebRepresentation.mapper;

/**
 * Doodle controller 
 * 
 * @author hakim
 *
 */
@RestController
@RequestMapping("/api/{version:v1}/doodle")
public class DoodleController {

	/**
	 * service doodle meeting
	 */
	@Autowired
	private DoodleService doodleService;
	
	
	@Autowired
	private DoodleRepositorieService doodleRepositorieService;
	
	
	/**
	 * 
	 */
	@Autowired
	private DoodleQueryService doodleQueryService;
	
	/**
	 * @param id : id of doodle meeting 
	 * @return  doodle meeting
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public SoadleMeeting doodle(@PathVariable("id") String id) {

		Meeting meeting = doodleService.findDoodle(id);
		
		Optional<Meeting> optional = doodleRepositorieService.findDoodle(id);
		
		if(optional.isPresent() && meeting !=null)
		{
			meeting.setTags(optional.get().getTags());
			meeting.setDoodleReference(optional.get().getDoodleReference());
			meeting.setImportationDate(optional.get().getImportationDate());
		}
		
		
		return SoadleTransformer.to(meeting);
	}
	
	/**
	 * @param doodleReference : reference to doodle meeting
	 * @return  doodle meeting
	 */
	@RequestMapping(value = "/import/{id}", method = RequestMethod.GET)
	public SoadleMeeting addDoodleMeeting(@PathVariable("id") String id) {

		return SoadleTransformer.to(doodleRepositorieService.addDoodleMeeting(id));
	}
	
	
	
	/**
	 * @param soadleMeeting : doodle a cr�er 
	 * @return soadleMeeting
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public SoadleMeeting createDoodleMeeting(@RequestBody SoadleMeeting soadleMeeting) {

		Meeting meeting = SoadleTransformer.from(soadleMeeting);		
		
		meeting = doodleService.createDoodle(meeting);
				
		return SoadleTransformer.to(doodleRepositorieService.addDoodleMeeting(meeting.getId()));
	}
	
	/**
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") String id) {
		doodleRepositorieService.delete(id);
	}



	/**
	 * @return
	 */
	@RequestMapping(value = "/doodles", method = RequestMethod.GET)
	public @ResponseBody
	Collection<DoodleWebRepresentation> list() {
		return doodleQueryService.getAllDoodles()
				.stream()
				.map(mapper())
				.collect(Collectors.toSet());
	}

}
