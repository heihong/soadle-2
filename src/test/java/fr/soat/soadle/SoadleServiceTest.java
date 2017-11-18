package fr.soat.soadle;

import fr.soat.soadle.doodle.services.DoodleService;
import fr.soat.soadle.model.Meeting;
import fr.soat.soadle.services.SoadleService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by adam on 17/11/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SoadleApplication.class)
public class SoadleServiceTest {

    @Autowired
    SoadleService soadleService;

    @MockBean
    DoodleService doodleService;


    @Test
    public void shouldSaveDoodlePoll(){
        mockDoodleService();
        Meeting savedMeeting = soadleService.addDoodleMeeting("doodleReferenceForTest");
                Assert.assertNotNull(savedMeeting.getId());
                Assert.assertNotNull(savedMeeting.getImportationDate());
            }

    private void mockDoodleService() {
Meeting meeting = new Meeting();
meeting.setId("testId1");
        Mockito.when(doodleService.findDoodle("doodleReferenceForTest"))
                .thenReturn(meeting);
    }

}
