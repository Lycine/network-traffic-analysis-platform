package org.jozif.controller;

import org.jozif.entity.Msg;
import org.jozif.entity.Token;
import org.jozif.entity.User;
import org.jozif.service.TokenService;
import org.jozif.service.UserService;
import org.jozif.util.RandomStringGenerator;
import org.jozif.util.Validator;
import org.jozif.util.encryptAndDecode.CryptoUtils;
import org.jozif.util.mail.SendEmail;
import com.sun.deploy.net.HttpResponse;
import lombok.extern.apachecommons.CommonsLog;
import org.jozif.util.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@CommonsLog
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Secured("")
    @RequestMapping(value = "/emergency-response-center-home")
    public String home(User user, Model model, HttpSession session, HttpServletRequest request) {
        user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "emergency-response-center-home";
    }

    //step one
    @RequestMapping(value = "/admin/getInfoByEmail")
    public String getInfoByEmail(String email, Model model, HttpSession session, HttpServletRequest request) {
        log.debug("email: " + email);
        System.out.println("email: " + email);
        if (null == email || "".equals(email) || !Validator.isEmail(email)){
            Msg msg = new Msg("failure", "修改用户步骤1失败，邮箱格式问题。");
            model.addAttribute("msg", msg);
            log.debug("修改用户步骤1失败，邮箱格式问题。");
            return "admin_index";
        }
        User user = new User();
        user.setEmail(email);
        user = userService.userFindByEmail(user);
        if (null == user) {
            Msg msg = new Msg("failure", "修改用户步骤1失败，未找到对应的邮箱。");
            model.addAttribute("msg", msg);
            log.debug("修改用户步骤1失败，未找到对应的邮箱。");
            return "admin_index";
        }
        model.addAttribute("targetUser", user);
        System.out.println("targetUser: " + user.toString());
        user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "admin_index";
    }

    @RequestMapping(value = "/admin/updateUser", method = RequestMethod.POST)
    public String adminUpdateUser(String email,String role,String isStop,User user, Model model, HttpSession session, HttpServletRequest request, HttpResponse response) {
        Date date = new Date();
        log.info(user.toString());
        if (null == user.getEmail() || "".equals(user.getEmail())) {
            Msg msg = new Msg("failure", "更新基本信息失败，邮箱为空。");
            model.addAttribute("msg", msg);
            log.debug("更新基本信息失败，邮箱为空。");
            return "admin_index";
        } else if (null == user.getRole() || "".equals(user.getRole())) {
            Msg msg = new Msg("failure", "更新基本信息失败，角色为空。");
            model.addAttribute("msg", msg);
            log.debug("更新基本信息失败，角色为空。");
            return "admin_index";
        }
        User newUser = userService.userFindByEmail(user);
        System.out.println("newUser: " + newUser);
        newUser.setRole(user.getRole());
        newUser.setIsStop(user.getIsStop() );
        System.out.println("new newUser: " + newUser);
        user = newUser;
        if (!userService.userUpdateById(user)) {
            Msg msg = new Msg("failure", "更新基本信息失败，未知错误。");
            model.addAttribute("msg", msg);
            log.debug("更新基本信息失败，未知错误。");
            return "admin_index";
        } else {
            Msg msg = new Msg("success", "更新用户信息成功。");
            model.addAttribute("msg", msg);
            session.setAttribute("user", userService.userFindById(user));
            log.debug("更新用户信息成功。");
            return "admin_index";
        }
    }

    @RequestMapping(value = "/admin/registerUser", method = RequestMethod.POST)
    public String registerNewStudent(HttpSession session,User user, Model model) {
        log.debug(user.toString());
        User sessionUser = (User) session.getAttribute("user");
        try {
            if (null == user.getEmail() || "".equals(user.getEmail().trim())) {
                log.debug("邮箱为空");
                Msg msg = new Msg("failure", "新建用户失败，邮箱为空。");
                model.addAttribute("msg", msg);
                if ("ROLE_ADVANCED".equals(sessionUser.getRole())){
                    return "advanced_index";
                } else {
                    return "admin_index";
                }
            }
            String email = user.getEmail().trim();
            if (!Validator.isEmail(email)) {
                log.debug("邮箱格式不正确: " + email);
                Msg msg = new Msg("failure", "新建用户失败，邮箱格式不正确。");
                model.addAttribute("msg", msg);
                if ("ROLE_ADVANCED".equals(sessionUser.getRole())){
                    return "advanced_index";
                } else {
                    return "admin_index";
                }
            }
            if (userService.isDuplicateEmail(user)) {
                log.debug("已注册过的邮箱");
                Msg msg = new Msg("failure", "新建用户失败，已注册过的邮箱。");
                model.addAttribute("msg", msg);
                if ("ROLE_ADVANCED".equals(sessionUser.getRole())){
                    return "advanced_index";
                } else {
                    return "admin_index";
                }
            }

            if (null == user.getRole() || "".equals(user.getRole().trim())) {
                log.debug("角色为空");
                Msg msg = new Msg("failure", "新建用户失败，角色为空。");
                model.addAttribute("msg", msg);
                if ("ROLE_ADVANCED".equals(sessionUser.getRole())){
                    return "advanced_index";
                } else {
                    return "admin_index";
                }
            } else {
                log.debug("新建用户");
                user.setRole(user.getRole());
                user.setNickName(user.getEmail());
                user.setEmail(user.getEmail());
                String salt = CryptoUtils.getSalt();//盐值
//              随机密码
                String randomString = RandomStringGenerator.getRandomString(20);
                String password = randomString.toUpperCase();
                String hashPassword = CryptoUtils.getHash(password, salt);//加密的密码
                user.setSalt(salt);
                user.setPassword(hashPassword);
                if (userService.userAdd(user)) {
                    log.debug("新建用户成功");
                } else {
                    log.debug("新建用户失败，dao层错误");
                    Msg msg = new Msg("failure", "新建用户失败，未知错误。");
                    model.addAttribute("msg", msg);
                    if ("ROLE_ADVANCED".equals(sessionUser.getRole())){
                        return "advanced_index";
                    } else {
                        return "admin_index";
                    }
                }
                Token token = new Token();
                token.setToken(RandomStringGenerator.getRandomString(50));
                Date dt = new Date();
                long nowSec = dt.getTime() / 1000;
                Date expireTime = new Date((nowSec + 36000) * 1000);
                Date addTime = new Date(nowSec * 1000);
                token.setExpireTime(expireTime);
                token.setAddTime(addTime);
                token.setMaster("ACTIVE_ACCOUNT");
                user = userService.userFindByEmail(user);
                token.setRemark(String.valueOf(user.getId()));
                if (tokenService.isDuplicateToken(token)) {
                    log.debug("新建用户失败，token重复");
                    Msg msg = new Msg("failure", "新建用户失败，未知错误。");
                    model.addAttribute("msg", msg);
                    if ("ROLE_ADVANCED".equals(sessionUser.getRole())){
                        return "advanced_index";
                    } else {
                        return "admin_index";
                    }
                } else {
                    tokenService.tokenAdd(token);
                    SendEmail sendEmail = new SendEmail();
                    String link = "http://" + Global.HOSTNAME_AND_PORT + "/pass/signIn/active/" + user.getId() + "_" + token.getToken();
                    if (sendEmail.sendMailActiveAccount(email, link, password)) {
                        log.debug("邮件发送成功");
                        Msg msg = new Msg("success", "新建用户成功，请让用户按照邮件激活账户。");
                        model.addAttribute("msg", msg);
                        if ("ROLE_ADVANCED".equals(sessionUser.getRole())){
                            return "advanced_index";
                        } else {
                            return "admin_index";
                        }
                    } else {
                        log.debug("邮件发送失败");
                        Msg msg = new Msg("failure", "新建用户失败，未知错误。");
                        model.addAttribute("msg", msg);
                        if ("ROLE_ADVANCED".equals(sessionUser.getRole())){
                            return "advanced_index";
                        } else {
                            return "admin_index";
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.debug("新建用户失败，未知错误");
            e.printStackTrace();
            return "redirect:" + Global.REGISTER_PAGE_UNKNOWN;
        }

    }

    @RequestMapping(value = "/admin/updateBasic", method = RequestMethod.POST)
    public String adminUpdateBasic(User user, Model model, HttpSession session, HttpServletRequest request) {
        Date date = new Date();
        user.setUpdateTime(date);
        User oldUser = userService.userFindById(user);
        log.info(user.toString());
        if (null == user.getEmail() && "".equals(user.getEmail())) {
            Msg msg = new Msg("failure", "更新基本信息失败，邮箱为空。");
            model.addAttribute("msg", msg);
            log.debug("更新基本信息失败，邮箱为空。");
            return "admin_index";
        } else if (null == user.getPhoneNumber() && "".equals(user.getPhoneNumber())) {
            Msg msg = new Msg("failure", "更新基本信息失败，手机号为空。");
            model.addAttribute("msg", msg);
            log.debug("更新基本信息失败，手机号为空。");
            return "admin_index";
        } else if (null == user.getNickName() && "".equals(user.getNickName())) {
            Msg msg = new Msg("failure", "更新基本信息失败，昵称为空。");
            model.addAttribute("msg", msg);
            log.debug("更新基本信息失败，昵称为空。");
            return "admin_index";
        } else if (!Validator.isEmail(user.getEmail())) {
            Msg msg = new Msg("failure", "更新基本信息失败，邮箱格式不正确。");
            model.addAttribute("msg", msg);
            log.debug("更新基本信息失败，邮箱格式不正确。");
            return "admin_index";
        } else if (!Validator.isMobile(user.getPhoneNumber())) {
            Msg msg = new Msg("failure", "更新基本信息失败，手机号格式不正确。");
            model.addAttribute("msg", msg);
            log.debug("更新基本信息失败，手机号格式不正确。");
            return "admin_index";
        } else if (userService.isDuplicatePhoneNumber(user) && !user.getPhoneNumber().equals(oldUser.getPhoneNumber())) {
            Msg msg = new Msg("failure", "更新基本信息失败，手机号已存在。");
            model.addAttribute("msg", msg);
            log.debug("更新基本信息失败，手机号已存在。");
            return "admin_index";
        } else if (userService.isDuplicateEmail(user) && !user.getEmail().equals(oldUser.getEmail())) {
            Msg msg = new Msg("failure", "更新基本信息失败，邮箱已存在。");
            model.addAttribute("msg", msg);
            log.debug("更新基本信息失败，邮箱已存在。");
            return "admin_index";
        } else if (userService.isDuplicateNickName(user) && !user.getNickName().equals(oldUser.getNickName())) {
            Msg msg = new Msg("failure", "更新基本信息失败，昵称已存在。");
            model.addAttribute("msg", msg);
            log.debug("更新基本信息失败，昵称已存在。");
            return "admin_index";
        } else if (!userService.userUpdateById(user)) {
            Msg msg = new Msg("failure", "更新基本信息失败，未知错误。");
            model.addAttribute("msg", msg);
            log.debug("更新基本信息失败，未知错误。");
            return "admin_index";
        } else {
            Msg msg = new Msg("success", "更新基本信息成功。");
            model.addAttribute("msg", msg);
            session.setAttribute("user", userService.userFindById(user));
            log.debug("更新基本信息成功。");
            return "admin_index";
        }
    }


    @RequestMapping(value = "/student/updateBasic", method = RequestMethod.POST)
    public String studentUpdateBasic(User user, Model model, HttpSession session, HttpServletRequest request, HttpResponse response) {
        Date date = new Date();
        user.setUpdateTime(date);
        User oldUser = userService.userFindById(user);
        log.info(user.toString());
        if (null == user.getEmail() && "".equals(user.getEmail())) {
            Msg msg = new Msg("failure", "更新基本信息失败，邮箱为空。");
            model.addAttribute("msg", msg);
            log.debug("更新基本信息失败，邮箱为空。");
            return "stu/stu_index";
        } else if (null == user.getPhoneNumber() && "".equals(user.getPhoneNumber())) {
            Msg msg = new Msg("failure", "更新基本信息失败，手机号为空。");
            model.addAttribute("msg", msg);
            log.debug("更新基本信息失败，手机号为空。");
            return "stu/stu_index";
        } else if (null == user.getNickName() && "".equals(user.getNickName())) {
            Msg msg = new Msg("failure", "更新基本信息失败，姓名为空。");
            model.addAttribute("msg", msg);
            log.debug("更新基本信息失败，姓名为空。");
            return "stu/stu_index";
        } else if (!Validator.isEmail(user.getEmail())) {
            Msg msg = new Msg("failure", "更新基本信息失败，邮箱格式不正确。");
            model.addAttribute("msg", msg);
            log.debug("更新基本信息失败，邮箱格式不正确。");
            return "stu/stu_index";
        } else if (!Validator.isMobile(user.getPhoneNumber())) {
            Msg msg = new Msg("failure", "更新基本信息失败，手机号格式不正确。");
            model.addAttribute("msg", msg);
            log.debug("更新基本信息失败，手机号格式不正确。");
            return "stu/stu_index";
        } else if (userService.isDuplicatePhoneNumber(user) && !user.getPhoneNumber().equals(oldUser.getPhoneNumber())) {
            Msg msg = new Msg("failure", "更新基本信息失败，手机号已存在。");
            model.addAttribute("msg", msg);
            log.debug("更新基本信息失败，手机号已存在。");
            return "stu/stu_index";
        } else if (userService.isDuplicateEmail(user) && !user.getEmail().equals(oldUser.getEmail())) {
            Msg msg = new Msg("failure", "更新基本信息失败，邮箱已存在。");
            model.addAttribute("msg", msg);
            log.debug("更新基本信息失败，邮箱已存在。");
            return "stu/stu_index";
        } else if (userService.isDuplicateNickName(user) && !user.getNickName().equals(oldUser.getNickName())) {
            Msg msg = new Msg("failure", "更新基本信息失败，昵称已存在。");
            model.addAttribute("msg", msg);
            log.debug("更新基本信息失败，昵称已存在。");
            return "stu/stu_index";
        } else if (!userService.userUpdateById(user)) {
            Msg msg = new Msg("failure", "更新基本信息失败，未知错误。");
            model.addAttribute("msg", msg);
            log.debug("更新基本信息失败，未知错误。");
            return "stu/stu_index";
        } else {
            Msg msg = new Msg("success", "更新基本信息成功。");
            model.addAttribute("msg", msg);
            session.setAttribute("user", userService.userFindById(user));
            log.debug("更新基本信息成功。");
            return "stu/stu_index";
        }
    }

    @RequestMapping(value = "/admin/updatePassword", method = RequestMethod.POST)
    public String adminUpdatePassword(User user, Model model, HttpSession session, HttpServletRequest request) {
        Date date = new Date();
        user.setUpdateTime(date);
        User oldUser = userService.userFindById(user);
        String oldPassword = request.getParameter("oldPassword");


        log.info(user.toString());
        if (null == user.getPassword() && "".equals(user.getPassword())) {
            Msg msg = new Msg("failure", "更新密码失败，密码为空。");
            model.addAttribute("msg", msg);
            log.debug("更新密码失败，密码为空。");
            return "admin_index";
        } else if (null == oldPassword && "".equals(oldPassword)) {
            Msg msg = new Msg("failure", "更新密码失败，旧密码为空。");
            model.addAttribute("msg", msg);
            log.debug("更新密码失败，旧密码为空。");
            return "admin_index";
        }
        //验证密码
        String hashPassword = oldUser.getPassword();
        String salt = oldUser.getSalt();
        log.debug("hashPassword: " + hashPassword);
        log.debug("password: " + oldPassword);
        log.debug("salt: " + salt);
        if (!CryptoUtils.verify(hashPassword, oldPassword, salt)) {
            Msg msg = new Msg("failure", "更新密码失败，旧密码验证未通过。");
            model.addAttribute("msg", msg);
            log.debug("更新密码失败，旧密码验证未通过。");
            return "admin_index";
        }
        //修改密码
        salt = CryptoUtils.getSalt();//盐值
        String password = user.getPassword();//传过来的明文密码
        hashPassword = CryptoUtils.getHash(password, salt);//加密的密码
        user.setSalt(salt);
        user.setPassword(hashPassword);
        if (!userService.userUpdateById(user)) {
            Msg msg = new Msg("failure", "更新密码失败，未知错误。");
            model.addAttribute("msg", msg);
            log.debug("更新密码失败，未知错误。");
            return "admin_index";
        } else {
            Msg msg = new Msg("success", "更新密码成功。");
            model.addAttribute("msg", msg);
            session.setAttribute("user", userService.userFindById(user));
            log.debug("更新密码成功。");
            return "admin_index";
        }
    }

    @RequestMapping(value = "/student/updatePassword", method = RequestMethod.POST)
    public String studentUpdatePassword(User user, Model model, HttpSession session, HttpServletRequest request) {
        Date date = new Date();
        user.setUpdateTime(date);
        User oldUser = userService.userFindById(user);
        String oldPassword = request.getParameter("oldPassword");


        log.info(user.toString());
        if (null == user.getPassword() && "".equals(user.getPassword())) {
            Msg msg = new Msg("failure", "更新密码失败，密码为空。");
            model.addAttribute("msg", msg);
            log.debug("更新密码失败，密码为空。");
            return "stu/stu_index";
        } else if (null == oldPassword && "".equals(oldPassword)) {
            Msg msg = new Msg("failure", "更新密码失败，旧密码为空。");
            model.addAttribute("msg", msg);
            log.debug("更新密码失败，旧密码为空。");
            return "stu/stu_index";
        }
        //验证密码
        String hashPassword = oldUser.getPassword();
        String salt = oldUser.getSalt();
        log.debug("hashPassword: " + hashPassword);
        log.debug("password: " + oldPassword);
        log.debug("salt: " + salt);
        if (!CryptoUtils.verify(hashPassword, oldPassword, salt)) {
            Msg msg = new Msg("failure", "更新密码失败，旧密码验证未通过。");
            model.addAttribute("msg", msg);
            log.debug("更新密码失败，旧密码验证未通过。");
            return "stu/stu_index";
        }
        //修改密码
        salt = CryptoUtils.getSalt();//盐值
        String password = user.getPassword();//传过来的明文密码
        hashPassword = CryptoUtils.getHash(password, salt);//加密的密码
        user.setSalt(salt);
        user.setPassword(hashPassword);
        if (!userService.userUpdateById(user)) {
            Msg msg = new Msg("failure", "更新密码失败，未知错误。");
            model.addAttribute("msg", msg);
            log.debug("更新密码失败，未知错误。");
            return "stu/stu_index";
        } else {
            Msg msg = new Msg("success", "更新密码成功。");
            model.addAttribute("msg", msg);
            session.setAttribute("user", userService.userFindById(user));
            log.debug("更新密码成功。");
            return "stu/stu_index";
        }
    }

}
