package tesksystems.psomos_michael_casestudy.controller;

import lombok.extern.slf4j.Slf4j;
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
import tesksystems.psomos_michael_casestudy.database.dao.UserDao;
import tesksystems.psomos_michael_casestudy.database.dao.WaterActivityDao;
import tesksystems.psomos_michael_casestudy.database.entity.User;
import tesksystems.psomos_michael_casestudy.database.entity.WaterActivity;
import tesksystems.psomos_michael_casestudy.formbean.AddWaterActivityFormBean;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class WaterActivityController {

    @Autowired
    private WaterActivityDao waterActivityDao;

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/wateractivity/add", method = RequestMethod.GET)
    public ModelAndView addWaterActivity() {
        ModelAndView response = new ModelAndView();
        response.setViewName("wateractivity/add");

        AddWaterActivityFormBean waterActivityForm = new AddWaterActivityFormBean();
        response.addObject("waterActivityForm", waterActivityForm);

        return response;
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/wateractivity/addsubmit", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView addWaterActivitySubmit(@Valid AddWaterActivityFormBean waterActivityForm, BindingResult bindingResult) {
        ModelAndView response = new ModelAndView();

        if (bindingResult.hasErrors()) {
            List<String> errorMessages = new ArrayList<>();

            for (ObjectError error : bindingResult.getAllErrors()) {

                errorMessages.add(error.getDefaultMessage());
                log.info(((FieldError) error).getField() + " " + error.getDefaultMessage());
            }

            response.addObject("waterActivityForm", waterActivityForm);
            response.addObject("errorMessages", errorMessages);
            response.addObject("bindingResult", bindingResult);

            response.setViewName("wateractivity/add");

            return response;
        }

        WaterActivity waterActivity = new WaterActivity();

        waterActivity.setWaterActivity(waterActivityForm.getWaterActivity().trim());
        waterActivity.setDescription(waterActivityForm.getDescription().trim());

        if (waterActivityForm.getImage() == "") {
            waterActivity.setImage("https://images.pexels.com/photos/757133/pexels-photo-757133.jpeg?auto=compress&cs=tinysrgb&w=600");
        } else
            waterActivity.setImage(waterActivityForm.getImage().trim());

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        User user = userDao.findByEmail(username);

        waterActivity.setUserId(user.getId());
        waterActivityDao.save(waterActivity);

        log.info("Water Activity Added to Account: " + waterActivity);

        response.setViewName("redirect:/user/mywateractivities");
        return response;
    }

    @RequestMapping(value = "/user/mywateractivities", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView viewUserWaterActivities() {
        ModelAndView response = new ModelAndView();

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();

        User user = userDao.findByEmail(username);
        List<WaterActivity> waterActivities = waterActivityDao.findWaterActivitiesByUserId(user.getId());
        response.addObject("waterActivities", waterActivities);

        waterActivities.forEach((waterActivity) -> log.info("Water Activity: " + waterActivity.getWaterActivity() + " Description: " + waterActivity.getDescription()));

        return response;


    }

    @Transactional
    @RequestMapping(value = "/user/removewateractivity", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView removeWaterActivity(@RequestParam(name = "waterActivityId", required = false) Integer waterActivityId) throws Exception {
        ModelAndView response = new ModelAndView();

        log.info("Water Activity being removed - Water Activity ID: " + waterActivityId);
        waterActivityDao.deleteById(waterActivityId);

        response.setViewName("redirect:/user/mywateractivities");

        return response;

    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping(value = "/user/wateractivity/{userId}")
    public ModelAndView viewTargetUserWaterActivities(@PathVariable("userId") Integer userId) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/targetuserwateractivities");

        User user = userDao.findById(userId);


        List<WaterActivity> waterActivities = waterActivityDao.findWaterActivitiesByUserId(user.getId());
        response.addObject("waterActivities", waterActivities);

        waterActivities.forEach((waterActivity) -> log.info("Water Activity: " + waterActivity.getWaterActivity()));

        return response;

    }

}