package yep.greenFire.greenfirebackend.seller.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import yep.greenFire.greenfirebackend.apply.domain.type.ApplyStatus;
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
    private Long sellerCode;
    private Long memberCode;
    private String storeName;
    private String storeRepresentativeName;
    @Enumerated(value = EnumType.STRING)
    private ApplyStatus applyStatus = ApplyStatus.CHECKING;
//    @Enumerated(value = EnumType.STRING)
//    private StoreStatus storeStatus = StoreStatus.OPEN;
    private String businessNumber;
    private String mosNumber;
    private String businessImg;
    private String storeType;

    // 입점 신청 관련
    private String applyContent;
    @CreatedDate
    private LocalDateTime applyDatetime;
    private LocalDateTime applyProcessingDate;
    private LocalDateTime applyCancelDate;
    private String rejectReason;

    private Seller(Long memberCode, String storeName, String storeRepresentativeName, String businessNumber, String mosNumber,
                   String storeType, String applyContent, String businessImg
    ) {
        this.memberCode = memberCode;
        this.storeName = storeName;
        this.storeRepresentativeName = storeRepresentativeName;
        this.businessNumber = businessNumber;
        this.mosNumber = mosNumber;
        this.storeType = storeType;
        this.applyContent = applyContent;
        this.businessImg = businessImg;
    }

    public static Seller of(
            final Long memberCode, final String storeName, final String storeRepresentativeName, final String businessNumber,
            final String mosNumber, final String storeType, final String applyContent, final String businessImg
    ) {
        return new Seller(
                memberCode,
                storeName,
                storeRepresentativeName,
                businessNumber,
                mosNumber,
                storeType,
                applyContent,
                businessImg
        );
    }

    public void modifyBusinessImg(String businessImg) {
        this.businessImg = businessImg;
    }

    public void modify(Long sellerCode, String storeName, String storeRepresentativeName, String businessNumber, String mosNumber, String storeType, String applyContent) {
        this.sellerCode = sellerCode;
        this.storeName = storeName;
        this.storeRepresentativeName = storeRepresentativeName;
        this.businessNumber = businessNumber;
        this.mosNumber = mosNumber;
        this.storeType = storeType;
        this.applyContent = applyContent;
    }

    public void cancel(ApplyStatus applyStatus) {
        this.applyStatus = applyStatus;
    }


//    private AddressZonecode addressZonecode;
//    private String addressType;
//    private String address;
//    private String addressDetail;

}
