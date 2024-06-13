package yep.greenFire.greenfirebackend.order.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import yep.greenFire.greenfirebackend.delivery.domain.type.DeliveryStatus;
import yep.greenFire.greenfirebackend.order.domain.type.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class StoreOrderDTO {

    private final Long storeOrderCode;
    private final Long storeCode;
    private final String storeName;
    private final OrderStatus orderStatus;

    private final String deliveryCompany;
    private final String transportNumber;
    private final LocalDateTime deliveryDate;
    private final DeliveryStatus deliveryStatus;

    private final LocalDateTime rejectionDate;
    private final String rejectionReason;

    private List<OrderDetailDTO> orderDetails;

}
