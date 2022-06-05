package com.example.ordersys.controller;

import com.example.ordersys.config.AppFinal;
import com.example.ordersys.mapper.UserMapper;
import com.example.ordersys.model.UserInfo;
import com.example.ordersys.tools.ResopnseBody;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Resource
    private UserMapper userMapper;

    /**
     * 用户注册
     * @return
     */
    @RequestMapping("/reg")
    public ResopnseBody<Integer> register(UserInfo userInfo){
        int data = userMapper.register(userInfo);
        return new ResopnseBody<>(0,"",data);
    }

    /**
     * 查询所有用户列表
     * @return
     */
    @RequestMapping("/ulist")
    public ResopnseBody<List<UserInfo>> getUserList(){
        List<UserInfo> data = new ArrayList<>();
        data = userMapper.getUserList();
        return new ResopnseBody<>(0,"",data);
    }

    /**
     * 登陆方法
     * @return
     */
    @RequestMapping("/login")
    public ResopnseBody<UserInfo>login (UserInfo userInfo, HttpServletRequest request){
        UserInfo user = userMapper.login(userInfo);
        if(user != null && user.getId() > 0){
            //登录成功，存储session信息
            HttpSession session = request.getSession();
            session.setAttribute(AppFinal.USERINFO_SESSION_KEY,user);
        }
        return new ResopnseBody<>(0,"",user);
    }
    /**
     * 判断登录状态
     */
    @RequestMapping("/islogin")
    public ResopnseBody<UserInfo> isLogin(HttpServletRequest request){
        UserInfo userInfo = null;
        HttpSession session = request.getSession(false);
        if (session != null &&
        session.getAttribute(AppFinal.USERINFO_SESSION_KEY)!=null){
           userInfo =(UserInfo) session.getAttribute(AppFinal.USERINFO_SESSION_KEY);
        }
        return new ResopnseBody<>(0,"",userInfo);
    }

    /**
     * 退出登陆
     */
    @RequestMapping("/logout")
    public ResopnseBody<Integer> logout(HttpServletRequest request){
        int data = 0;
        HttpSession session = request.getSession(false);
        if (session != null &&
                session.getAttribute(AppFinal.USERINFO_SESSION_KEY)!=null){
                session.removeAttribute(AppFinal.USERINFO_SESSION_KEY);
                data = 1;
        }
        return new ResopnseBody<>(0,"",data);
    }
}
