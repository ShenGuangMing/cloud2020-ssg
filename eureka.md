# Eureka搭建

# 1.Eureka简单搭建

## 1.1Eureka服务端搭建

### 1.1.1新建项目作为Eureka服务端

### 1.1.2添加依赖
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
```

### 1.1.3添加yml配置文件
```yaml
server:
  port: 10086

eureka:
  instance:
    hostname: localhost

  client:
    # false表示不将自己注册到注册中心，true和false没有什么影响
    register-with-eureka: false
    # false表示自己端就是注册中心，职责就是维护服务实例，并不需要去搜索服务
    fetch-registry: false
    service-url:
      # 设置Eureka Server交互的地址查询服务和注册服务都依赖这个地址
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/

```

### 1.1.4添加启动类
```java
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }
}
```
> 启动后点击进入http://{eureka.instance.hostname}:{server.port}/就可以进入服务端控制页面

## 1.2Eureka客户端搭建
### 1.2.1给需要的微服务项目添加依赖
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

### 1.2.2修改yml配置文件
```yaml
spring:
  application:
    name: cloud-payment-service

eureka:
  client:
    service-url: # eureka的地址信息
      defaultZone: http://127.0.0.1:10086/eureka
    fetch-registry: true
    register-with-eureka: true
```

### 1.2.3再引导类添加Eureka客户端注解
    @EnableEurekaClient


# 2.Eureka集群（两位服务为例子）
先在[C:\Windows\System32\drivers\etc]下的host文件中添加
    
    127.0.0.1	eureka7001.com
    127.0.0.1	eureka7002.com

## 2.1添加多个微服务作为Eureka服务端


## 2.2修改yaml配置文件
7002端口的
```yaml
server:
  port: 7002
spring:
  application:
    name: cloud-eureka7002-server

eureka:
  instance:
    hostname: eureka7002.com
  client:
    # false表示不将自己注册到注册中心，true和false没有什么影响
    register-with-eureka: false
    # false表示自己端就是注册中心，职责就是维护服务实例，并不需要去搜索服务
    fetch-registry: false
    service-url:
      # 设置Eureka Server交互的地址查询服务和注册服务都依赖这个地址
      defaultZone: http://eureka7001.com:7001/eureka/

```
7001端口的
```yaml
server:
  port: 7001
spring:
  application:
    name: cloud-eureka7001-server

eureka:
  instance:
    hostname: eureka7001.com
  client:
    # false表示不将自己注册到注册中心，true和false没有什么影响
    register-with-eureka: false
    # false表示自己端就是注册中心，职责就是维护服务实例，并不需要去搜索服务
    fetch-registry: false
    service-url:
      # 设置Eureka Server交互的地址查询服务和注册服务都依赖这个地址
      defaultZone: http://eureka7002.com:7002/eureka/

```

## 2.3客户端的yaml配置文件修改
只修改 defaultZone
```yaml
eureka:
  client:
    service-url: # eureka的地址信息
      defaultZone: http://eureka7002.com:7002/eureka/,http://eureka7001.com:7001/eureka/
      #单机的
#      defaultZone: http://127.0.0.1:7001/eureka
```

## 2.4启动测试
启动顺序：服务端>客户端

# 3.Eureka客户端集群
和Eureka服务端集群类似，主要是修改yaml文件中的端口

# 4.禁止Eureka自动保护

## 4.1修改服务端yaml配置文件
```yaml
eureka:
  server:
    # 关闭自我保护机制，保证不可用服务及时被剔除
    enable-self-preservation: false
    eviction-interval-timer-in-ms: 2000
```
## 4.2修改客户端yaml配置文件
```yaml
eureka:
  instance:
    instance-id: {id}
    prefer-ip-address: true
    # Eureka客户端向服务端发送心跳的间隔时间，单位秒（默认30）
    lease-renewal-interval-in-seconds: 1
    # Eureka服务端接收到最后一次心跳后等待的使时间上限，单位秒（默认90），超时将提出服务
    lease-expiration-duration-in-seconds: 2
    

```