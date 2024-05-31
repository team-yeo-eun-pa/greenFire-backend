package yep.greenFire.greenfirebackend.user.seller.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import yep.greenFire.greenfirebackend.order.domain.type.AddressZonecode;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_store")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeCode;

    private Long sellerCode;

    private String storeName;
    private String storeInfo;

    private AddressZonecode addressZonecode;
    private String addressType;
    private String address;
    private String addressDetail;

    /* 스토어 배송비 관련 */
    private Long deliveryAmount;
    private Long freeDeliveryCondition;

    private Long reportCount;
    private LocalDateTime suspendedEndDate;

    private String storeStatus;
}