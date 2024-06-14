package yep.greenFire.greenfirebackend.order.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class OrderCreateRequest {

    // 주문 정보
    @NotNull
    private Long deliveryAddressCode;

    // 스토어별 주문 정보
    private List<StoreOrderRequest> storeOrders;

    @Getter
    public static class StoreOrderRequest {

        private Long storeCode;

        // 주문 상세 정보
        private List<OrderDetailRequest> orderDetails;

    }

    @Getter
    public static class OrderDetailRequest {

        @Min(value = 1)
        private Long optionCode;
        private Long optionPrice;

        private Long orderQuantity;

    }
}
