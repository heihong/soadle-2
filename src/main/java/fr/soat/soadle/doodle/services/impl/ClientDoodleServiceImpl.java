package fr.soat.soadle.doodle.services.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;








import fr.soat.soadle.doodle.dto.DoodleDto;
import fr.soat.soadle.doodle.dto.DoodleParticipantDto;
import fr.soat.soadle.doodle.services.ClientDoodleService;
import fr.soat.soadle.doodle.utils.DoodleTransformer;
import fr.soat.soadle.model.Meeting;
import fr.soat.soadle.model.Participant;
import static fr.soat.soadle.utils.ObjectToGeson.toGeson;

/**
 * @author hakim
 *
 */
@Service
public class ClientDoodleServiceImpl implements ClientDoodleService {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ClientDoodleService.class);
	 
	/**
	 * doodle url web service
	 */
	@Value("${polls.doodle.url}")
	private String pollsDoodleUrl;
	
	/**
	 * 
	 */
	@Autowired
	private RestTemplate restTemplate;

	
	/**
	 * @see fr.soat.soadle.doodle.services.ClientDoodleService#findDoodle(java.lang.String)
	 */
	@Override
	public Meeting findDoodle(String id) {

		ResponseEntity<DoodleDto> response = restTemplate.getForEntity(pollsDoodleUrl + id, DoodleDto.class);

		return DoodleTransformer.from(response.getBody());

	}

	
	/**
	 * @see fr.soat.soadle.doodle.services.ClientDoodleService#createDoodle(fr.soat.soadle.model.Meeting)
	 */
	@Override
	public Meeting createDoodle(Meeting meeting) {
		
		
		DoodleDto doodleDto = DoodleTransformer.to(meeting);
		
	    LOGGER.debug(toGeson(doodleDto));
	     
	    HttpEntity<DoodleDto> request = new HttpEntity<>(doodleDto);
		
		ResponseEntity<DoodleDto> response = restTemplate.exchange(pollsDoodleUrl , HttpMethod.POST , request, DoodleDto.class);

		return DoodleTransformer.from(response.getBody());
	}


	@Override
	public Participant participe(String mettnigId, Participant pParticipant) {

		DoodleParticipantDto participantDto = DoodleTransformer.to(pParticipant);
		
	    LOGGER.debug(toGeson(participantDto));
	    
	    
	    HttpEntity<DoodleParticipantDto> request = new HttpEntity<>(participantDto);
	    
	    ResponseEntity<DoodleParticipantDto> response = null;
		
	    if(StringUtils.isBlank(participantDto.getId()))
	    {
	      response = restTemplate.exchange(pollsDoodleUrl+mettnigId+"/participants" , HttpMethod.POST , request, DoodleParticipantDto.class);
	 	   
	    } else
	    {
	      response = restTemplate.exchange(pollsDoodleUrl+mettnigId+"/participants/"+participantDto.getId() , HttpMethod.PUT , request, DoodleParticipantDto.class);
	    }
	    
		LOGGER.debug(toGeson(response.getBody()));
				 
		return DoodleTransformer.from(response.getBody());
	}
	
}
