package yep.greenFire.greenfirebackend.order.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//import yep.greenFire.greenfirebackend.order.dto.request.OrderCreateRequest;

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
    private Integer memberCode;

    /* 배송지 - 수령자 연락처 주소 요청사항 //카카오 우편번호 서비스 적용해보기 */
    private String receiver;
    private String phone;

    private Long addressZipcode;
    private String addressSido;
    private String addressSigungu;
    private String addressDongeupmyeon;
    private String addressDetail;
    private String request;

    /* 주문금액, 총할인, 배송비, 실결제금액 */
    private Long orderPrice;
    private Long discountAmount;
    private Long deliveryAmount;
    private Long realPayment;

    /* 주문 날짜 */
    @CreatedDate
    private LocalDateTime orderDate;

    /* 주문 취소 여부 - 기본값 false */
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isOrderCancel = false;


    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "orderCode")
    private List<StoreOrder> storeOrder;


    public Order(Integer memberCode, String receiver, String phone, Long addressZipcode, String addressSido, String addressSigungu, String addressDongeupmyeon, String addressDetail, String request,
//                 Long orderPrice, Long discountAmount, Long deliveryAmount, Long realPayment,
                 List<StoreOrder> storeOrder) {
        this.memberCode = memberCode;
        this.receiver = receiver;
        this.phone = phone;
        this.addressZipcode = addressZipcode;
        this.addressSido = addressSido;
        this.addressSigungu = addressSigungu;
        this.addressDongeupmyeon = addressDongeupmyeon;
        this.addressDetail = addressDetail;
        this.request = request;
//        this.orderPrice = orderPrice;
//        this.discountAmount = discountAmount;
//        this.deliveryAmount = deliveryAmount;
//        this.realPayment = realPayment;
        this.storeOrder = storeOrder;
    }


    public static Order of(Integer memberCode, String receiver, String phone, Long addressZipcode, String addressSido, String addressSigungu, String addressDongeupmyeon, String addressDetail, String request, /* Long orderPrice, Long discountAmount, Long deliveryAmount, Long realPayment,*/ List<StoreOrder> storeOrders) {
        return new Order(memberCode,
                receiver, phone,
                addressZipcode,addressSido, addressSigungu, addressDongeupmyeon, addressDetail, request,
//                orderPrice, discountAmount, deliveryAmount, realPayment,
                storeOrders
                );
    }
}

