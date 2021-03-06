package tesksystems.psomos_michael_casestudy.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tesksystems.psomos_michael_casestudy.database.dao.UserDao;
import tesksystems.psomos_michael_casestudy.database.dao.UserRoleDao;
import tesksystems.psomos_michael_casestudy.database.entity.User;
import tesksystems.psomos_michael_casestudy.database.entity.UserRole;
import tesksystems.psomos_michael_casestudy.formbean.RegisterFormBean;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Showing the registration form
    @RequestMapping(value = "/login/register" , method = RequestMethod.GET)
    public ModelAndView register() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("/login/register");

        RegisterFormBean form = new RegisterFormBean();
        response.addObject("form" , form);

        return response;
    }
    // Filled out the form
    @RequestMapping(value = "/login/registerSubmit", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView registerSubmit(@Valid RegisterFormBean form, BindingResult bindingResult) throws Exception{
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

            response.setViewName("login/register");
            return response;
        }

        User user = userDao.findById(form.getId());

        if (user == null){
            user = new User();

            log.info("form Information" + form);
            user.setEmail(form.getEmail().toLowerCase().trim());
            user.setFirstName(form.getFirstName().toLowerCase().trim());
            user.setLastName(form.getLastName().toLowerCase().trim());
            user.setTownState(form.getTownState().toLowerCase().trim());
            user.setProfileDescription(form.getProfileDescription().toLowerCase().trim());

            user.setFavoriteMeetups(form.getFavoriteMeetUps().toLowerCase().trim());

            if (form.getProfileImg() == ""){
                user.setProfileImg("https://images.pexels.com/photos/11504427/pexels-photo-11504427.jpeg?auto=compress&cs=tinysrgb&w=600");
            } else
                user.setProfileImg(form.getProfileImg().trim());
        }

        String password = passwordEncoder.encode((form.getPassword()));
        user.setPassword(password);

        userDao.save(user);

        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setUserRole("USER");

        userRoleDao.save(userRole);


        log.info("Saved: " + user);
        log.info("User Role: " + userRole.getUserRole());


        response.setViewName(("redirect:/login/login"));
        return response;
    }
    @GetMapping(value = "/user/profile")
    public ModelAndView profile(@RequestParam(name = "searchId", required = false, defaultValue = "") String searchFirstName) {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/profile");

        //identifies the auth user,
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();

        User user = userDao.findByEmail(username);

        log.info("Home Profile of: " + user.getId() + " " + user.getFirstName() + " " + user.getLastName());

        // information is displayed on user profile page which is pulled from formbean
        RegisterFormBean form = new RegisterFormBean();

        form.setId(user.getId());
        form.setEmail(user.getEmail());
        form.setFirstName(user.getFirstName());
        form.setLastName(user.getLastName());
        form.setTownState(user.getTownState());
        form.setProfileDescription(user.getProfileDescription());
        form.setFavoriteMeetUps(user.getFavoriteMeetups());

        form.setProfileImg(user.getProfileImg());

        response.addObject("form", form);


        return response;

    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping(value = "/login/edit/{userId}")
    public ModelAndView editUser(@PathVariable("userId") Integer userId) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("login/register");

        User user = userDao.findById(userId);

        RegisterFormBean form = new RegisterFormBean();

        form.setId(user.getId());
        form.setEmail(user.getEmail());
        form.setFirstName(user.getFirstName());
        form.setLastName(user.getLastName());
        form.setTownState(user.getTownState());
        form.setProfileDescription(user.getProfileDescription());
        form.setFavoriteMeetUps(user.getFavoriteMeetups());

        response.addObject("form", form);

        log.info("User Information before update: " + form);

        return response;
    }
    // Viewing someone elses profile
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping(value = "/user/profile/{userId}")
    public ModelAndView viewTargetUserProfile(@PathVariable("userId") Integer userId) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("/user/targetprofile");

        User user = userDao.findById(userId);

        RegisterFormBean form = new RegisterFormBean();

        form.setId(user.getId());
        form.setEmail(user.getEmail());
        form.setFirstName(user.getFirstName());
        form.setLastName(user.getLastName());
        form.setTownState(user.getTownState());
        form.setProfileDescription(user.getProfileDescription());
        form.setFavoriteMeetUps(user.getFavoriteMeetups());

        form.setProfileImg(user.getProfileImg());

        response.addObject("form", form);

        log.info("Currently Viewing Profile of" + " " + user.getId() + " " + user.getFirstName() + " " + user.getLastName());

        return response;

    }
}




