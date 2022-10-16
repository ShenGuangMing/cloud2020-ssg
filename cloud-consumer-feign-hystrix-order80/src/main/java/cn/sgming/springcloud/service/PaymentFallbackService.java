package cn.sgming.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfoError(Integer id) {
        return "PaymentFallbackService-paymentInfoError error";
    }

    @Override
    public String paymentInfoOK(Integer id) {
        return "PaymentFallbackService-paymentInfoOK error";
    }
}
