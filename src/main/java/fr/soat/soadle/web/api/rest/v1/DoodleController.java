package fr.soat.soadle.web.api.rest.v1;

import fr.soat.soadle.security.services.AuthenticationService;
import fr.soat.soadle.services.DoodleQueryService;
import fr.soat.soadle.web.api.doodle.DoodleWebRepresentation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import fr.soat.soadle.model.Meeting;
import fr.soat.soadle.services.DoodleRepositorieService;
import fr.soat.soadle.web.api.dto.v1.SoadleMeeting;
import fr.soat.soadle.web.api.dto.v1.SoadleParticipant;
import fr.soat.soadle.web.api.utils.SoadleTransformer;

import java.util.Collection;
import java.util.stream.Collectors;

import static fr.soat.soadle.utils.ObjectToGeson.toGeson;
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

	private static final Logger LOGGER = LoggerFactory.getLogger(DoodleController.class);
	
	/**
	 * 
	 */
	@Autowired
	private AuthenticationService authenticationService;
		
	
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
	
		return SoadleTransformer.to(doodleRepositorieService.findDoodle(id), authenticationService.getAuthentication(), true);
	}
	
	/**
	 * @param doodleReference : reference to doodle meeting
	 * @return  doodle meeting
	 */
	@RequestMapping(value = "/import/{id}", method = RequestMethod.GET)
	public SoadleMeeting addDoodleMeeting(@PathVariable("id") String id) {

		return SoadleTransformer.to(doodleRepositorieService.addDoodleMeeting(id), authenticationService.getAuthentication(), true);
	}
	
	
	
	/**
	 * @param soadleMeeting : doodle a créer 
	 * @return soadleMeeting
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public SoadleMeeting createDoodleMeeting(@RequestBody SoadleMeeting soadleMeeting) {
			
		Meeting meeting = doodleRepositorieService.createDoodle(SoadleTransformer.from(soadleMeeting));
				
		return SoadleTransformer.to(meeting, authenticationService.getAuthentication(), true);
	}
	
	/**
	 * @param id
	 * @param soadleMeeting
	 */
	@RequestMapping(value = "/{id}" , method = RequestMethod.PUT)
	public void saveMeeting(@PathVariable("id") String id, @RequestBody SoadleMeeting soadleMeeting) {
		doodleRepositorieService.updateMeeting(SoadleTransformer.from(soadleMeeting));
	}

	
	/**
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") String id) {
		doodleRepositorieService.delete(id);
	}

	@RequestMapping(value = "/participe/{id}", method = RequestMethod.POST)
	public void participe(@PathVariable("id") String id, @RequestBody SoadleParticipant soadleParticipant) {

		LOGGER.debug(toGeson(soadleParticipant));
		
		doodleRepositorieService.participe(id,SoadleTransformer.from(soadleParticipant));
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
