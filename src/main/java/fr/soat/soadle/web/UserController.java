package fr.soat.soadle.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.soat.soadle.security.services.AuthenticationService;

import java.security.Principal;

/**
 * @author abenchabana
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

	/**
	 * 
	 */
	@Autowired
	AuthenticationService authenticationService;

	/**
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public Principal user(Principal user) {
		return user;
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/auth", method = RequestMethod.GET)
	public Authentication auth() {
		return authenticationService.getAuthentication();
	}
	
}
