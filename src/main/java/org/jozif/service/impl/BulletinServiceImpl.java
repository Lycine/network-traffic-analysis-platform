package org.jozif.service.impl;

import org.jozif.dao.BulletinDao;
import org.jozif.entity.Bulletin;
import org.jozif.entity.User;
import org.jozif.service.BulletinService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hongyu on 2017/4/30.
 */
@CommonsLog
@Service("bulletinService")
public class BulletinServiceImpl implements BulletinService {
    private BulletinDao bulletinDao;

    @Autowired
    public BulletinServiceImpl(BulletinDao bulletinDao) {
        this.bulletinDao = bulletinDao;
    }

    @Override
    public Boolean bulletinAdd(Bulletin bulletin) {
        int effect_row = bulletinDao.bulletinAdd(bulletin);
        return effect_row > 0;
    }

    @Override
    public Bulletin bulletinFindById(Bulletin bulletin) {
        return bulletinDao.selectBulletinById(bulletin);
    }

    @Override
    public List<Bulletin> bulletinFindAll() {
        return bulletinDao.selectBulletinAll();
    }

    @Override
    public List<Bulletin> bulletinFindByTea(User user) {
        return bulletinDao.selectBulletinByTeaId(user);
    }

    @Override
    public Bulletin bulletinLastFindByStuId(User user) {
        return bulletinDao.selectBulletinByStuId(user);
    }
}
