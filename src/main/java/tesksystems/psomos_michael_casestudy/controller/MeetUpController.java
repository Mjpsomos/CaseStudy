package tesksystems.psomos_michael_casestudy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import tesksystems.psomos_michael_casestudy.database.dao.MeetUpDao;
import tesksystems.psomos_michael_casestudy.database.dao.MeetUpPostDao;
import tesksystems.psomos_michael_casestudy.database.dao.WaterActivityDao;
import tesksystems.psomos_michael_casestudy.database.entity.MeetUp;

import java.util.List;

@Slf4j
@Controller
public class MeetUpController {

    @Autowired
    private MeetUpDao meetUpDao;

    @Autowired
    private WaterActivityDao waterActivityDao;

    @Autowired
    private MeetUpPostDao meetUpPostDao;

    //Interface where you register for a MeetUp Post event
    @RequestMapping(value = "/meetuppost/addwateractivityregister", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView meetupSearchRegister(@RequestParam(name = "waterActivity", required = false) Integer waterActivityId,
                                             @RequestParam(name = "meetuppost", required = false) Integer meetuppostId) throws Exception {
    //Sets up search interface
        ModelAndView response = new ModelAndView();
        response.setViewName("meetuppost/search");


        log.info("Water Activity Id " + waterActivityId);
        log.info("Meetup Post Id " + meetuppostId);

        MeetUp meetup = new MeetUp();

        meetup.setWaterActivity(waterActivityDao.findById(waterActivityId));
        meetup.setMeetUpPost(meetUpPostDao.findById(meetuppostId));
    //Will save register MeetUp by WaterActivity Register id and Meetup post ID
        meetUpDao.save(meetup);

            // Will log that the user register for meetup
        log.info("Water Activity ID: " + " " + waterActivityId + " " + "is registered for Post ID: " + " " + meetuppostId);

        List<MeetUp> peopleGoing = meetUpDao.findByMeetUpPostId(meetuppostId);
        Long bethere = peopleGoing.stream().count();

        // will log how many people are registered
        log.info("There will be: " + bethere + " people going!");
        response.addObject("bethere", bethere);

        response.setViewName("meetuppost/registered");

        return response;
    }
}
