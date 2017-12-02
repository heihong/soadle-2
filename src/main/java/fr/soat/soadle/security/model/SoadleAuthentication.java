package fr.soat.soadle.security.model;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

public class SoadleAuthentication implements Authentication {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5665948288361389079L;
	
	
	private OAuth2Authentication authentication;
	
	
	public SoadleAuthentication(OAuth2Authentication authentication) {
		super();
		this.authentication = authentication;
	}

	@Override
	public String getName() {
		return authentication.getName();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authentication.getAuthorities();
	}

	@Override
	public Object getCredentials() {
		return authentication.getCredentials();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String,Object> getDetails() {
		return (Map<String,Object>)authentication.getUserAuthentication().getDetails();
	}

	@Override
	public Object getPrincipal() {
		return authentication.getPrincipal();
	}

	@Override
	public boolean isAuthenticated() {
		return authentication.isAuthenticated();
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated)
			throws IllegalArgumentException {
		authentication.setAuthenticated(isAuthenticated);		
	}

	

	public String getMail()
	{
		return (String)getDetails().get("email");
	}
	
}
