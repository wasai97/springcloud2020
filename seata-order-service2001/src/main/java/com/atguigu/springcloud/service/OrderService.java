package com.atguigu.springcloud.service;

import com.atguigu.springcloud.domain.Order;
import org.springframework.cloud.openfeign.FeignClient;


public interface OrderService {
    void create(Order order);

}
