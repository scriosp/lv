package com.example.ordersys.controller;

import com.example.ordersys.mapper.OrderDetailMapper;
import com.example.ordersys.model.OrderDetail;
import com.example.ordersys.tools.ResopnseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/detail")
public class OrderDetailController {

    @Resource
    private OrderDetailMapper orderDetailMapper;

    @RequestMapping("/list")
    public ResopnseBody<List<OrderDetail>> getList( int oid){
        List<OrderDetail> list = orderDetailMapper.getList(oid);

        return new ResopnseBody<>(0,"",list);
    }

}
