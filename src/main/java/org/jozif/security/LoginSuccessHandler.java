package org.jozif.security;

import org.jozif.entity.User;
import org.jozif.service.UserService;
import org.jozif.util.GetRealIp;
import lombok.extern.apachecommons.CommonsLog;
import org.jozif.util.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by hongyu on 2017/4/10.
 */
@CommonsLog
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();
        String ip = GetRealIp.getIpAddr(httpServletRequest);
        Date date = new Date();
        user.setLastTime(date);
        user.setLoginIp(ip);
        user.setLoginFailureCount(0);
        log.debug(user.toString());
        httpServletRequest.getSession().setAttribute("user", user);
        httpServletResponse.sendRedirect(Global.SIGNIN_SUCCESS_CONTROLLER);
    }
}
