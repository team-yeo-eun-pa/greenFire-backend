package yep.greenFire.greenfirebackend.order.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class OrderCreateRequest {

    /* 배송지 - 수령자 연락처 주소 요청사항 */
    @NotNull
    private Long deliveryAddressCode;

    private List<StoreOrderRequest> storeOrders;

    @Getter
    public static class StoreOrderRequest {

        private List<OrderDetailRequest> orderDetails;

    }

    @Getter
    public static class OrderDetailRequest {

        @Min(value = 1)
        private Long optionCode;
        private Long orderQuantity;

    }
}
