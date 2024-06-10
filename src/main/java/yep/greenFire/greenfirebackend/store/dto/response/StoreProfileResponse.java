package yep.greenFire.greenfirebackend.store.dto.response;

import lombok.Getter;
import yep.greenFire.greenfirebackend.delivery.domain.type.AddressType;
import yep.greenFire.greenfirebackend.store.domain.type.StoreStatus;

import java.time.LocalDateTime;

@Getter

public class StoreProfileResponse {

    private final Long storeCode;
    private final Long sellerCode;
    private final String storeName;
    private final String storeInfo;
    private final Long addressZonecode;
    private final AddressType addressType;
    private final String address;
    private final String addressDetail;
    private final Long deliveryAmount;
    private final Long freeDeliveryCondition;
    private final Long reportCount;
    private final LocalDateTime suspendedEndDate;
    private final StoreStatus storeStatus;

    public StoreProfileResponse(Long storeCode, Long sellerCode, String storeName, String storeInfo, Long addressZonecode, AddressType addressType, String address, String addressDetail, Long deliveryAmount, Long freeDeliveryCondition, Long reportCount, LocalDateTime suspendedEndDate, StoreStatus storeStatus) {
        this.storeCode = storeCode;
        this.sellerCode = sellerCode;
        this.storeName = storeName;
        this.storeInfo = storeInfo;
        this.addressZonecode = addressZonecode;
        this.addressType = addressType;
        this.address = address;
        this.addressDetail = addressDetail;
        this.deliveryAmount = deliveryAmount;
        this.freeDeliveryCondition = freeDeliveryCondition;
        this.reportCount = reportCount;
        this.suspendedEndDate = suspendedEndDate;
        this.storeStatus = storeStatus;
    }
}
