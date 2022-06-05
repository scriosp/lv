package com.example.ordersys.model;

import lombok.Data;

import java.util.Date;

@Data
public class OrderInfo {
    private int id;
    private int uid;
    private Date createtime;
    private int status;
    private String uname;
}
