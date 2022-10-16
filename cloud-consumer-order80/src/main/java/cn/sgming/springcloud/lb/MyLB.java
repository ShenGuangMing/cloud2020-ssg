package cn.sgming.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements LoadBalancer{
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement() {
        int current = atomicInteger.get();
        int next = current == Integer.MAX_VALUE ? 0 : current + 1;
        while (!atomicInteger.compareAndSet(current, next)) {
            current = atomicInteger.get();
            next = current == Integer.MAX_VALUE? 0 : current + 1;
        }
        System.out.println("****next = " + next);
        return next;
    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
