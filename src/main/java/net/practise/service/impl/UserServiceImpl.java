package net.practise.service.impl;

import net.practise.dao.UserDao;
import net.practise.pojo.User;
import net.practise.service.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gjw on 2018/7/26.
 */
@Service
public class UserServiceImpl implements UserServiceI {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean isRegister(String name) {
        return userDao.isRegister(name);
    }

    @Override
    public int userInsert(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public List<User> selectByNameForId(String name) {
        return userDao.selectByNameForId(name);
    }

    @Override
    public int selectByNamePassword(String name, String password) {
        return userDao.selectByNamePassword(name,password);
    }

    @Override
    public int updateByPrimaryKeySelective(User user) {
        return userDao.updateByPrimaryKeySelective(user);
    }
}
