# ribbon

# 1.ribbon自定义轮巡策略

## 1.1新建package
不要在引导类的ComponentScan的扫描范围

## 1.2新建自己的策略类
```java
@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule() {
        return new RandomRule();
    }
}
```

## 1.3在引导类上添加注解
```text
@RibbonClient(name = "", configuration = )
name：对什么服务使用这个策略
configuration：使用的策略配置类
```

# 2.ribbon负载均衡原理

## 2.1默认的负载均衡算法
负载均衡算法（轮训）：rest接口第几次请求 % 服务集群数 = 实际调用的服务位置下标，服务重启后rest接口计数从1开始

服务集群的获取：
```text
List<ServiceInstance> instances = 
                            discoveryClient.getInstances("微服务名称"");
```



