package yep.greenFire.greenfirebackend.store.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
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

    public Store(Long sellerCode, String storeName, StoreStatus storeStatus) {
        this.sellerCode = sellerCode;
        this.storeName = storeName;
        this.storeStatus = storeStatus;
    }

    public static Store of(final Long sellerCode, final String storeName, final StoreStatus storeStatus) {
        return new Store(
                sellerCode,
                storeName,
                storeStatus
        );
    }

    public void increaseStoreReportCount(long reportCount) {
        this.reportCount=reportCount;
    }

    public void modifyProfile(Long sellerCode, String storeName, String storeInfo, Long addressZonecode, AddressType addressType, String address, String addressDetail, Long deliveryAmount, Long freeDeliveryCondition) {
        this.sellerCode = sellerCode;
        this.storeName = storeName;
        this.storeInfo = storeInfo;
        this.addressZonecode = addressZonecode;
        this.addressType = addressType;
        this.address = address;
        this.addressDetail = addressDetail;
        this.deliveryAmount = deliveryAmount;
        this.freeDeliveryCondition = freeDeliveryCondition;
    }

    public void closeStore(Long sellerCode, LocalDateTime suspendedEndDate) {
        this.sellerCode = sellerCode;
        this.storeStatus = StoreStatus.CLOSED;
        this.suspendedEndDate = suspendedEndDate;
    }
}
