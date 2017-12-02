package fr.soat.soadle.web.user.rest.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.soat.soadle.security.model.SoadleAuthentication;
import fr.soat.soadle.security.services.AuthenticationService;

import java.security.Principal;

/**
 * user controller
 * @author abenchabana
 *
 */
@RestController
@RequestMapping("/user/{version:v1}")
public class UserController {

	/**
	 * 
	 */
	@Autowired
	AuthenticationService authenticationService;

	/**
	 * @param user
	 * @return user authentication
	 */
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public Principal user(Principal user) {
		return user;
	}

	/**
	 * @return customer authentication
	 */
	@RequestMapping(value = "/auth", method = RequestMethod.GET)
	public SoadleAuthentication auth() {
		return authenticationService.getAuthentication();
	}
	
}
