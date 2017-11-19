package fr.soat.soadle.services.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import fr.soat.soadle.SoadleApplication;
import fr.soat.soadle.doodle.services.DoodleService;
import fr.soat.soadle.model.Meeting;
import fr.soat.soadle.services.DoodleImportService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SoadleApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class DoodleImportServiceTest {

		@Autowired
		DoodleImportService doodleImportService;

		@MockBean
		DoodleService doodleService;

		@Test
		public void shouldSaveDoodlePoll() {
			mockDoodleService();
			Meeting savedMeeting = doodleImportService.addDoodleMeeting("doodleReferenceForTest");
			Assert.assertNotNull(savedMeeting.getId());
			Assert.assertNotNull(savedMeeting.getImportationDate());
		}

		private void mockDoodleService() {
			Meeting meeting = new Meeting();
			meeting.setId("testId1");
			Mockito.when(doodleService.findDoodle("doodleReferenceForTest")).thenReturn(meeting);
		}

	}