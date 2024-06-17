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
    private final Boolean isOrderCancel;

    private final List<StoreOrderDTO> storeOrders;

    // 명시적인 생성자 추가
    public OrderResponse(Long orderCode, Long memberCode, String orderName, String receiverName, String contactNumber,
                         String addressZonecode, String address, String addressDetail, String deliveryRequest,
                         Long totalOrderAmount, Long totalDiscountAmount, Long totalDeliveryAmount, Long totalRealPayment,
                         Boolean isOrderCancel, LocalDateTime orderDate, PaymentWay paymentWay, List<StoreOrderDTO> storeOrders) {
        this.orderCode = orderCode;
        this.memberCode = memberCode;
        this.orderName = orderName;
        this.receiverName = receiverName;
        this.contactNumber = contactNumber;
        this.addressZonecode = addressZonecode;
        this.address = address;
        this.addressDetail = addressDetail;
        this.deliveryRequest = deliveryRequest;
        this.totalOrderAmount = totalOrderAmount;
        this.totalDiscountAmount = totalDiscountAmount;
        this.totalDeliveryAmount = totalDeliveryAmount;
        this.totalRealPayment = totalRealPayment;
        this.isOrderCancel = isOrderCancel; // 추가된 필드
        this.orderDate = orderDate;
        this.paymentWay = paymentWay;
        this.storeOrders = storeOrders;
    }
}