package cn.sgming.springccloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Order8007 {
    public static void main(String[] args) {
        SpringApplication.run(Order8007.class, args);
    }
}
