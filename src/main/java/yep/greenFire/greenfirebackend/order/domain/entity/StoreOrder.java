package yep.greenFire.greenfirebackend.order.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import yep.greenFire.greenfirebackend.order.domain.type.OrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @ManyToOne
    @JoinColumn
    private Order order;
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

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<OrderDetail> orderDetails;

    public StoreOrder(Long storeCode, Long orderAmount, Long deliveryAmount, List<OrderDetail> orderDetails) {
        this.storeCode = storeCode;
        this.orderAmount = orderAmount;
        this.deliveryAmount = deliveryAmount;
        this.orderDetails = orderDetails;
    }

    public static StoreOrder of(long storeCode, long orderAmount, long deliveryAmount, List<OrderDetail> orderDetails) {
        return new StoreOrder(storeCode, orderAmount, deliveryAmount, orderDetails);
    }
}
