package fr.soat.soadle.web.api.rest.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.soat.soadle.doodle.services.DoodleService;
import fr.soat.soadle.services.DoodleImportService;
import fr.soat.soadle.web.api.dto.v1.SoadleMeeting;
import fr.soat.soadle.web.api.utils.SoadleTransformer;

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
	private DoodleImportService doodleImportService;

	
	/**
	 * @param id : id of doodle meeting 
	 * @return  doodle meeting
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public SoadleMeeting doodle(@PathVariable("id") String id) {

		return SoadleTransformer.to(doodleService.findDoodle(id));
	}
	
	/**
	 * @param doodleReference : reference to doodle meeting
	 * @return  doodle meeting
	 */
	@RequestMapping(value = "/import/{id}", method = RequestMethod.GET)
	public SoadleMeeting addDoodleMeeting(@PathVariable("id") String id) {

		return SoadleTransformer.to(doodleImportService.addDoodleMeeting(id));
	}




}
