package yep.greenFire.greenfirebackend.order.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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

    private String receiver;
    private String phone;

    private Long addressZipcode;
    private String addressSido;
    private String addressSigungu;
    private String addressDongeupmyeon;
    private String addressDetail;
    private String request;

}
