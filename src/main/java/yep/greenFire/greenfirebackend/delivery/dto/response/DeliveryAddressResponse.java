package yep.greenFire.greenfirebackend.delivery.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.delivery.domain.entity.DeliveryAddress;

@Getter
@RequiredArgsConstructor
public class DeliveryAddressResponse {

    private String deliveryAddressName;

    // 기본 배송지 여부
    private Boolean isOrdinaryAddress;

    private String receiverName;
    private String contactNumber;

    private Long addressZonecode;
    private String addressType;
    private String address;
    private String addressDetail;
    private String deliveryRequest;

    public DeliveryAddressResponse(DeliveryAddress deliveryAddress) {
        this.deliveryAddressName = deliveryAddress.getDeliveryAddressName();
        this.isOrdinaryAddress = deliveryAddress.getIsOrdinaryAddress();
        this.receiverName = deliveryAddress.getReceiverName();
        this.contactNumber = deliveryAddress.getContactNumber();
        this.addressZonecode = deliveryAddress.getAddressZonecode();
        this.addressType = deliveryAddress.getAddressType().toString();
        this.address = deliveryAddress.getAddress();
        this.addressDetail = deliveryAddress.getAddressDetail();
        this.deliveryRequest = deliveryAddress.getDeliveryRequest();
    }

}
