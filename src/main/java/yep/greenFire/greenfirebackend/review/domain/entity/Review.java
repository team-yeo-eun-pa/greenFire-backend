package yep.greenFire.greenfirebackend.review.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import yep.greenFire.greenfirebackend.order.domain.type.OrderStatus;
import yep.greenFire.greenfirebackend.review.domain.type.ReviewReplyStatus;
import yep.greenFire.greenfirebackend.review.domain.type.ReviewStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_review")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewCode;
    private Long orderDetailCode;
    private String reviewTitle;
    private String reviewContent;
    @CreatedDate
    private LocalDateTime reviewDate;
    @Enumerated(value = EnumType.STRING)
    private ReviewStatus reviewStatus = ReviewStatus.ACTIVE;
    @CreatedDate
    private LocalDateTime modifyDate;
    @CreatedDate
    private LocalDateTime deleteDate;
    @Enumerated(value = EnumType.STRING)
    private ReviewReplyStatus reviewReplyStatus = ReviewReplyStatus.Y;
    private Long productCode;
    private Long memberCode;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.COMPLETED;

    public Review(String reviewTitle, String reviewContent, Long memberCode) {
        this.reviewTitle=reviewTitle;
        this.reviewContent=reviewContent;
        this.memberCode=memberCode;
    }

    public static Review of(Long memberCode, String reviewTitle, String reviewContent) {
        return new Review(reviewTitle, reviewContent, memberCode);

    }
}
