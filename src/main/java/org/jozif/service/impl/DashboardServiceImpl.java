package org.jozif.service.impl;

import lombok.extern.apachecommons.CommonsLog;
import org.jozif.dao.DashboardDao;
import org.jozif.entity.Dashboard;
import org.jozif.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hongyu on 2017/4/30.
 */
@CommonsLog
@Service("dashboardService")
public class DashboardServiceImpl implements DashboardService {
    private DashboardDao dashboardDao;

    @Autowired
    public DashboardServiceImpl(DashboardDao dashboardDao) {
        this.dashboardDao = dashboardDao;
    }

    @Override
    public Boolean dashboardAdd(Dashboard dashboard) {
        int effect_row = dashboardDao.dashboardAdd(dashboard);
        return effect_row > 0;
    }

    @Override
    public Dashboard dashboardFindById(Dashboard dashboard) {
        return dashboardDao.selectDashboardById(dashboard);
    }

    @Override
    public List<Dashboard> dashboardFindAll() {
        return dashboardDao.selectDashboardAll();
    }

    @Override
    public Boolean dashboardDeleteById(Dashboard dashboard){
        int effect_row = dashboardDao.deleteDashboardById(dashboard);
        return effect_row > 0;
    }
}
