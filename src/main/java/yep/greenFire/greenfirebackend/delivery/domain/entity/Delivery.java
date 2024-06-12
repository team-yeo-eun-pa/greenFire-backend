package yep.greenFire.greenfirebackend.delivery.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import yep.greenFire.greenfirebackend.delivery.domain.type.DeliveryStatus;
import yep.greenFire.greenfirebackend.delivery.domain.type.DeliveryType;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_delivery")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryCode;
    private Long storeOrderCode;

    // 배송사, 운송장 번호
    private String deliveryCompany;
    private String transportNumber;

    // 일반 배송, 수거, 재배송
    @Enumerated(value = EnumType.STRING)
    private DeliveryType deliveryType;

    // 배송 중, 배송 완료
    @Enumerated(value = EnumType.STRING)
    private DeliveryStatus deliveryStatus = DeliveryStatus.SHIPPED; // 기본 값을 배송 중

    // 배송 일자, 완료 일자
    @CreatedDate
    private LocalDateTime deliveryDate;
    private LocalDateTime deliveryDoneDate;

    private Delivery(Long storeOrderCode, String deliveryCompany, String transportNumber, DeliveryType deliveryType) {
        this.storeOrderCode = storeOrderCode;
        this.deliveryCompany = deliveryCompany;
        this.transportNumber = transportNumber;
        this.deliveryType = deliveryType;
//        this.deliveryStatus = deliveryStatus;
//        this.deliveryDoneDate = deliveryDoneDate;
    }

    public static Delivery of(Long storeOrderCode, String deliveryCompany, String transportNumber, DeliveryType deliveryType) {
        return new Delivery(storeOrderCode, deliveryCompany, transportNumber,deliveryType);
    }
}
