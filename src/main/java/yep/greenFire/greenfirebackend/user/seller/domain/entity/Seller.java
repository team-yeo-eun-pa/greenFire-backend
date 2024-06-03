package yep.greenFire.greenfirebackend.user.seller.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import yep.greenFire.greenfirebackend.store.domain.type.StoreStatus;
import yep.greenFire.greenfirebackend.user.seller.domain.type.ApplyStatus;

@Entity
@Table(name = "tbl_seller")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberCode;
    private Long sellerCode;
    private String storeName;
    private String storeRepresentativeName;
    @Enumerated(value = EnumType.STRING)
    private ApplyStatus applyStatus = ApplyStatus.APPLY;
    @Enumerated(value = EnumType.STRING)
    private StoreStatus storeStatus = StoreStatus.OPEN;
    private String businessNumber;
    private String mosNumber;
    private String businessImg;
    private String storeType;

//    private AddressZonecode addressZonecode;
//    private String addressType;
//    private String address;
//    private String addressDetail;

}
