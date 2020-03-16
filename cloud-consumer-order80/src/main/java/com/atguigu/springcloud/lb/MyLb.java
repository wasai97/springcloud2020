package com.atguigu.springcloud.lb;

import com.netflix.appinfo.InstanceInfo;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 轮询算法核心，用请求的次数与服务个数求余，最后从服务中取出对应服务调用
 */
@Component
public class MyLb implements LoadBalancer {
    private AtomicInteger atomicInteger = new AtomicInteger();
    public int getIndex(){
        int current;
        int next;
        do {
            current = atomicInteger.get();
            next = (current > Integer.MAX_VALUE ? 0 : current+1);
        }while (! atomicInteger.compareAndSet(current,next));
        System.out.println("当前next：\t"+next);
        return next;
    }
    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = getIndex() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
