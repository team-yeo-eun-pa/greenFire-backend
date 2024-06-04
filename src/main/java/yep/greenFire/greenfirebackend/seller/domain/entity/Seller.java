package yep.greenFire.greenfirebackend.seller.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import yep.greenFire.greenfirebackend.seller.domain.type.ApplyStatus;
import yep.greenFire.greenfirebackend.store.domain.type.StoreStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_seller")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberCode;
    private Long memberId;
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

    // 입점 신청 관련
    private String memberPhone;
    private String applyContent;
    private LocalDateTime applyDatetime;
    private LocalDateTime applyProcessingDate;
    private LocalDateTime applyCancelDate;
    private String rejectReason;

//    private AddressZonecode addressZonecode;
//    private String addressType;
//    private String address;
//    private String addressDetail;

}
