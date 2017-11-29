package fr.soat.soadle.web.api.rest.v1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.hamcrest.Matchers.hasSize;

import java.util.Arrays;
import java.util.List;


import static org.hamcrest.CoreMatchers.is;

import fr.soat.soadle.SoadleApplication;
import fr.soat.soadle.model.Meeting;
import fr.soat.soadle.services.SoadleService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SoadleApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class SoadleControllerTests {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private SoadleService soadleService;

	@Test
	public void soadleTest() throws Exception {
		
			
		Meeting meeting = new Meeting();		
		meeting.setId("test1");
		meeting.setTitle("title test1");
		
		
		given(soadleService.find(Mockito.anyString())).willReturn(meeting);

		

		mvc.perform(get("/api/v1/soadle/test1")
		   .contentType(MediaType.APPLICATION_JSON))
		   .andExpect(status().isOk())
		   .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		   .andExpect(jsonPath("$.id", is(meeting.getId())))
		   .andExpect(jsonPath("$.title", is(meeting.getTitle())));
		
		
		verify(soadleService, VerificationModeFactory.times(1)).find("test1");
		
		 reset(soadleService);
	}
	
	
	@Test
	public void allTest() throws Exception {
				
		Meeting meeting1 = new Meeting();		
		meeting1.setId("test1");
		meeting1.setTitle("title test1");
		
		Meeting meeting2 = new Meeting();		
		meeting2.setId("test2");
		meeting2.setTitle("title test2");
		
		Meeting meeting3 = new Meeting();		
		meeting3.setId("test3");
		meeting3.setTitle("title test3");
		

		List<Meeting>  meetings = Arrays.asList(meeting1,meeting2,meeting3);
		
		given(soadleService.findAll()).willReturn(meetings);

		mvc.perform(get("/api/v1/soadle/")
		   .contentType(MediaType.APPLICATION_JSON))
		   .andExpect(status().isOk())
		   .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		   .andExpect(jsonPath("$", hasSize(meetings.size())))
		   .andExpect(jsonPath("$[0].id", is(meetings.get(0).getId())))
		   .andExpect(jsonPath("$[0].title", is(meetings.get(0).getTitle())))
		   .andExpect(jsonPath("$[1].id", is(meetings.get(1).getId())))
		   .andExpect(jsonPath("$[1].title", is(meetings.get(1).getTitle())));
		
		verify(soadleService, VerificationModeFactory.times(1)).findAll();
		
		 reset(soadleService);

	}

}
