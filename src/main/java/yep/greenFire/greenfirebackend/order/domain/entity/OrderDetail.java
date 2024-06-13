package yep.greenFire.greenfirebackend.order.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import yep.greenFire.greenfirebackend.product.domain.entity.Product;

@Entity
@Table(name = "tbl_order_detail")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderDetailCode;

    private Long storeOrderCode;
    private Long optionCode;

    private Long optionPrice;
    private Long orderQuantity;

    /* 쿠폰 사용 여부 - 기본값 false */
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isCouponUsed = false;
    /* 주문 취소 여부 - 기본값 false */
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isOrderCancel = false;

    @ManyToOne
    @JoinColumn(name = "productCode", referencedColumnName = "productCode")
    private Product product;

    private OrderDetail(Long optionCode, Long optionPrice, Long orderQuantity) {
        this.optionCode = optionCode;
        this.optionPrice = optionPrice;
        this.orderQuantity = orderQuantity;
    }

    public static OrderDetail of(Long optionCode, Long optionPrice, Long orderQuantity) {
        return new OrderDetail(optionCode, optionPrice, orderQuantity);
    }

}
