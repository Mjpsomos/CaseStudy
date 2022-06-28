package tesksystems.psomos_michael_casestudy.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@ControllerAdvice
public class ErrorController {

    @RequestMapping(value = "/error/404")
    // Returns 404 page if no URL found
    public String error404(HttpServletRequest request) {

        String originalUrl = (String) request.getAttribute("javax.servlet.forward.request_uri");
        log.error("Requested URL not found : " + request.getMethod() + " " + originalUrl);

        return "error/404";
    }


    @ExceptionHandler(Exception.class)
    // Handles 500 page errors caused by stacktrace errors
    public ModelAndView handleAllExceptions(HttpServletRequest request, Exception ex) {

        log.error("Error page exception : " + getRequestURL(request), ex);

        ModelAndView model = new ModelAndView();
        model.setViewName("error/500");

        String stackTrace = getHTMLStackTrace(ExceptionUtils.getStackFrames(ex));


        model.addObject("requestUrl", getRequestURL(request));
        model.addObject("message", ex.getMessage());
        model.addObject("stackTrace", stackTrace);

        if (ex.getCause() != null) {
            Throwable root = ExceptionUtils.getRootCause(ex);
            model.addObject("rootcause", root);

            String roottrace = getHTMLStackTrace(ExceptionUtils.getStackFrames(ex));
            model.addObject("roottrace", roottrace);
        }

        return model;
    }

    //retrieves the stack trace error
    private String getHTMLStackTrace(String[] stack) {
        StringBuffer result = new StringBuffer();

        for (String frame : stack) {
            if (frame.contains("tesksystems")) {
                result.append(" &nbsp; &nbsp; &nbsp;" + frame.trim().substring(3) + "<br>\n");
            } else if (frame.contains("Caused by:")) {
                result.append("Caused By: " + frame + "<br>");
            }
        }

        return result.toString();
    }

    //Gets the error URL
    private String getRequestURL(HttpServletRequest request) {
        String result = request.getRequestURL().toString();
        if (request.getQueryString() != null) {
            result = result + "?" + request.getQueryString();
        }

        return result;
    }

}