package com.flab.commerce.domain.order.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/orders/v1")
public class OrderController {


    @GetMapping("/cart")
    public void getCart(){

    }

}
