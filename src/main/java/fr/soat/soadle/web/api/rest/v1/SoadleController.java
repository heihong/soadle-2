package fr.soat.soadle.web.api.rest.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import fr.soat.soadle.model.Meeting;
import fr.soat.soadle.security.services.AuthenticationService;
import fr.soat.soadle.services.SoadleService;
import fr.soat.soadle.web.api.dto.v1.SoadleMeeting;
import fr.soat.soadle.web.api.dto.v1.SoadleParticipant;
import fr.soat.soadle.web.api.utils.SoadleTransformer;

/**
 * Soadle controller
 * 
 * @author hakim
 *
 */
@RestController
@RequestMapping("/api/{version:v1}/soadle")
public class SoadleController {

	/**
	 * 
	 */
	@Autowired
	private AuthenticationService authenticationService;
	
	/**
	 * Sodale service meeting
	 */
	@Autowired
	private SoadleService soadleService;

	/**
	 * @param id
	 *            : id of soadle meeting
	 * @return soadle meeting
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public SoadleMeeting soadle(@PathVariable("id") String id) {

		return SoadleTransformer.to(soadleService.find(id), authenticationService.getAuthentication(), true);
	}

	/**
	 * @return all sodale meetings
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<SoadleMeeting> all() {

		return SoadleTransformer.to(soadleService.findAll());
	}
	
	/**
	 * @param tag
	 * @return
	 */
	@RequestMapping(value = "/tags/{tag}", method = RequestMethod.GET)
	public List<SoadleMeeting> allTag(@PathVariable("tag") String tag) {

		return SoadleTransformer.to(soadleService.findByTag(tag));
	}
	
	@RequestMapping(value = "/tags/{id}/{tags}", method = RequestMethod.GET)
	public void saveTags(@PathVariable("id") String id, @PathVariable("tags") String tags) {
		soadleService.saveTags(id,tags);
	}


	/**
	 * @param soadleMeeting
	 *            : soalde a créer
	 * @return soadleMeeting
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public SoadleMeeting createSoadleMeeting(
			@RequestBody SoadleMeeting soadleMeeting) {

		Meeting meeting = SoadleTransformer.from(soadleMeeting);

		return SoadleTransformer.to(soadleService.save(meeting), authenticationService.getAuthentication(), true);
	}
	
	
	@RequestMapping(value = "/participe/{id}", method = RequestMethod.POST)
	public void participe(@PathVariable("id") String id, @RequestBody SoadleParticipant soadleParticipant) {

		soadleService.participe(id,SoadleTransformer.from(soadleParticipant));
	}
	
	
	/**
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") String id) {
		soadleService.delete(id);
	}


}
