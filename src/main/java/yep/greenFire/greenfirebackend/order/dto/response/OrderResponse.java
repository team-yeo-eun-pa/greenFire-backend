package yep.greenFire.greenfirebackend.order.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import yep.greenFire.greenfirebackend.order.domain.entity.Order;
import yep.greenFire.greenfirebackend.order.domain.type.OrderStatus;
import yep.greenFire.greenfirebackend.payment.domain.entity.Payment;
import yep.greenFire.greenfirebackend.payment.domain.type.PaymentWay;
import yep.greenFire.greenfirebackend.product.domain.entity.Product;
import yep.greenFire.greenfirebackend.product.domain.entity.ProductOption;
import yep.greenFire.greenfirebackend.store.domain.entity.Store;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime orderDate;

    private final PaymentWay paymentWay;

    private final List<StoreOrderDTO> storeOrders;



    public OrderResponse(Order order,
                         ProductOption productOption, Product product, Store store, Payment payment) {
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
        this.orderDate = order.getOrderDate();

        this.paymentWay = payment.getPaymentWay();

        // StoreOrder -> StoreOrderDTO 변환
        this.storeOrders = order.getStoreOrders().stream()
                .map(storeOrder -> new StoreOrderDTO(
                        storeOrder.getStoreOrderCode(),
                        store.getStoreName(),
                        storeOrder.getOrderStatus().getOrderStatus(),
                        storeOrder.getOrderDetails().stream()
                                .map(orderDetail -> new OrderDetailDTO(
                                        orderDetail.getOrderDetailCode(),
                                        orderDetail.getOptionCode(),
                                        orderDetail.getOptionPrice(),
                                        orderDetail.getOrderQuantity(),
                                        productOption.getOptionName(),
                                        product.getProductName(),
                                        product.getProductImg()
                                        ))
                                .collect(Collectors.toList())))
                .distinct() // 중복 제거
                .collect(Collectors.toList());

    }
}
