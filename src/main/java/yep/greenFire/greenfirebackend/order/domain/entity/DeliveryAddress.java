package yep.greenFire.greenfirebackend.order.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import yep.greenFire.greenfirebackend.order.domain.type.AddressZonecode;

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
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean isOrdinaryAddress = true;

    private String receiverName;
    private String contactNumber;

    private AddressZonecode addressZonecode;
    private String addressType;
    private String address;
    private String addressDetail;
    private String deliveryRequest;

}
