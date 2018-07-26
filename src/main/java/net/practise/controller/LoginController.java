package net.practise.controller;

import net.practise.pojo.User;
import net.practise.service.UserServiceI;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by gjw on 2018/7/26.
 */
@RestController
public class LoginController {

    private static final Log logger = LogFactory.getLog(LoginController.class);

    @Autowired
    private UserServiceI userService;

    @ResponseBody
    @RequestMapping("/register")
    public String Register(String name, String password) {

        String message = null;
        //判断该用户名是否已被注册
        boolean num = userService.isRegister(name);

        if (num == false) {
            message = new String("该用户已经存在！");
            return message;
        } else {
            User user = new User();
            user.setName(name);
            user.setPassword(password);
            //新注册用户错误次数都为0
            user.setMiss_number(0);
            //1超级管理员:直接修改数据库的用户，只能打开mySQL改的
            //2普通会员:通过请求注册的用户
            user.setRole_id(2);
            int flag = userService.userInsert(user);

            if (flag == 1) {
                return message = new String("注册用户成功！");
            } else {
                return message = new String("注册用户失败！");
            }
        }
    }

    @ResponseBody
    @RequestMapping("/login")
    public String Login(HttpServletRequest request, String name, String password) {

        String message = null;
        //根据账号判断数据库中是否存在该用户
        List<User> userList = userService.selectByNameForId(name);
        //如果不存在该用户
        if (userList.size() == 0) {
            //返回注册页面
            return message = new String("该用户不存在！");
            //如果存在该用户
        } else {
            //获得登录失败的次数
            int intMissNumber = userList.get(0).getMiss_number();
            //因为不能有相同的用户名，所以该List<User>只有一个值，可以直接使用获得id值
            int intUserId = userList.get(0).getId();
            //获得该用户上一次登录的时间
            Date dateLogin = userList.get(0).getMiss_time();
            //获得允许登录时间的字段:allow_time
            Date dateAllowTime = userList.get(0).getAllow_time();
            //获得当前时间
            Date dateNow = new Date();
            //根据账号和密码判断是否输入的都正确
            int num = userService.selectByNamePassword(name, password);
            //begin:对能否登录时间的判断
            //如果该时间允许登录
            //如果现在的时间大于允许登录的时间
            if (dateAllowTime == null || dateNow.getTime() > dateAllowTime.getTime()) {
                //begin:对错误登录次数的判断
                //判断错误次数是否大于等于3
                if (intMissNumber >= 3) {
                    //已经登录失败了三次及以上，锁定账号，不允许登录
                    //允许登录的时间加2分钟
                    logger.info("允许登录的时间没有加2分钟前是:" + dateAllowTime);
                    Date dateAfterAllowTime = new Date(dateNow.getTime() + 120000);
                    logger.info("允许登录的时间加2分钟后是:" + dateAfterAllowTime);
                    //修改数据库中的miss_number错误记录的数目
                    //把错误次数清0
                    intMissNumber = 0;
                    User user = new User();
                    user.setId(intUserId);
                    user.setMiss_number(intMissNumber);
                    user.setAllow_time(dateAfterAllowTime);
                    int intFlag = userService.updateByPrimaryKeySelective(user);
                    logger.info("intFlag:" + intFlag);
                    logger.info("222时间允许登录，但是错误次数超过三次！");
                    //错误次数小于三次，可以登录
                } else {

                    //begin:对密码是否正确的判断
                    //如果密码对了
                    if (num != 0) {
                        //把错误次数清0
                        intMissNumber = 0;
                        //记录最新登录的时间
                        dateLogin = new Date();
                        //记录最新的允许登录时间
                        dateAllowTime = new Date();
                        //修改数据库中的miss_number错误记录的数目
                        User user = new User();
                        user.setId(intUserId);
                        user.setMiss_time(dateLogin);
                        user.setMiss_number(intMissNumber);
                        user.setAllow_time(dateAllowTime);
                        int intFlag = userService.updateByPrimaryKeySelective(user);
                        logger.info("intFlag:" + intFlag);
                        //把id保存进session，在后面的文章发表、评论发表时候会用到
                        HttpSession session = request.getSession();
                        session.setAttribute("intUserId", intUserId);
                        //begin:拦截器所需
                        session.setAttribute("userList", userList);
                        //end:拦截器所需
                        //如果密码错了
                    } else {
                        //把错误次数+1
                        intMissNumber = intMissNumber + 1;
                        //修改数据库中的miss_number错误记录的数目
                        User user = new User();
                        user.setId(intUserId);
                        user.setMiss_number(intMissNumber);
                        int intFlag = userService.updateByPrimaryKeySelective(user);
                        logger.info("密码错误的intFlag:" + intFlag);
                    }
                    //end:对密码是否正确的判断
                }
                //end:错误登录次数的判断
                //该时间不允许登录
            } else {
                logger.info("111对时间的判断结果：当前时间不允许登录!");
            }
            //end:对能否登录时间的判断
        }
        return "登陆成功！";
    }
    //end:login方法
}
