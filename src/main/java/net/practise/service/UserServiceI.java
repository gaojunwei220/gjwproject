package net.practise.service;

import net.practise.pojo.User;

import java.util.List;

/**
 * Created by gjw on 2018/7/26.
 */
public interface UserServiceI {

    public boolean isRegister(String name);

    public int userInsert(User user);

    public List<User> selectByNameForId(String name);

    public int selectByNamePassword(String name, String password);

    public int updateByPrimaryKeySelective(User user);
}
