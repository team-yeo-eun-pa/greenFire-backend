package yep.greenFire.greenfirebackend.order.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class OrderApprovalRequest {

    private Long orderCode;
    private Long storeOrderCode;

    // -- 판매자 주문 승인, 거절

    private String orderStatus;
    private String rejectionReason;

    // -- 판매자 배송 처리

    // 배송사, 운송장 번호
    private String deliveryCompany;
    private String transportNumber;

    // 일반 배송, 수거, 재배송
    private String deliveryType;

}
