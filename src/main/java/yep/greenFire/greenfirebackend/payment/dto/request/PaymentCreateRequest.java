package yep.greenFire.greenfirebackend.payment.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PaymentCreateRequest {

    private String orderId;

    private Long paymentAmount; // 가격

}
