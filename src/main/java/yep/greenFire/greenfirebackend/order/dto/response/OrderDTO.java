package yep.greenFire.greenfirebackend.order.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.payment.domain.type.PaymentWay;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class OrderDTO {

    private final Long orderCode;
    private final String orderName;
    private final String receiverName;
    private final String contactNumber;
    private final String addressZonecode;
    private final String address;
    private final String addressDetail;
    private final String deliveryRequest;
    /* 주문금액, 총할인, 배송비, 실결제금액 */
    private final Long totalOrderAmount;
    private final Long totalDiscountAmount;
    private final Long totalDeliveryAmount;
    private final Long totalRealPayment;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime orderDate;
    private final PaymentWay paymentWay;

    private final Long storeOrderCode;
    private final String storeName;
    private final String orderStatus;
    private final Long orderDetailCode;
    private final Long optionCode;
    private final Long optionPrice;
    private final Long orderQuantity;

    private final String optionName;
    private final String productName;
    private final String productImg;

}

