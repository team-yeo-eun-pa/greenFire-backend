package yep.greenFire.greenfirebackend.delivery.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import yep.greenFire.greenfirebackend.delivery.domain.type.AddressType;

@Entity
@Table(name = "tbl_delivery_address")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class DeliveryAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryAddressCode;

    private Long memberCode;

    private String deliveryAddressName;

//    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT true")
//    private Boolean isOrdinaryAddress = true;
    @Column(nullable = false)
    private Boolean isOrdinaryAddress = true;

    private String receiverName;
    private String contactNumber;

    private String addressZonecode;
    @Enumerated(value = EnumType.STRING)
    private AddressType addressType;
    private String address;
    private String addressDetail;
    private String deliveryRequest;

    private DeliveryAddress(Long memberCode, String deliveryAddressName, Boolean isOrdinaryAddress, String receiverName, String contactNumber, String addressZonecode, AddressType addressType, String address, String addressDetail, String deliveryRequest) {
        this.memberCode = memberCode;
        this.deliveryAddressName = deliveryAddressName;
        this.isOrdinaryAddress = isOrdinaryAddress;
        this.receiverName = receiverName;
        this.contactNumber = contactNumber;
        this.addressZonecode = addressZonecode;
        this.addressType = addressType;
        this.address = address;
        this.addressDetail = addressDetail;
        this.deliveryRequest = deliveryRequest;
    }

    public static DeliveryAddress of(Long memberCode,
                                     String deliveryAddressName,
                                     Boolean isOrdinaryAddress,
                                     String receiverName, String contactNumber,
                                     String addressZonecode, AddressType addressType, String address, String addressDetail, String deliveryRequest) {

    return new DeliveryAddress(memberCode,
            deliveryAddressName,
            isOrdinaryAddress,
            receiverName, contactNumber,
            addressZonecode, addressType, address, addressDetail, deliveryRequest);
    }

    // 새로운 배송지를 기본 배송지로 등록할때, 기존의 기본 배송지는 false
    public void deactivateOrdinaryAddress() {
        this.isOrdinaryAddress = false;
    }
}
