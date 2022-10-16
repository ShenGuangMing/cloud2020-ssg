package cn.sgming.springcloud.web;

import cn.sgming.springcloud.entity.CommonResult;
import cn.sgming.springcloud.entity.Payment;
import cn.sgming.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Value("${server.port}")
    private String serverPort;
    @PostMapping("/save")
    public CommonResult<Integer> saveOne(@RequestBody Payment payment) {
        int result = paymentService.saveOne(payment);
        log.info("insert result: " + result);
        if (result > 0) {
            return new CommonResult<>(200, "插入数据成功" + serverPort, result);
        }else {
            return new CommonResult<>(444, "插入数据失败" + serverPort);

        }
    }

    @GetMapping("/get/{id}")
    public CommonResult<Payment> getOneById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getOneById(id);
        log.info("get result: " + payment);
        if (payment != null) {
            return new CommonResult<>(200, "查询数据成功" + serverPort, payment);
        }else {
            return new CommonResult<>(444, "查询数据失败" + serverPort);
        }
    }

    @GetMapping("/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("service-element: " + service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost()
                    + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return discoveryClient;
    }

    @GetMapping("/lb")
    public String getPaymentLb() {
        return serverPort;
    }

}
