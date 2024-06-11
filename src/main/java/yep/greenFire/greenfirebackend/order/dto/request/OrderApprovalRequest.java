package yep.greenFire.greenfirebackend.order.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.order.domain.type.OrderStatus;

@RequiredArgsConstructor
@Getter
public class OrderApprovalRequest {

    private Long orderCode;

    private Long storeCode;

    private String orderStatus;
    
    private String rejectionReason;

}
