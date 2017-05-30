package org.jozif.service;

import org.jozif.entity.Dashboard;

import java.util.List;

/**
 * Created by hongyu on 2017/4/30.
 */
public interface DashboardService {
    /**
     * 新增dashboard
     *
     * @param dashboard
     * @return
     */
    Boolean dashboardAdd(Dashboard dashboard);

    /**
     * 查找dashboard所有信息
     * 根据dashboard ID查找该dashboard所有信息
     *
     * @param dashboard
     * @return
     */
    Dashboard dashboardFindById(Dashboard dashboard);

    /**
     * 查找所有dashboard
     *
     * @return
     */
    List<Dashboard> dashboardFindAll();

    /**
     * 删除dashboard所有信息
     * 根据dashboard ID删除dashboard
     *
     * @param dashboard
     * @return
     */
    Boolean dashboardDeleteById(Dashboard dashboard);
}
