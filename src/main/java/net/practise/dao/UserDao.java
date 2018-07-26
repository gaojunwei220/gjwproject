package net.practise.dao;

import com.mysql.jdbc.StringUtils;
import net.practise.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gjw on 2018/7/26.
 */
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean isRegister(String name) {

        String sql = " select  name  from user  where name= ? ";
        List<User> userList = jdbcTemplate.query(sql, new Object[]{name}, new BeanPropertyRowMapper<>(User.class));
        for (User user : userList) {
            if (StringUtils.isNullOrEmpty(user.getName())) {
                return true;
            }
        }
        return false;
    }


    public int insertUser(User user) {

        String sql = "INSERT into user ( name, password, miss_number, miss_time) VALUES (?,?,?,?)";
        int isSuccessOrfaild = jdbcTemplate.update(sql, user.getName(), user.getPassword(), user.getMiss_number(), user.getMiss_time());
        return isSuccessOrfaild;
    }

    public List<User> selectByNameForId(String name) {

        String sql = "select  *  from user where name=?";
        List<User> userList = jdbcTemplate.query(sql, new Object[]{name}, new BeanPropertyRowMapper<>(User.class));
        return userList;
    }

    public int selectByNamePassword(String name, String password) {


        return 0;
    }

    public int updateByPrimaryKeySelective(User user) {


        return 0;
    }
}
