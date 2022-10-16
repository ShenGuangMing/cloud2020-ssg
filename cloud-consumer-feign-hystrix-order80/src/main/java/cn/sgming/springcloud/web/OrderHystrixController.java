package cn.sgming.springcloud.web;

import cn.sgming.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
@Slf4j
@DefaultProperties(defaultFallback = "defaultFallback")
public class OrderHystrixController {
    @Autowired
    private PaymentHystrixService paymentHystrixService;


    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfoOK(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentInfoOK(id);
    }

    @GetMapping("/payment/hystrix/error/{id}")
    @HystrixCommand(fallbackMethod = "paymentInfoErrorHandler", commandProperties = {
            //设置超时时间
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })
//    @HystrixCommand
    public String paymentInfoError(@PathVariable("id") Integer id) {
        System.out.println(1/id);
        return paymentHystrixService.paymentInfoError(id);
    }

    public String paymentInfoErrorHandler(Integer id) {
        return "线程池:" + Thread.currentThread().getName() + "-errorHandler-错误或超时";
    }

    //全局的fallback方法
    public String defaultFallback() {
        return "Hystrix的defaultFallback";
    }
}
