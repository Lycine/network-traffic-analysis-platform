package org.jozif.controller;



import org.jozif.entity.User;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;

@CommonsLog
@Controller
public class EmergencyResponseController {

    @Secured("")
    @RequestMapping(value = "/emergency-response-center-home")
    public String home(User user, Model model, HttpSession session, HttpServletRequest request) {
        user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "emergency-response-center-home";
    }
}
