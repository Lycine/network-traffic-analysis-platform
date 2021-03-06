package org.jozif.security;

import org.jozif.dao.UserDao;
import org.jozif.entity.User;
import org.jozif.service.UserService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static org.jozif.util.Global.SIGNIN_FAILURE_USERSTOP_CODE;
import static org.jozif.util.Global.SIGNIN_FAILURE_USERNOTFOUND_CODE;


/**
 * Created by hongyu on 2017/4/7.
 */
@CommonsLog
@Service("MyUserDetailsImpl")
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    /**
     * 重写的加载用户方法，可以通过手机号，邮箱
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("MyAuthPro传过来的username: " + username);
        User user = new User();
        user.setPhoneNumber(username);
        user.setEmail(username);
        user = userService.loadUserByUsername(user);
        if (null == user) {
            log.debug("未找到用户code:" + SIGNIN_FAILURE_USERNOTFOUND_CODE + ".id:0");
            throw new UsernameNotFoundException("用户已停用code:" + SIGNIN_FAILURE_USERSTOP_CODE + ".id:0");
        }
        return new MyUserDetails(user);
    }
}
