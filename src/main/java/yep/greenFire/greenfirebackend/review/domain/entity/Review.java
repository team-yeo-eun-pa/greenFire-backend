package yep.greenFire.greenfirebackend.review.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import yep.greenFire.greenfirebackend.member.domain.entity.Member;
import yep.greenFire.greenfirebackend.notice.domain.type.NoticeStatusType;
import yep.greenFire.greenfirebackend.product.domain.entity.Product;
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
    private String idReply;
    @Enumerated(value = EnumType.STRING)
    private ReviewReplyStatus reviewReplyStatus = ReviewReplyStatus.Y;
    private Long productCode;
    private Long memberCode;


}
