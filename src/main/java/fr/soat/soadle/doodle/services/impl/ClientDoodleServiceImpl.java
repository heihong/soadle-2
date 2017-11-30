package fr.soat.soadle.doodle.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fr.soat.soadle.doodle.dto.DoodleDto;
import fr.soat.soadle.doodle.services.ClientDoodleService;

/**
 * @author hakim
 *
 */
@Service
public class ClientDoodleServiceImpl implements ClientDoodleService {

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
	 * 
	 * @see
	 * fr.soat.soadle.doodle.services.ClientDoodleService#findDoodle(java.lang.
	 * String)
	 */
	@Override
	public DoodleDto findDoodle(String id) {

		ResponseEntity<DoodleDto> response = restTemplate.getForEntity(pollsDoodleUrl + id, DoodleDto.class);

		return response.getBody();

	}

	/**
	 * @see fr.soat.soadle.doodle.services.ClientDoodleService#createDoodle(fr.soat.soadle.doodle.dto.DoodleDto)
	 */
	@Override
	public DoodleDto createDoodle(DoodleDto doodleDto) {
		
	     HttpEntity<DoodleDto> request = new HttpEntity<>(doodleDto);
		
		ResponseEntity<DoodleDto> response = restTemplate.exchange(pollsDoodleUrl , HttpMethod.POST , request, DoodleDto.class);

		return response.getBody();
	}

}
