package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.HystrixService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class HystrixController {
    @Resource
    private HystrixService hystrixService;
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id")Integer id){
        log.info("*******id"+id);
        String result = hystrixService.paymentInfo_OK(id);
        log.info("*********result"+result);
        return result;
    }
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        log.info("*******id"+id);
        String result = hystrixService.paymentInfo_TimeOut(id);
        log.info("*********result"+result);
        return result;
    }

    @GetMapping("/payment/hystrix/breaker/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        log.info("********id"+id);
        String result = hystrixService.paymentCircuitBreaker(id);
        log.info("************result"+result);
        return result;
    }


}
