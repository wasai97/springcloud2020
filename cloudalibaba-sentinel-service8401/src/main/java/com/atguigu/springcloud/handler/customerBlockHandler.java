package com.atguigu.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;

public class customerBlockHandler {
    public static CommonResult handlerException(BlockException exception){
        return new CommonResult(4444,"自定义处理错误-------1");
    }

    public static CommonResult handlerException2(BlockException exception){
        return new CommonResult(4444,"自定义处理错误----------2");
    }
}
