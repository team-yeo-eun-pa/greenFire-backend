package yep.greenFire.greenfirebackend.admin.notice.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import yep.greenFire.greenfirebackend.admin.notice.domain.type.NoticeStatusType;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_notice")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int noticeCode;
    private String noticeTitle;
    private String noticeFixable;
    @Enumerated(value = EnumType.STRING)
    private NoticeStatusType noticeStatus = NoticeStatusType.ACTIVE;
    @CreatedDate
    private LocalDate noticeDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "noticeWriter", referencedColumnName = "memberCode")
    private Member noticeWriter;
}
