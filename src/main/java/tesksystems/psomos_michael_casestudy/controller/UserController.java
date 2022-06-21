package tesksystems.psomos_michael_casestudy.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/login/register" , method = RequestMethod.GET)
    public ModelAndView register() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("/login/register");

        RegisterFormBean form = new RegisterFormBean();
        response.addObject("form" , form);

        return response;
    }
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
}

