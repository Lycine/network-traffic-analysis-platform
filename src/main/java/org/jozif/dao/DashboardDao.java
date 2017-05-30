package org.jozif.dao;

import org.jozif.entity.Dashboard;

import java.util.List;

/**
 * Created by hongyu on 2017/4/30.
 */
public interface DashboardDao {
    /**
     * 查找Dashboard所有信息
     * 根据Dashboard ID查找该Dashboard所有信息
     *
     * @param dashboard
     * @return
     */
    Dashboard selectDashboardById(Dashboard dashboard);

    /**
     * 新增Dashboard
     *
     * @param dashboard
     * @return
     */
    int dashboardAdd(Dashboard dashboard);

    /**
     * 查找所有dashboard
     *
     * @return
     */
    List<Dashboard> selectDashboardAll();

    /**
     * 删除dashboard所有信息
     * 根据dashboard ID删除dashboard
     *
     * @param dashboard
     * @return
     */
    int deleteDashboardById(Dashboard dashboard);
}
