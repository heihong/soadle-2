package fr.soat.soadle.web.api.rest.v1;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
	 * @param id
	 * @param soadleMeeting
	 */
	@RequestMapping(value = "/{id}" , method = RequestMethod.PUT)
	public void saveMeeting(@PathVariable("id") String id, @RequestBody SoadleMeeting soadleMeeting) {
		soadleService.updateMeeting(SoadleTransformer.from(soadleMeeting));
	}

	
	/**
	 * @param tag
	 * @return
	 */
	@RequestMapping(value = "/filter", method = RequestMethod.GET)
	public List<SoadleMeeting> allTag(@RequestParam("tag") String tag, 
			                          @RequestParam("dateDebut") @DateTimeFormat(pattern="yyyy-MM-dd") Date dateDebut, 
			                          @RequestParam("dateFin") @DateTimeFormat(pattern="yyyy-MM-dd") Date dateFin) {

		return SoadleTransformer.to(soadleService.findAll(tag,dateDebut, dateFin ));
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
