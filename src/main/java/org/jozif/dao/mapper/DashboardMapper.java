package org.jozif.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.jozif.dao.DashboardDao;
import org.jozif.entity.Dashboard;

import java.util.List;

/**
 * Created by hongyu on 2017/1/15.
 */
@Mapper
public interface DashboardMapper extends DashboardDao {

    /**
     * 查找Dashboard所有信息
     * 根据Dashboard ID查找该Dashboard所有信息
     *
     * @param dashboard
     * @return
     */
    @Override
    Dashboard selectDashboardById(@Param("dashboard") Dashboard dashboard);

    /**
     * 新增dashboard
     *
     * @param dashboard
     * @return
     */
    @Override
    int dashboardAdd(@Param("dashboard") Dashboard dashboard);

    @Override
    List<Dashboard> selectDashboardAll();

    @Override
    int deleteDashboardById(@Param("dashboard") Dashboard dashboard);
}
