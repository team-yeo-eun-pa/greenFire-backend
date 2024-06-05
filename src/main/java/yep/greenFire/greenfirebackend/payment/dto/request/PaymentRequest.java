package yep.greenFire.greenfirebackend.payment.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentRequest {

    private String orderId;

    private String paymentKey;
    private String method;

    private Long balanceAmount;
    private String requestedAt;
    private String approvedAt;
    private String lastTransactionKey;
    private String status;

}
