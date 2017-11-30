package fr.soat.soadle.utils;

import java.util.Map;

import org.springframework.security.oauth2.provider.OAuth2Authentication;

import fr.soat.soadle.model.Participant;

public class InitiatorfromAuthentication {
	
	
	
	@SuppressWarnings("unchecked")
	public static Participant from(OAuth2Authentication authentication)
	{
        Participant initiator = new Participant();		
		initiator.setName(authentication.getName());
		
		Map<String,String> map = (Map<String,String>)authentication.getUserAuthentication().getDetails();
		
		initiator.setEmail(map.get("email"));
		
		return initiator;
	}

}
