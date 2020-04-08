package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
//对一个微服务统一的fallback
public class HystrixServiceFallback implements HystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "HystrixServiceFallback"+"异常"+"paymentInfo_OK";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "HystrixServiceFallback"+"异常"+"paymentInfo_OK";
    }
}
