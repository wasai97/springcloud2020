package com.atguigu.springcloud.lb;

import com.netflix.appinfo.InstanceInfo;
import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * 手写负载均衡
 */
public interface LoadBalancer {

    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
