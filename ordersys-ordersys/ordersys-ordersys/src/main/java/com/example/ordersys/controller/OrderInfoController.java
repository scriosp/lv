package com.example.ordersys.controller;


import com.example.ordersys.config.AppFinal;
import com.example.ordersys.mapper.OrderDetailMapper;
import com.example.ordersys.mapper.OrderInfoMapper;
import com.example.ordersys.model.OrderDetail;
import com.example.ordersys.model.OrderInfo;
import com.example.ordersys.model.UserInfo;
import com.example.ordersys.tools.ResopnseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.PushBuilder;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderInfoController {

    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Resource
    private OrderDetailMapper orderDetailMapper;

    @RequestMapping("/add")
    public ResopnseBody<Integer> add(String dids, HttpServletRequest request){
        int data = 0;
        //1.添加订单信息，返回一个 订单ID
        HttpSession session = request.getSession(false);
        if(session != null && session.getAttribute(AppFinal.USERINFO_SESSION_KEY) != null){
            int uid = ((UserInfo)session.getAttribute(AppFinal.USERINFO_SESSION_KEY)).getId();
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setUid(uid);
            //订单添加方法
             int result = orderInfoMapper.add(orderInfo);
             if(result>0){
                 //2,添加订单信息
                data = orderDetailMapper.add(orderInfo.getId(),dids.split(","));
             }
        }
        return new ResopnseBody<>(0,"",data);
    }

    @RequestMapping("/list")
    public ResopnseBody<List<OrderInfo>> getList(HttpServletRequest request){
        List<OrderInfo> list = null;
        HttpSession session = request.getSession(false);
        if(session != null && session.getAttribute(AppFinal.USERINFO_SESSION_KEY) != null) {
            //获取用户id
            int uid = ((UserInfo) session.getAttribute(AppFinal.USERINFO_SESSION_KEY)).getId();
            list = orderInfoMapper.getList(uid);
        }
        return new ResopnseBody<>(0,"",list);
    }

    @RequestMapping("/alllist")
    public ResopnseBody<List<OrderInfo>> getAllList(){
        List<OrderInfo> list = orderInfoMapper.getAllList();
        return new ResopnseBody<>(0,"",list);
    }

    @RequestMapping("/up")
    public ResopnseBody<Integer> up(int oid,int status){
        int data = 0;
        data = orderInfoMapper.up(oid,status);
        return new ResopnseBody<>(0,"",data);
    }
}
