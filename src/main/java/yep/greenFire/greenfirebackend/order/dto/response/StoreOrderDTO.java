package yep.greenFire.greenfirebackend.order.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.order.domain.entity.StoreOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class StoreOrderDTO {

    private final Long storeOrderCode;
//    private final String storeName;
    private final String orderStatus;

    private final List<OrderDetailDTO> orderDetails;

}
