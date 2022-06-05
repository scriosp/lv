package com.example.ordersys.controller;


import com.example.ordersys.mapper.DishMapper;
import com.example.ordersys.model.Dish;
import com.example.ordersys.tools.ResopnseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/dish")
public class DishController {

    @Resource
    private DishMapper dishMapper;

    @RequestMapping("/list")
    public ResopnseBody<List<Dish>> getList(){
        List<Dish> data = dishMapper.getList();
        return new ResopnseBody<>(0,"",data);
    }

    @RequestMapping("/add")
    public ResopnseBody<Integer> add(Dish dish){
        int data = 0;
        data = dishMapper.add(dish);
        return new ResopnseBody<>(0,"",data);
    }

    @RequestMapping("/del")
    public ResopnseBody<Integer> del(int id){
        int data = dishMapper.del(id);
        return new ResopnseBody<>(0,"",data);
    }
}
