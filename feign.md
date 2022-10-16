# feign

# 1.feign的集成

## 1.1新建项目作为消费端

## 1.2pom依赖
```xml
<dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <dependency>
            <groupId>cn.SGMing</groupId>
            <artifactId>spring-api-commons</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <!--包含了sleuth+zipkin-->
        <!--        <dependency>-->
        <!--            <groupId>org.springframework.cloud</groupId>-->
        <!--            <artifactId>spring-cloud-starter-zipkin</artifactId>-->
        <!--        </dependency>-->

    </dependencies>
```

## 1.3yaml配置文件
可变参数可以自定义
```yaml
server:
  port: 8849

spring:
  application:
    # 自定义
    name: cloud-consumer-feign-service
  # 自定义
  datasource:
    url: jdbc:mysql://localhost:3306/db2019?useUnicode=true&characterEncoding=utf-8&useSSL=false
    username: root
    password: root


eureka:
  client:
    # 根据自己的eureka服务地址来
    service-url: # eureka的地址信息
      defaultZone: http://eureka7002.com:7002/eureka/,http://eureka7001.com:7001/eureka/
    register-with-eureka: false
```

## 1.4添加调用其他服务的service层
```java
@Component
@FeignClient("cloud-payment-service")
public interface PaymentOpenFeign {
    @GetMapping("/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);
}
```
> 类似我们的mapping层

## 1.5添加controller层
调用刚刚的service层
```java
@RestController
@RequestMapping("/consumer")
public class OrderFeignController {

    @Autowired
    private PaymentOpenFeign paymentOpenFeign;

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return paymentOpenFeign.getPaymentById(id);
    }

}
```

## 1.6启动服务即可

# 2.openfeign设置超时时间
默认feign超时时间为1s
## 2.1添加如下到yaml配置文件修改超时时间
```yaml
ribbon:
  # 建立连接所用时间，适用于网络状态正常的情况，两端连接所用的时间
  ReadTimeout: 5000
  # 建立连接后从服务器读取可用资源的所用时间
  ConnectTimeout: 5000
```

# 3.openfeign日志打印功能
- NONE：默认的
- BASIC：记录请求方法，URL，响应状态码和时间
- HEADERS：在BASIC上添加了，请求的响应头信息
- FULL：在HEADERS添加了请求和响应的正文和元数据

## 3.1将日志级别注入
```java
@Configuration
public class FeignConfig {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
```

## 3.2在yaml配置文件中开启日志

