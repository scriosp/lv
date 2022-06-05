package com.example.ordersys.tools;

import lombok.Data;

@Data
public class ResopnseBody<T> {
    private int status;
    private String msg;
    private T data;
    public ResopnseBody(int status,
                        String msg,
                        T data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
}
