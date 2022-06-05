package com.example.ordersys.model;

import lombok.Data;

@Data
public class OrderDetail {
    private int oid;
    private int did;
    private Dish dish;
}
