package fr.soat.soadle.web.api.rest.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import fr.soat.soadle.services.SoadleService;
import fr.soat.soadle.web.api.dto.v1.SoadleMeeting;
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
	 * Sodale service meeting 
	 */
	@Autowired
	private SoadleService soadleService;
	

	/**
	 * @param id : id of soadle meeting 
	 * @return  soadle meeting
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public SoadleMeeting soadle(@PathVariable("id") String id) {

		return SoadleTransformer.to(soadleService.find(id));
	}
	
	
	/**
	 * @return all sodale meetings
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<SoadleMeeting> all() {

		return SoadleTransformer.to(soadleService.findAll());
	}


}
