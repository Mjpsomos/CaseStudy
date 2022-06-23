package tesksystems.psomos_michael_casestudy.controller;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tesksystems.psomos_michael_casestudy.database.dao.MeetUpPostDao;
import tesksystems.psomos_michael_casestudy.database.dao.UserDao;
import tesksystems.psomos_michael_casestudy.database.dao.WaterActivityDao;
import tesksystems.psomos_michael_casestudy.database.entity.MeetUpPost;
import tesksystems.psomos_michael_casestudy.database.entity.User;
import tesksystems.psomos_michael_casestudy.database.entity.WaterActivity;
import tesksystems.psomos_michael_casestudy.formbean.MeetUpPostFormBean;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class MeetUpPostController {

    @Autowired
    private MeetUpPostDao meetUpPostDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private WaterActivityDao waterActivityDao;

    @RequestMapping(value = "/meetuppost/create", method = RequestMethod.GET)
    public ModelAndView meetUpPost() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("meetuppost/create");

        return response;
    }

    @RequestMapping(value = "/meetuppost/createpost", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView createMeetUpPost(@Valid MeetUpPostFormBean form, BindingResult bindingResult) throws Exception {
        ModelAndView response = new ModelAndView();


        if (bindingResult.hasErrors()) {

            List<String> errorMessages = new ArrayList<>();

            for (ObjectError error : bindingResult.getAllErrors()) {
                errorMessages.add(error.getDefaultMessage());
                log.info(((FieldError) error).getField() + " " + error.getDefaultMessage());
            }

            response.addObject("form", form);
            response.addObject("errorMessages", errorMessages);
            response.addObject("bindingResult", bindingResult);

            response.setViewName("meetuppost/create");

            return response;
        }

        MeetUpPost meetUpPost = meetUpPostDao.findById(form.getId());

        if (meetUpPost == null) {
            meetUpPost = new MeetUpPost();
        }

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();

        User user = userDao.findByEmail(username);

        meetUpPost.setId(form.getId());
        meetUpPost.setUserId(user.getId());
        meetUpPost.setMeetupMessage(form.getMeetupMessage().trim());
        meetUpPost.setLocation(form.getLocation().trim());

        //convert the date and time to a String
        Object date = form.getMeetupDate();
        String stringDate = date.toString();
        meetUpPost.setMeetupDate(stringDate);

        Object time = form.getMeetupTime();
        String stringTime = time.toString();
        meetUpPost.setMeetupTime(stringTime);

        meetUpPostDao.save(meetUpPost);

        log.info("New Meetup Post created: " + meetUpPost);

        response.setViewName("redirect:/meetuppost/userposts");

        return response;
    }

    @RequestMapping(value = "/meetuppost/userposts", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView displayUserMeetUpPosts(@Valid MeetUpPostFormBean meetUpPostForm, BindingResult bindingResult) throws Exception {

        ModelAndView response = new ModelAndView();

        response.setViewName("meetuppost/userposts");

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        User user = userDao.findByEmail(username);

        List<MeetUpPost> postList = meetUpPostDao.findMeetUpPostByUserIdOrderByMeetupDateDesc(user.getId());

        response.addObject("postList", postList);

        return response;
    }
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping(value = "/meetuppost/createpost/{meetUpPostId}")
    public ModelAndView editMeetUpPost(@PathVariable("meetUpPostId") Integer meetUpPostId) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("meetuppost/create");

        MeetUpPost meetupPost = meetUpPostDao.findById(meetUpPostId);
        log.info("Editing meetup post Id: " + meetUpPostId);
        MeetUpPostFormBean form = new MeetUpPostFormBean();

        form.setId(meetupPost.getId());
        form.setMeetupMessage(meetupPost.getMeetupMessage());
        form.setLocation(meetupPost.getLocation());
        form.setMeetupDate(meetupPost.getMeetupDate());
        form.setMeetupTime(meetupPost.getMeetupTime());

        response.addObject("form", form);

        return response;
    }
    @Transactional
    @RequestMapping(value = "/meetuppost/delete", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView removeMeetUpPost(@RequestParam(name = "id", required = false) Integer meetupPostId) throws Exception {
        ModelAndView response = new ModelAndView();

        log.info("Meetup Post being removed - ID: " + meetupPostId);
        meetUpPostDao.deleteById(meetupPostId);

        response.setViewName("redirect:/meetuppost/userposts");

        return response;

    }
    @GetMapping(value = "/meetuppost/search")
    public ModelAndView meetupPostSearch(@RequestParam(name = "searchId", required = false, defaultValue = "") String searchLocation) {
        ModelAndView response = new ModelAndView();
        response.setViewName("/meetuppost/search");
        log.info("User is searching for: " + searchLocation);


        if (!StringUtils.isBlank(searchLocation)) {
            List<MeetUpPost> meetUpPosts = meetUpPostDao.findMeetUpPostByLocationContainsOrderByMeetupDateDesc(searchLocation);
            response.addObject("meetUpPosts", meetUpPosts);

        } else {
            searchLocation = "Search for Location";
        }

        response.addObject("searchValue", searchLocation);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        User user = userDao.findByEmail(username);

        List<WaterActivity> userWaterActivity = waterActivityDao.findWaterActivitiesByUserId(user.getId());
        response.addObject("userWaterActivity", userWaterActivity);


        return response;
    }

}



