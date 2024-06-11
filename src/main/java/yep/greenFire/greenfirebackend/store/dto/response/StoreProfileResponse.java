package yep.greenFire.greenfirebackend.store.dto.response;

import lombok.Getter;
import yep.greenFire.greenfirebackend.apply.domain.type.ApplyStatus;
import yep.greenFire.greenfirebackend.order.domain.type.AddressType;
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

    private final String storeRepresentativeName;
    private final String businessNumber;
    private final String mosNumber;
    private final String businessImg;
    private final String applyContent;
    private final String storeType;
    private final LocalDateTime applyDatetime;
    private final LocalDateTime applyProcessingDate;
    private final LocalDateTime applyCancelDate;
    private final String rejectReason;
    private final ApplyStatus applyStatus;

    public StoreProfileResponse(Long storeCode, Long sellerCode, String storeName, String storeInfo, Long addressZonecode,
                                AddressType addressType, String address, String addressDetail, Long deliveryAmount,
                                Long freeDeliveryCondition, Long reportCount, LocalDateTime suspendedEndDate, StoreStatus storeStatus,
                                String storeRepresentativeName, String businessNumber, String mosNumber, String businessImg,
                                String applyContent, String storeType, LocalDateTime applyDatetime, LocalDateTime applyProcessingDate,
                                LocalDateTime applyCancelDate, String rejectReason, ApplyStatus applyStatus) {
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
        this.storeRepresentativeName = storeRepresentativeName;
        this.businessNumber = businessNumber;
        this.mosNumber = mosNumber;
        this.businessImg = businessImg;
        this.applyContent = applyContent;
        this.storeType = storeType;
        this.applyDatetime = applyDatetime;
        this.applyProcessingDate = applyProcessingDate;
        this.applyCancelDate = applyCancelDate;
        this.rejectReason = rejectReason;
        this.applyStatus = applyStatus;
    }
}
