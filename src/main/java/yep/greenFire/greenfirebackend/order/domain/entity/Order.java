package yep.greenFire.greenfirebackend.order.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import yep.greenFire.greenfirebackend.delivery.domain.type.AddressType;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tbl_order")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderCode;
    private Long memberCode;

    private String orderName;

    //카카오 우편번호 서비스 적용해보기
    private String receiverName;
    private String contactNumber;

    private String addressZonecode;
    @Enumerated(value = EnumType.STRING)
    private AddressType addressType;
    private String address;
    private String addressDetail;
    private String deliveryRequest;

    private Long totalOrderAmount;
    private Long totalDiscountAmount;
    private Long totalDeliveryAmount;
    private Long totalRealPayment;

    /* 주문 취소 부분 취소 여부 - 기본값 false */
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isOrderCancel = false;
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isPartialCancel = false;

    /* 주문 날짜, 취소 날짜 */
    @CreatedDate
    private LocalDateTime orderDate;

    private LocalDateTime cancelDate;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "orderCode")
    private List<StoreOrder> storeOrders;

    private Order(Long memberCode,String orderName, String receiverName, String contactNumber, String addressZonecode, AddressType addressType, String address, String addressDetail, String deliveryRequest,
                 Long totalOrderAmount, Long totalDiscountAmount, Long totalDeliveryAmount, Long totalRealPayment,
                  List<StoreOrder> storeOrders) {
        this.memberCode = memberCode;
        this.orderName = orderName;
        this.receiverName = receiverName;
        this.contactNumber = contactNumber;
        this.addressZonecode = addressZonecode;
        this.addressType = addressType;
        this.address = address;
        this.addressDetail = addressDetail;
        this.deliveryRequest = deliveryRequest;
        this.totalOrderAmount = totalOrderAmount;
        this.totalDiscountAmount = totalDiscountAmount;
        this.totalDeliveryAmount = totalDeliveryAmount;
        this.totalRealPayment = totalRealPayment;
        this.storeOrders = storeOrders;
    }

    public static Order of(Long memberCode, String orderName, String receiverName, String contactNumber, String addressZonecode, AddressType addressType, String address,String addressDetail, String deliveryRequest, Long totalOrderAmount, Long totalDiscountAmount, Long totalDeliveryAmount, Long totalRealPayment,  List<StoreOrder> storeOrders) {
        return new Order(memberCode, orderName,
                receiverName, contactNumber,
                addressZonecode,addressType, address, addressDetail, deliveryRequest,
                totalOrderAmount, totalDiscountAmount, totalDeliveryAmount, totalRealPayment,
                storeOrders);
    }

    // 주문 - 총 주문금액
    public void totalOrderAmount(Long orderPrice) {
        this.totalOrderAmount += orderPrice;
    }

    // 주문 - 총 배송비
    public void totalDeliveryAmount(Long deliveryAmount) {
        this.totalDeliveryAmount += deliveryAmount;
    }

    // 주문 취소일
    public void setCancel(boolean isOrderCancel, boolean isPartialCancel) {
        this.isOrderCancel = isOrderCancel;
        this.isPartialCancel = isPartialCancel;
        if (isOrderCancel || isPartialCancel) {
            this.cancelDate = LocalDateTime.now();
        }
    }
}

