package yep.greenFire.greenfirebackend.user.seller.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import yep.greenFire.greenfirebackend.user.seller.domain.type.CouponStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_coupon_creation")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class CouponCreation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long couponCreationCode;
    private Long storeCode;

    private String couponName;
    private Long discountRate;
    private Long minPurchaseAmount;
    private Long maxDiscountAmount;
    private Long availableDate;
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isApplicableToAllProducts = false;
    @CreatedDate
    private LocalDateTime createDate;
    private Long totalCouponsIssued;
    private Long totalCouponsUsed;

    @Enumerated(value = EnumType.STRING)
    private CouponStatus couponStatus;

}
