package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class Controller {
    @Resource
    private RestTemplate restTemplate;
    private static final String PAYMENT_URL = "http://cloud-provider-payment";

    @GetMapping("/consumer/consul")
    public String consul(){
        return restTemplate.getForObject(PAYMENT_URL+"/consul/test",String.class);
    }
}
