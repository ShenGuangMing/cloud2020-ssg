# Consul

# 1.下载与安装

## 1.1下载地址：https://developer.hashicorp.com/consul/downloads

## 1.2中文文档：https://www.springcloud.cc/spring-cloud-consul.html

## 解压压缩包会得到.exe文件

# 2.运行

## 2.1在对应的.exe文件cmd运行
- consul --version 查看版本号
- consul agent -dev 运行

## 2.2进入控制页面
    http://localhost:8500

# 3.集成到微服务

## 3.1服务端

### 3.1.1导入依赖
```xml
 <dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-consul-discovery</artifactId>
</dependency>
```

## 3.1.2修改yaml文件
```yaml
spring:
  cloud:
    consul:
      host: localhost
      port: 8500
      discovery:
        service-name: ${spring.application.name}
```

## 3.2客户端

### 3.2.1导入依赖
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-consul-discovery</artifactId>
</dependency>
```

### 3.2.2修改yaml文件
```yaml
spring:
  cloud:
    # consul地址
    consul:
      host: localhost
      port: 8500
      discovery:
        service-name: ${spring.application.name}
```

