package yep.greenFire.greenfirebackend.payment.presentation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yep.greenFire.greenfirebackend.payment.dto.request.PaymentCreateRequest;
import yep.greenFire.greenfirebackend.payment.dto.request.PaymentRequest;
import yep.greenFire.greenfirebackend.payment.service.PaymentService;
@Slf4j
@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    @PostMapping("")
    public ResponseEntity<PaymentCreateRequest> save(@RequestBody PaymentCreateRequest paymentCreateRequest) {

        paymentService.save(paymentCreateRequest);

        return ResponseEntity.ok(paymentCreateRequest);
    }

    @PostMapping("/{orderId}")
    public ResponseEntity<PaymentRequest> modify(@RequestBody PaymentRequest paymentRequest) {

        paymentService.modifyPayment(paymentRequest);

        return ResponseEntity.ok(paymentRequest);
    }
}
