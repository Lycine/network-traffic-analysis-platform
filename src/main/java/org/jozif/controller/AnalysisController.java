package org.jozif.controller;

import lombok.extern.apachecommons.CommonsLog;
import org.jozif.entity.Dashboard;
import org.jozif.entity.DashboardDto;
import org.jozif.entity.Msg;
import org.jozif.entity.User;
import org.jozif.service.DashboardService;
import org.jozif.service.TokenService;
import org.jozif.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@CommonsLog
@Controller
public class AnalysisController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private DashboardService dashboardService;

    @Secured("")
    @RequestMapping(value = "/analysis")
    public String analysisList(User user, Model model, HttpSession session, HttpServletRequest request) {
        user = (User) session.getAttribute("user");
        List<Dashboard> dashboardList = dashboardService.dashboardFindAll();
        List<DashboardDto> dashboardDtoList = new ArrayList<>();
        log.info("dashboardList: ");
        for (Dashboard i : dashboardList) {
            User tempUser = new User();
            tempUser.setId(i.getFrom());
            tempUser = userService.userFindById(tempUser);
            DashboardDto dashboardDto = new DashboardDto(i.getId(), i.getLink(), i.getTitle(), i.getContent(), tempUser.getNickName(), i.getAddTime(), i.getIsDel(), i.getNote());
            log.info(dashboardDto.toString());
            dashboardDtoList.add(dashboardDto);
        }
        model.addAttribute("dashboards", dashboardDtoList);
        model.addAttribute("user", user);
        return "analysis/analysis_index";
    }

    @Secured("")
    @RequestMapping(value = "/analysis/detail/{dashboardId}")
    public String home(@PathVariable int dashboardId, User user, Model model, HttpSession session, HttpServletRequest request) {
        Dashboard dashboard = new Dashboard();
        dashboard.setId(dashboardId);
        dashboard = dashboardService.dashboardFindById(dashboard);
        model.addAttribute("dashboard", dashboard);
        return "analysis/analysis_detail";
    }

    @Secured({"ROLE_ADVANCED", "ROLE_ADMIN"})
    @RequestMapping(value = "/analysis/add")
    public String add(Dashboard dashboard, Model model, HttpSession session, HttpServletRequest request) {
        dashboard.toString();
        if(null == dashboard.getLink() || "".equals(dashboard.getLink())){
            Msg msg = new Msg("failure", "添加仪表盘失败，链接为空");
            model.addAttribute("msg", msg);
            log.debug("添加仪表盘失败，链接为空");
            List<Dashboard> dashboardList = dashboardService.dashboardFindAll();
            List<DashboardDto> dashboardDtoList = new ArrayList<>();
            log.info("dashboardList: ");
            for (Dashboard i : dashboardList) {
                User tempUser = new User();
                tempUser.setId(i.getFrom());
                tempUser = userService.userFindById(tempUser);
                DashboardDto dashboardDto = new DashboardDto(i.getId(), i.getLink(), i.getTitle(), i.getContent(), tempUser.getNickName(), i.getAddTime(), i.getIsDel(), i.getNote());
                log.info(dashboardDto.toString());
                dashboardDtoList.add(dashboardDto);
            }
            model.addAttribute("dashboards", dashboardDtoList);
            return "analysis/analysis_index";
        } else if(null == dashboard.getTitle() || "".equals(dashboard.getTitle())){
            Msg msg = new Msg("failure", "添加仪表盘失败，标题为空");
            model.addAttribute("msg", msg);
            log.debug("添加仪表盘失败，标题为空");
            List<Dashboard> dashboardList = dashboardService.dashboardFindAll();
            List<DashboardDto> dashboardDtoList = new ArrayList<>();
            log.info("dashboardList: ");
            for (Dashboard i : dashboardList) {
                User tempUser = new User();
                tempUser.setId(i.getFrom());
                tempUser = userService.userFindById(tempUser);
                DashboardDto dashboardDto = new DashboardDto(i.getId(), i.getLink(), i.getTitle(), i.getContent(), tempUser.getNickName(), i.getAddTime(), i.getIsDel(), i.getNote());
                log.info(dashboardDto.toString());
                dashboardDtoList.add(dashboardDto);
            }
            model.addAttribute("dashboards", dashboardDtoList);
            return "analysis/analysis_index";
        } else if(null == dashboard.getContent() || "".equals(dashboard.getContent())){
            Msg msg = new Msg("failure", "添加仪表盘失败，内容为空");
            model.addAttribute("msg", msg);
            log.debug("添加仪表盘失败，内容为空");
            List<Dashboard> dashboardList = dashboardService.dashboardFindAll();
            List<DashboardDto> dashboardDtoList = new ArrayList<>();
            log.info("dashboardList: ");
            for (Dashboard i : dashboardList) {
                User tempUser = new User();
                tempUser.setId(i.getFrom());
                tempUser = userService.userFindById(tempUser);
                DashboardDto dashboardDto = new DashboardDto(i.getId(), i.getLink(), i.getTitle(), i.getContent(), tempUser.getNickName(), i.getAddTime(), i.getIsDel(), i.getNote());
                log.info(dashboardDto.toString());
                dashboardDtoList.add(dashboardDto);
            }
            model.addAttribute("dashboards", dashboardDtoList);
            return "analysis/analysis_index";
        } else if(0 == dashboard.getFrom()){
            Msg msg = new Msg("failure", "添加仪表盘失败，来源为空");
            model.addAttribute("msg", msg);
            log.debug("添加仪表盘失败，来源为空");
            List<Dashboard> dashboardList = dashboardService.dashboardFindAll();
            List<DashboardDto> dashboardDtoList = new ArrayList<>();
            log.info("dashboardList: ");
            for (Dashboard i : dashboardList) {
                User tempUser = new User();
                tempUser.setId(i.getFrom());
                tempUser = userService.userFindById(tempUser);
                DashboardDto dashboardDto = new DashboardDto(i.getId(), i.getLink(), i.getTitle(), i.getContent(), tempUser.getNickName(), i.getAddTime(), i.getIsDel(), i.getNote());
                log.info(dashboardDto.toString());
                dashboardDtoList.add(dashboardDto);
            }
            model.addAttribute("dashboards", dashboardDtoList);
            return "analysis/analysis_index";
        }

        if(dashboardService.dashboardAdd(dashboard)){
            Msg msg = new Msg("success", "添加仪表盘成功");
            model.addAttribute("msg", msg);
            log.debug("添加仪表盘成功");
            List<Dashboard> dashboardList = dashboardService.dashboardFindAll();
            List<DashboardDto> dashboardDtoList = new ArrayList<>();
            log.info("dashboardList: ");
            for (Dashboard i : dashboardList) {
                User tempUser = new User();
                tempUser.setId(i.getFrom());
                tempUser = userService.userFindById(tempUser);
                DashboardDto dashboardDto = new DashboardDto(i.getId(), i.getLink(), i.getTitle(), i.getContent(), tempUser.getNickName(), i.getAddTime(), i.getIsDel(), i.getNote());
                log.info(dashboardDto.toString());
                dashboardDtoList.add(dashboardDto);
            }
            model.addAttribute("dashboards", dashboardDtoList);
            return "analysis/analysis_index";
        } else {
            Msg msg = new Msg("failure", "添加仪表盘失败，未知错误");
            model.addAttribute("msg", msg);
            log.debug("添加仪表盘失败，未知错误");
            List<Dashboard> dashboardList = dashboardService.dashboardFindAll();
            List<DashboardDto> dashboardDtoList = new ArrayList<>();
            log.info("dashboardList: ");
            for (Dashboard i : dashboardList) {
                User tempUser = new User();
                tempUser.setId(i.getFrom());
                tempUser = userService.userFindById(tempUser);
                DashboardDto dashboardDto = new DashboardDto(i.getId(), i.getLink(), i.getTitle(), i.getContent(), tempUser.getNickName(), i.getAddTime(), i.getIsDel(), i.getNote());
                log.info(dashboardDto.toString());
                dashboardDtoList.add(dashboardDto);
            }
            model.addAttribute("dashboards", dashboardDtoList);
            return "analysis/analysis_index";
        }
    }

    @Secured({"ROLE_ADVANCED", "ROLE_ADMIN"})
    @RequestMapping(value = "/analysis/delete/{dashboardId}")
    public String delete(@PathVariable int dashboardId,Model model, HttpSession session, HttpServletRequest request) {
        System.out.println("dashboardId: " + dashboardId);
        User user = (User) session.getAttribute("user");
        Dashboard dashboard = new Dashboard();
        dashboard.setId(dashboardId);
        if (!"ROLE_ADMIN".equals(user.getRole())) {
            dashboard = dashboardService.dashboardFindById(dashboard);
            if (user.getId() != dashboard.getFrom()) {
                Msg msg = new Msg("failure", "删除仪表盘失败，权限不够。");
                model.addAttribute("msg", msg);
                log.debug("删除仪表盘失败，权限不够。");
                List<Dashboard> dashboardList = dashboardService.dashboardFindAll();
                List<DashboardDto> dashboardDtoList = new ArrayList<>();
                log.info("dashboardList: ");
                for (Dashboard i : dashboardList) {
                    User tempUser = new User();
                    tempUser.setId(i.getFrom());
                    tempUser = userService.userFindById(tempUser);
                    DashboardDto dashboardDto = new DashboardDto(i.getId(), i.getLink(), i.getTitle(), i.getContent(), tempUser.getNickName(), i.getAddTime(), i.getIsDel(), i.getNote());
                    log.info(dashboardDto.toString());
                    dashboardDtoList.add(dashboardDto);
                }
                model.addAttribute("dashboards", dashboardDtoList);
                return "analysis/analysis_index";
            }
        }
        if (dashboardService.dashboardDeleteById(dashboard)){
            Msg msg = new Msg("success", "删除仪表盘成功。");
            model.addAttribute("msg", msg);
            log.debug("删除仪表盘成功。");
            List<Dashboard> dashboardList = dashboardService.dashboardFindAll();
            List<DashboardDto> dashboardDtoList = new ArrayList<>();
            log.info("dashboardList: ");
            for (Dashboard i : dashboardList) {
                User tempUser = new User();
                tempUser.setId(i.getFrom());
                tempUser = userService.userFindById(tempUser);
                DashboardDto dashboardDto = new DashboardDto(i.getId(), i.getLink(), i.getTitle(), i.getContent(), tempUser.getNickName(), i.getAddTime(), i.getIsDel(), i.getNote());
                log.info(dashboardDto.toString());
                dashboardDtoList.add(dashboardDto);
            }
            model.addAttribute("dashboards", dashboardDtoList);
            return "analysis/analysis_index";
        } else {
            Msg msg = new Msg("failure", "删除仪表盘失败，未知错误。");
            model.addAttribute("msg", msg);
            log.debug("删除仪表盘失败，未知错误。");
            List<Dashboard> dashboardList = dashboardService.dashboardFindAll();
            List<DashboardDto> dashboardDtoList = new ArrayList<>();
            log.info("dashboardList: ");
            for (Dashboard i : dashboardList) {
                User tempUser = new User();
                tempUser.setId(i.getFrom());
                tempUser = userService.userFindById(tempUser);
                DashboardDto dashboardDto = new DashboardDto(i.getId(), i.getLink(), i.getTitle(), i.getContent(), tempUser.getNickName(), i.getAddTime(), i.getIsDel(), i.getNote());
                log.info(dashboardDto.toString());
                dashboardDtoList.add(dashboardDto);
            }
            model.addAttribute("dashboards", dashboardDtoList);
            return "analysis/analysis_index";
        }
    }
}
