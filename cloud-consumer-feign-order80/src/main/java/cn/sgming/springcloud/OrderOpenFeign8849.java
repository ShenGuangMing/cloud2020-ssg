package cn.sgming.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderOpenFeign8849 {
    public static void main(String[] args) {
        SpringApplication.run(OrderOpenFeign8849.class, args);
    }
}
