package fr.soat.soadle.web.rest.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.soat.soadle.doodle.services.DoodleService;
import fr.soat.soadle.services.SoadleService;
import fr.soat.soadle.web.dto.v1.SoadleMeeting;
import fr.soat.soadle.web.utils.SoadleTransformerUtils;

/**
 * @author hakim
 *
 */
@RestController
@RequestMapping("/api/{version:v1}")
public class SoadleController {

	@Autowired
	private DoodleService doodleService;

	@Autowired
	private SoadleService soadleService;

	@RequestMapping(value = "/doodle/{id}", method = RequestMethod.GET)
	public SoadleMeeting doodle(@PathVariable("id") String id) {

		return SoadleTransformerUtils.to(doodleService.findDoodle(id));
	}

	@RequestMapping(value = "/soadle/{id}", method = RequestMethod.GET)
	public SoadleMeeting soadle(@PathVariable("id") String id) {

		return SoadleTransformerUtils.to(soadleService.findSoadle(id));
	}

}
