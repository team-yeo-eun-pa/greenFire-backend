package yep.greenFire.greenfirebackend.delivery.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DeliveryAddressRequest {

    private String deliveryAddressName;

    // 기본 배송지 여부
    private Boolean isOrdinaryAddress;

    private String receiverName;
    private String contactNumber;

    private String addressZonecode;
    private String addressType;
    private String address;
    private String addressDetail;
    private String deliveryRequest;

}
