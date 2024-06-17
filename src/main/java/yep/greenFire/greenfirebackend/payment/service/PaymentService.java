package yep.greenFire.greenfirebackend.payment.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yep.greenFire.greenfirebackend.common.exception.NotFoundException;
import yep.greenFire.greenfirebackend.common.exception.type.ExceptionCode;
import yep.greenFire.greenfirebackend.order.domain.entity.Order;
import yep.greenFire.greenfirebackend.order.domain.repository.OrderRepository;
import yep.greenFire.greenfirebackend.payment.domain.entity.Payment;
import yep.greenFire.greenfirebackend.payment.domain.repository.PaymentRepository;
import yep.greenFire.greenfirebackend.payment.domain.type.PaymentStatus;
import yep.greenFire.greenfirebackend.payment.domain.type.PaymentWay;
import yep.greenFire.greenfirebackend.payment.dto.request.PaymentCreateRequest;
import yep.greenFire.greenfirebackend.payment.dto.request.PaymentRequest;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public void save(PaymentCreateRequest paymentCreateRequest) {

        final Payment newPayment = Payment.of(
                paymentCreateRequest.getOrderId(),
                paymentCreateRequest.getPaymentAmount()
        );
        paymentRepository.save(newPayment);
    }

    public void modifyPayment(PaymentRequest paymentRequest) {

        String orderId = paymentRequest.getOrderId();

        Optional<Payment> paymentOptional = paymentRepository.findByOrderId(orderId);
        Payment payment = paymentOptional
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_VALID_PAYMENT));

        // 토스에서 method 응답 결과가 String = "간편결제" -> PaymentWay 타입으로 받아야함.
        PaymentWay paymentWay = PaymentWay.fromValue(paymentRequest.getMethod());
        PaymentStatus paymentStatus = PaymentStatus.fromValue(paymentRequest.getStatus());

        // LocalDateTime.parse는 오프셋을 포함한 문자열을 파싱할 수 없습니다
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

        OffsetDateTime requestedAtOffset = OffsetDateTime.parse(paymentRequest.getRequestedAt(), formatter);
        OffsetDateTime approvedAtOffset = OffsetDateTime.parse(paymentRequest.getApprovedAt(), formatter);

        LocalDateTime requestedAt = requestedAtOffset.toLocalDateTime();
        LocalDateTime approvedAt = approvedAtOffset.toLocalDateTime();

        payment.modifyPayment(
                paymentRequest.getPaymentKey(),
                paymentWay,
                paymentRequest.getBalanceAmount(),
                requestedAt,
                approvedAt,
                paymentRequest.getLastTransactionKey(),
                paymentStatus
        );

        // 주문 취소 여부를 false로 설정
        System.out.println("payment.getOrderCode()"+payment.getOrderCode());
        System.out.println("payment"+payment);

        Optional<Order> orderOptional = orderRepository.findByOrderCode(payment.getOrderCode());
        Order order = orderOptional
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_VALID_ORDER));

        order.setOrderCancel(false); // 주문 취소 여부를 false로 설정
    }
}