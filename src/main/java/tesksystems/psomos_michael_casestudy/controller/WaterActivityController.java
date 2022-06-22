package tesksystems.psomos_michael_casestudy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import tesksystems.psomos_michael_casestudy.database.dao.UserDao;
import tesksystems.psomos_michael_casestudy.database.dao.WaterActivityDao;
import tesksystems.psomos_michael_casestudy.formbean.AddWaterActivityFormBean;

@Slf4j
@Controller
public class WaterActivityController {

    @Autowired
    private WaterActivityDao waterActivityDao;

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/wateractivity/add", method = RequestMethod.GET)
    public ModelAndView addChild() {
        ModelAndView response = new ModelAndView();
        response.setViewName("wateractivity/add");

        AddWaterActivityFormBean waterActivityForm = new AddWaterActivityFormBean();
        response.addObject("waterActivityForm", waterActivityForm);

        return response;
    }
}
