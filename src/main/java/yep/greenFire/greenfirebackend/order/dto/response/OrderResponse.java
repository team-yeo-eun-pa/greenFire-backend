package yep.greenFire.greenfirebackend.order.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.payment.domain.type.PaymentWay;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class OrderResponse {

    private final Long orderCode;
    private final Long memberCode;
    private final String orderName;
    private final String receiverName;
    private final String contactNumber;
    private final String addressZonecode;
    private final String address;
    private final String addressDetail;
    private final String deliveryRequest;
    private final Long totalOrderAmount;
    private final Long totalDiscountAmount;
    private final Long totalDeliveryAmount;
    private final Long totalRealPayment;
    private final LocalDateTime orderDate;
    private final PaymentWay paymentWay;

    private final List<StoreOrderDTO> storeOrders;
}
