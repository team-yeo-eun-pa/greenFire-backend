package yep.greenFire.greenfirebackend.order.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import yep.greenFire.greenfirebackend.order.domain.type.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tbl_store_order")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class StoreOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeOrderCode;

    private Long orderCode;
    private Long storeCode;

    /* 주문 상태 */
    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.RECEIVED;

    /* 주문금액, ... */
    private Long orderAmount;
    private Long discountAmount;
    private Long deliveryAmount;
    private Long realPayment;

    private LocalDateTime completionDate;
    private LocalDateTime rejectionDate;
    private String rejectionReason;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "storeOrderCode")
    private List<OrderDetail> orderDetails;

    // 주문금액
    public void OrderAmount(Long optionPrice) {
        this.orderAmount += optionPrice;
    }

    // 배송비
    public void deliveryAmount(Long deliveryAmount) {
        this.deliveryAmount += deliveryAmount;
    }

    private StoreOrder(Long storeCode, Long orderAmount, Long discountAmount, Long deliveryAmount, Long realPayment, List<OrderDetail> orderDetails) {
        this.storeCode = storeCode;
        this.orderAmount = orderAmount;
        this.discountAmount =  discountAmount;
        this.deliveryAmount = deliveryAmount;
        this.realPayment = realPayment;
        this.orderDetails = orderDetails;
    }

    public static StoreOrder of(long storeCode, long orderAmount, long discountAmount, long deliveryAmount, long realPayment, List<OrderDetail> orderDetails) {
        return new StoreOrder(storeCode, orderAmount,discountAmount, deliveryAmount, realPayment, orderDetails);
    }

    public void modifyStatusApply(OrderStatus orderStatus, LocalDateTime rejectionDate, String rejectionReason) {
        this.orderStatus = orderStatus;
        if (orderStatus == OrderStatus.REJECTED) {
            this.rejectionDate = rejectionDate;
            this.rejectionReason = rejectionReason;
        }

    }
}
