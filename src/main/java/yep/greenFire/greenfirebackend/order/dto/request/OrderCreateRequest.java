package yep.greenFire.greenfirebackend.order.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class OrderCreateRequest {

    @Min(value = 1)
    private Long orderCode;

    /* 배송지 - 수령자 연락처 주소 요청사항 */
    @NotBlank
    private Long deliveryAddressCode;

    /* 주문금액, 총할인, 배송비, 실결제금액
    * 내가 로직으로 짜야지 여기 있을 필요 없음. */
    private Long orderPrice;
    private Long discountAmount;
    private Long deliveryAmount;
    private Long realPayment;

    private List<StoreOrderRequest> storeOrders;

    @Getter
    public static class StoreOrderRequest {

        @Min(value = 1)
        private Long storeOrderCode;
        private Long orderCode;

        private List<OrderDetailRequest> orderDetails;

    }

    @Getter
    public static class OrderDetailRequest {

        @Min(value = 1)
        private Long orderDetailCode;
        private Long storeOrderCode;

        private Long optionCode;
        private Long orderQuantity;

    }
}
