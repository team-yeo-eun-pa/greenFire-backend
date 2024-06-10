package yep.greenFire.greenfirebackend.store.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.delivery.domain.type.AddressType;
import yep.greenFire.greenfirebackend.store.domain.type.StoreStatus;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class StoreProfileUpdateRequest {

    @NotBlank
    private final String storeName;
    @NotBlank
    private final String storeInfo;

    private final Long addressZonecode;

    private final AddressType addressType;

    private final String address;

    private final String addressDetail;

    @Min(value = 0)
    private final Long deliveryAmount;

    private final Long freeDeliveryCondition;

    private final Long reportCount;

    private final LocalDateTime suspendedEndDate;

    private final StoreStatus storeStatus;

}
