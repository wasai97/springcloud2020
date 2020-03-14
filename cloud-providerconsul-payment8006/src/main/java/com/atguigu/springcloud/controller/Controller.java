package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class Controller {
    @Value("${server.port}")
    private String serverPort;
    @GetMapping("/consul/test")
    public String consul(){
        String s = "端口号"+serverPort+""+ UUID.randomUUID().toString();
        return s;
    }
}
