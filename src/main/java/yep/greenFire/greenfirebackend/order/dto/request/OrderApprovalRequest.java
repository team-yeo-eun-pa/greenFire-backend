package yep.greenFire.greenfirebackend.order.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.order.domain.type.OrderStatus;

@RequiredArgsConstructor
@Getter
public class OrderApprovalRequest {

    private Long orderCode;

    private Long storeCode;
    private Long storeOrderCode;

    // -- 판매자 주문 승인, 거절

    private OrderStatus orderStatus;
    private String rejectionReason;
}
