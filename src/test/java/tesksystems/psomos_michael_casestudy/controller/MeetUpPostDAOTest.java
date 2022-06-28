package tesksystems.psomos_michael_casestudy.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tesksystems.psomos_michael_casestudy.database.dao.MeetUpPostDao;
import tesksystems.psomos_michael_casestudy.database.entity.MeetUpPost;

import java.util.Date;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MeetUpPostDAOTest {


    @Autowired
    private MeetUpPostDao meetUpPostDao;


    @Test
    @Order(1) //test passed
    public void createMeetUpPostTest(){

        MeetUpPost expected = new MeetUpPost();

        expected.setCreateAt((new Date()));
        expected.setMeetupMessage("Going surfing this Saturday");
        expected.setLocation("Danvers, MA");
        expected.setMeetupDate("2022-07-14");
        expected.setMeetupTime("07:49");
        expected.setUserId(1);

        meetUpPostDao.save(expected);

        Assertions.assertTrue(expected.getId()>0);

    }

    @Test
    @Order(2)
    public void getMeetUpPostTest(){ //test passed

        MeetUpPost expected = new MeetUpPost ();
        expected.setId(1);

        MeetUpPost actual = meetUpPostDao.findById(1);

        Assertions.assertEquals(expected.getId(), actual.getId());

    }

    @Test
    @Order(3)
    public void updateMeetUpPostTest(){ //test passed

        String expected = "Going to the lake";

        MeetUpPost actual = meetUpPostDao.findById(1);
        actual.setMeetupMessage("Going to the lake");

        meetUpPostDao.save(actual);

        Assertions.assertEquals(expected, actual.getMeetupMessage());

    }

    @Test
    @Order(4)       //test passed
    public void deleteChildTest(){

        MeetUpPost actual = new MeetUpPost ();

        actual.setCreateAt((new Date()));
        actual.setMeetupMessage("Going to lake");
        actual.setLocation("Danvers, MA");
        actual.setMeetupDate("2022-06-14");
        actual.setMeetupTime("07:49");
        actual.setUserId(1);

        meetUpPostDao.save(actual);

        meetUpPostDao.delete(actual);

        Assertions.assertNull(meetUpPostDao.findById(actual.getId()));

    }

}

