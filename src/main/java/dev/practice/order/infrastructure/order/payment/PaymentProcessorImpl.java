package dev.practice.order.infrastructure.order.payment;

import dev.practice.order.common.exception.InvalidParamException;
import dev.practice.order.domain.order.Order;
import dev.practice.order.domain.order.OrderCommand;
import dev.practice.order.domain.order.payment.PaymentProcessor;
import dev.practice.order.domain.order.payment.validator.PaymentValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentProcessorImpl implements PaymentProcessor {
    // 복수개의 빈 생성
    private final List<PaymentValidator> paymentValidators;
    private final List<PaymentApiCaller> paymentApiCallers;

    @Override
    public void pay(Order order, OrderCommand.PaymentRequest request) {
        paymentValidators.forEach(paymentValidator -> paymentValidator.validate(order, request));
        PaymentApiCaller paymentApiCaller = routingApiCaller(request);
        paymentApiCaller.pay(request);
    }

    private PaymentApiCaller routingApiCaller(OrderCommand.PaymentRequest request) {
        return paymentApiCallers.stream()
                .filter(paymentApiCaller -> paymentApiCaller.support(request.getPayMethod()))
                .findFirst()
                .orElseThrow(InvalidParamException::new);
    }
}
