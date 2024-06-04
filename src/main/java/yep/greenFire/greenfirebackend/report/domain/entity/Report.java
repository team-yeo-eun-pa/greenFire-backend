package yep.greenFire.greenfirebackend.report.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import yep.greenFire.greenfirebackend.challenge.domain.entity.Comment;
import yep.greenFire.greenfirebackend.user.review.domain.entity.Review;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_report")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportCode;

//    @Enumerated(EnumType.STRING)
    private String reportReason;

    private LocalDateTime reportDate;

    private String reportType;

    private Long storeCode;

    private Long memberCode;

    @ManyToOne
    @JoinColumn(name = "comment_code")
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "review_code")
    private Review review;
}
