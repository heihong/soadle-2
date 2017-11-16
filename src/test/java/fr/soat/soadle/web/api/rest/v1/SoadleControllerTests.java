package fr.soat.soadle.web.api.rest.v1;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;

import java.util.List;

import javax.transaction.Transactional;

import static org.hamcrest.CoreMatchers.is;

import fr.soat.soadle.SoadleApplication;
import fr.soat.soadle.model.Meeting;
import fr.soat.soadle.repositories.MeetingRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SoadleApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
public class SoadleControllerTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private MeetingRepository meetingRepository;

	@Before
	public void setup() {

		Meeting meeting = new Meeting();

		meeting.setId("test1");
		meeting.setTitle("test1 meeting");

		meetingRepository.save(meeting);

		Meeting meeting2 = new Meeting();

		meeting2.setId("test2");
		meeting2.setTitle("test2 meeting");

		meetingRepository.save(meeting2);

	}

	@Test
	public void soadleTest() throws Exception {
		
		Meeting meeting = meetingRepository.getOne("test1");

		mvc.perform(get("/api/v1/soadle/test1")
		   .contentType(MediaType.APPLICATION_JSON))
		   .andExpect(status().isOk())
		   .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		   .andExpect(jsonPath("$.id", is(meeting.getId())))
		   .andExpect(jsonPath("$.title", is(meeting.getTitle())));
	}
	
	
	@Test
	public void allTest() throws Exception {
		
		List<Meeting> meetings = meetingRepository.findAll();

		mvc.perform(get("/api/v1/soadle/")
		   .contentType(MediaType.APPLICATION_JSON))
		   .andExpect(status().isOk())
		   .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		   .andExpect(jsonPath("$", hasSize(meetings.size())))
		   .andExpect(jsonPath("$[0].id", is(meetings.get(0).getId())))
		   .andExpect(jsonPath("$[0].title", is(meetings.get(0).getTitle())))
		   .andExpect(jsonPath("$[1].id", is(meetings.get(1).getId())))
		   .andExpect(jsonPath("$[1].title", is(meetings.get(1).getTitle())));

	}

}
