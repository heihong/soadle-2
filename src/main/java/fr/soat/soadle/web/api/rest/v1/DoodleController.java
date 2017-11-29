package fr.soat.soadle.web.api.rest.v1;

import fr.soat.soadle.services.DoodleQueryService;
import fr.soat.soadle.web.api.doodle.DoodleWebRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import fr.soat.soadle.doodle.services.DoodleService;
import fr.soat.soadle.services.DoodleImportService;
import fr.soat.soadle.web.api.dto.v1.SoadleMeeting;
import fr.soat.soadle.web.api.utils.SoadleTransformer;

import java.util.Collection;
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


	@Autowired
	private DoodleQueryService doodleQueryService;

	@RequestMapping(value = "/doodles", method = RequestMethod.GET)
	public @ResponseBody
	Collection<DoodleWebRepresentation> list() {
		return doodleQueryService.getAllDoodles()
				.stream()
				.map(mapper())
				.collect(Collectors.toSet());
	}

}
