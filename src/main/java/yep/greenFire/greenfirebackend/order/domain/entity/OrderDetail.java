package yep.greenFire.greenfirebackend.order.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "tbl_order_detail")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderCode;
    private Long optionCode;
    private Long storeOrderCode;

    private Long optionPrice;
    private Long orderQuantity;
    private Long orderDisCount;

    /* 주문 취소 여부 - 기본값 false */
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean OrderCancel = false;
}
