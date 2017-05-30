package org.jozif.service.impl;

import org.jozif.dao.UserDao;
import org.jozif.entity.User;
import org.jozif.service.UserService;
import com.github.pagehelper.PageHelper;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hongyu on 2017/1/15.
 */
@CommonsLog
@Service("UserService")
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public Boolean userUpdateById(User user) {
        return userDao.updateUserById(user) > 0;
    }

    @Override
    public User userFindById(User user) {
        return userDao.selectUserById(user);
    }

    @Override
    public User userFindByEmail(User user) {
        return userDao.selectUserByEmail(user);
    }

    @Override
    public Boolean isDuplicateEmail(User user) {
        return null != userDao.selectUserByEmail(user);
    }

    @Override
    public Boolean isDuplicatePhoneNumber(User user) {
        user = userDao.selectUserByPhoneNumber(user);
        return null != user;
    }

    @Override
    public Boolean isDuplicateNickName(User user) {
        return null != userDao.selectUserByNickName(user);
    }

    @Override
    public Boolean userAdd(User user) {
        return  userDao.addUser(user) > 0;
    }


    @Override
    public User loadUserByUsername(User user) {
        User resultuser = userDao.selectUserByEmail(user);
        if (null == resultuser){
            resultuser = userDao.selectUserByPhoneNumber(user);
        }
        return resultuser;
    }

    @Override
    public List<User> userFindAll(int page, int rows) {
        PageHelper.startPage(page, rows);
        return userDao.selectAllUser();
    }

}
