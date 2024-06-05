package yep.greenFire.greenfirebackend.admin.notice.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import yep.greenFire.greenfirebackend.admin.notice.domain.type.NoticeStatusType;

import java.util.Date;

@Entity
@Table(name = "tbl_notice")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeCode;
    private String noticeTitle;
    private String noticeContent;
    private String noticeFixable;

    @Enumerated(value = EnumType.STRING)
    private NoticeStatusType noticeStatus = NoticeStatusType.ACTIVE;

    @CreatedDate
    private Date noticeDate;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "noticeWriter", referencedColumnName = "memberCode")
    private Long noticeWriter;

    public Notice(String noticeTitle, String noticeContent,String noticeFixable,Long noticeWriter) {
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeFixable=noticeFixable;
        this.noticeWriter=noticeWriter;
    }

    public static Notice of(final String noticeTitle, final String noticeContent, final String noticeFixable, Long noticeWriter) {
        return new Notice(
                noticeTitle,
                noticeContent,
                noticeFixable,
                noticeWriter
        );
    }

    public void modify(String noticeTitle, String noticeContent) {
        this.noticeTitle=noticeTitle;
        this.noticeContent=noticeContent;
    }
}
