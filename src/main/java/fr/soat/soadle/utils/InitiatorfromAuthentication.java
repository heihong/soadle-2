package fr.soat.soadle.utils;

import org.springframework.security.core.Authentication;

import fr.soat.soadle.model.Participant;

public class InitiatorfromAuthentication {
	
	
	
	public static Participant from(Authentication authentication)
	{
        Participant initiator = new Participant();		
		initiator.setName(authentication.getName());		
		
		return initiator;
	}

}
