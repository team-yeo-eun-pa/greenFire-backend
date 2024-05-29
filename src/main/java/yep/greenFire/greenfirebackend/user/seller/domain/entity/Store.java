package yep.greenFire.greenfirebackend.user.seller.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "tbl_store")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeCode;

    private Long sellerCode;
    private Long memberCode;

    private String storeStatus;

    private String storeRepresentativeName;
    private String storeAddress;
    private String storeInfo;

    /* 스토어 배송비 관련 */
    private Long deliveryAmount;
    /* 무료 배송 조건 */
    private Long freeShippingLimit;
}
