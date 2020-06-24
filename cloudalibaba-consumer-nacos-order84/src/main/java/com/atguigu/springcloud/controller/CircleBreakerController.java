package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class CircleBreakerController {
    private static final String SERVICE_URL = "http://nacos-payment-provider";
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/fallback/{id}")
    @SentinelResource(value = "fallback",fallback = "handlerFallback")//fallBack是处理的代码内出现的异常，blockHandler处理的是在sentinel页面配置的qps等内容
    public CommonResult<Payment> fallback(@PathVariable("id") Long id){
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class, id);
        log.info("result-------------:"+result);
        if (id == 4){
            throw new IllegalArgumentException("非法参数异常。");
        }else if (result.getData() == null){
            throw new NullPointerException("该id没有对应记录，空指针异常");
        }
        return result;
    }
    public CommonResult handlerFallback(@PathVariable("id") Long id,Throwable e){
        Payment payment = new Payment(id,"null");
        return new CommonResult(444,"兜底异常handlerFallback"+e.getMessage(),payment);
    }

    //-------------openfeign----------
    @Resource
    private PaymentService paymentService;

    @GetMapping("/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        return paymentService.paymentSQL(id);
    }

}
