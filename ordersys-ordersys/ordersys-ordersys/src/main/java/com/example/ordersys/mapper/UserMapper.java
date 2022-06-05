package com.example.ordersys.mapper;


import com.example.ordersys.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    //注册
    public int register(UserInfo userInfo);

    public List<UserInfo> getUserList();

    public UserInfo login(UserInfo userInfo);
}
