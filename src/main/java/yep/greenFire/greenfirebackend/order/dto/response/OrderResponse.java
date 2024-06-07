package yep.greenFire.greenfirebackend.order.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import yep.greenFire.greenfirebackend.order.domain.entity.Order;
import yep.greenFire.greenfirebackend.product.domain.entity.Product;
import yep.greenFire.greenfirebackend.product.domain.entity.ProductOption;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@RequiredArgsConstructor
public class OrderResponse {

    private final Long orderCode;
    private final Long memberCode;

    private final String orderName;
    private final String receiverName;
    private final String contactNumber;

    private final Long addressZonecode;
    private final String address;
    private final String addressDetail;
    private final String deliveryRequest;

    /* 주문금액, 총할인, 배송비, 실결제금액 */
    private final Long totalOrderAmount;
    private final Long totalDiscountAmount;
    private final Long totalDeliveryAmount;
    private final Long totalRealPayment;

    private final List<StoreOrderDTO> storeOrders;

    public OrderResponse(Order order,
                         ProductOption productOption, Product product) {
        this.orderCode = order.getOrderCode();
        this.memberCode = order.getMemberCode();
        this.orderName = order.getOrderName();
        this.receiverName = order.getReceiverName();
        this.contactNumber = order.getContactNumber();
        this.addressZonecode = order.getAddressZonecode();
        this.address = order.getAddress();
        this.addressDetail = order.getAddressDetail();
        this.deliveryRequest = order.getDeliveryRequest();
        this.totalOrderAmount = order.getTotalOrderAmount();
        this.totalDiscountAmount = order.getTotalDiscountAmount();
        this.totalDeliveryAmount = order.getTotalDeliveryAmount();
        this.totalRealPayment = order.getTotalRealPayment();

        this.storeOrders = new ArrayList<>();

    }

    public static OrderResponse from(final OrderResponse order) {
        return new OrderResponse(
                order.getOrderCode(),
                order.getMemberCode(),
                order.getOrderName(),
                order.getReceiverName(),
                order.getContactNumber(),
                order.getAddressZonecode(),
                order.getAddress(),
                order.getAddressDetail(),
                order.getDeliveryRequest(),
                order.getTotalOrderAmount(),
                order.getTotalDiscountAmount(),
                order.getTotalDeliveryAmount(),
                order.getTotalRealPayment(),
                order.getStoreOrders()
        );
    }
}
