package com.atguigu.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class Controller {
    @Resource
    private RestTemplate restTemplate;
    private static final String PAYMENT_URL = "http://cloud-provider-payment";
    @GetMapping("/consumer/zk")
    public String zk(){
        return restTemplate.getForObject(PAYMENT_URL+"/zk/test",String.class);
    }
}
