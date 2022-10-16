package cn.sgming.springcloud.web;

import cn.sgming.springcloud.entity.CommonResult;
import cn.sgming.springcloud.entity.Payment;
import cn.sgming.springcloud.service.PaymentOpenFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
