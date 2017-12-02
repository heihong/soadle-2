package fr.soat.soadle.security.services.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import fr.soat.soadle.security.model.SoadleAuthentication;
import fr.soat.soadle.security.services.AuthenticationService;

/**
 * Service of customer Authentication
 * @author abenchabana
 *
 */
@Component
public class AuthenticationServiceImpl implements AuthenticationService {
 
    /**
     * Customer Authentication
     * @see fr.soat.soadle.security.services.AuthenticationService#getAuthentication()
     */
    @Override
    public SoadleAuthentication getAuthentication() {
        return new SoadleAuthentication((OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication());
    }
}