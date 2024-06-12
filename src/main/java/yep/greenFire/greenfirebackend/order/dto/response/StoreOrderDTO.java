package yep.greenFire.greenfirebackend.order.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class StoreOrderDTO {

    private final Long storeOrderCode;
    private final String storeName;
    private final String orderStatus;

    private final List<OrderDetailDTO> orderDetails;

}
