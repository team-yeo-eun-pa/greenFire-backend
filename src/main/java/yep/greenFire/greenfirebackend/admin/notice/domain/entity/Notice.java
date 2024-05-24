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
    private Integer noticeCode;
    private String noticeTitle;
    private String noticeContent;
    private Integer noticeView;
    private String noticeFixable;
    @Enumerated(value = EnumType.STRING)
    private NoticeStatusType noticeStatus = NoticeStatusType.ACTIVE;
    @CreatedDate
    private LocalDate noticeDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "noticeWriter", referencedColumnName = "memberCode")
    private Member noticeWriter;




    public Notice of(final String noticeTitle,
                     final String noticeFixable,
                     final LocalDate noticeDate,
                     final Member member
    ) {

        return new Notice(
                noticeTitle,
                noticeFixable,
                noticeDate,
                member
        );

    }

    public Notice(String noticeTitle, String noticeFixable, LocalDate noticeDate, Member member) {
        this.noticeTitle = noticeTitle;
        this.noticeFixable = noticeFixable;
        this.noticeDate = noticeDate;
        this.noticeWriter = member;
    }

}
