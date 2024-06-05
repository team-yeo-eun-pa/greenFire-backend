package yep.greenFire.greenfirebackend.store.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import yep.greenFire.greenfirebackend.order.domain.type.AddressType;
import yep.greenFire.greenfirebackend.store.domain.type.StoreStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_store")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeCode;

    private Long sellerCode;

    private String storeName;
    private String storeInfo;

    private Long addressZonecode;
    @Enumerated(value = EnumType.STRING)
    private AddressType addressType;
    private String address;
    private String addressDetail;

    /* 스토어 배송비 관련 */
    private Long deliveryAmount;
    private Long freeDeliveryCondition;

    private Long reportCount;
    private LocalDateTime suspendedEndDate;
    @Enumerated(EnumType.STRING)
    private StoreStatus storeStatus;

    public void increaseStoreReportCount(long reportCount) {
        this.reportCount=reportCount;
    }
}
