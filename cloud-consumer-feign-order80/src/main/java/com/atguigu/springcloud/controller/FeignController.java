package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.service.FeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class FeignController {
    @Resource
    private FeignService feignService;
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        log.info("*********id"+id);
        CommonResult r = feignService.getPaymentById(id);
        log.info("********result"+r);
        return r;
    }

    /**
     * OpenFeign默认时间为等待一秒，由于有些服务调用时间超过一秒，所以会直接在页面抛出异常，
     * 所有要在配置文件配置超时时间
     * @return
     */
    @GetMapping("/consumer/payment/timeout")
    public String testTimeOut(){
        String s = feignService.testTimeOut();
        return s;
    }
}
