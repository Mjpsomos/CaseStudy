package tesksystems.psomos_michael_casestudy.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import tesksystems.psomos_michael_casestudy.database.dao.MeetUpPostDao;
import tesksystems.psomos_michael_casestudy.database.dao.UserDao;
import tesksystems.psomos_michael_casestudy.database.entity.MeetUpPost;
import tesksystems.psomos_michael_casestudy.database.entity.User;
import tesksystems.psomos_michael_casestudy.formbean.MeetUpPostFormBean;

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
}
