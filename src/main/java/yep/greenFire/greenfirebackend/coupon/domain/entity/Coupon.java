package yep.greenFire.greenfirebackend.coupon.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_coupon")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long couponCode;

    private Long couponCreationCode;
    private Long memberCode;
    @CreatedDate
    private LocalDateTime publicationDate;
    private LocalDateTime expirationDate;
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isCouponUsed = false;
    private Long orderDetailCode;

    private LocalDateTime usedDate;

    /* 만료 일자
    -> 쿠폰 제작 테이블에서 사용 가능 기간을 받아와서
      발행 일자 + 사용 가능 기간 = 만료 일자 */

    /* 만료 일자가 지나면 쿠폰 사용 불가 로직 */

    /* 쿠폰 사용 일자 */
    public void setUsedDate(boolean isCouponUsed, Long orderDetailCode) {
        this.isCouponUsed = isCouponUsed;
        if (isCouponUsed && orderDetailCode != null) {
            this.usedDate = LocalDateTime.now();
        }
    }
}
