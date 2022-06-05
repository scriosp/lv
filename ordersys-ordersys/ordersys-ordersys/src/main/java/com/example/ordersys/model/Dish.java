package com.example.ordersys.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Dish {
    private int id;
    private String name;
    private BigDecimal price;
}
