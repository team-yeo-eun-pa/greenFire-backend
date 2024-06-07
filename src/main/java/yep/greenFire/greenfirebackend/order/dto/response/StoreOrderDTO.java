package yep.greenFire.greenfirebackend.order.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.order.domain.entity.StoreOrder;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class StoreOrderDTO {

    private final Long storeOrderCode;
    private final String orderStatus;

    private final List<OrderDetailDTO> orderDetails;

    public static StoreOrderDTO from(final StoreOrderDTO storeOrder) {
        return new StoreOrderDTO(
                storeOrder.getStoreOrderCode(),
//                storeOrder.getOrderStatus().getOrderStatus(),
                storeOrder.getOrderStatus(),
                storeOrder.getOrderDetails()
        );
    }
}
