package yep.greenFire.greenfirebackend.order.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import yep.greenFire.greenfirebackend.order.domain.type.OrderStatus;

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

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "storeOrderCode")
    private List<OrderDetail> orderDetail;

//    public StoreOrder(Long storeCode, List<OrderDetail> orderDetail) {
//        this.storeCode = storeCode;
//        this.orderDetail = orderDetail;
//    }
//
//    public static StoreOrder of(Long storeCode List<OrderDetail> orderDetail) {
//        return new StoreOrder(storeCode, orderDetail);
//    }
}
