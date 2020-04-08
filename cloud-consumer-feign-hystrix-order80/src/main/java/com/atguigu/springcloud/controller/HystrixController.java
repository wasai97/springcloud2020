package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.HystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class HystrixController {
    @Resource
    private HystrixService hystrixService;
    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id")Integer id){
        log.info("*******id"+id);
        String result = hystrixService.paymentInfo_OK(id);
        log.info("********result"+result);
        return result;
    }

//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//    })
    @HystrixCommand//没有fallback方法就调用全局默认的，有就调自己的
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        log.info("*******id"+id);
        String result = hystrixService.paymentInfo_TimeOut(id);
        log.info("********result"+result);
        return result;
    }

    public String paymentInfo_TimeOutHandler(@PathVariable("id") Integer id){
        return "我是消费者80，对方支付系统繁忙请10秒钟后再试或者自己运行出错 请检查自己。";
    }

    //全局fallback方法
    public String payment_Global_FallbackMethod(){
        return "Global 异常信息，请稍后在试。";
    }
}
