package yep.greenFire.greenfirebackend.challenge.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Table(name = "tbl_inquiry")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)

public class InquiryContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inquiryCode;
    private int memberCode;

    private Date inquiryWriteDate;
    private String inquiryStatus;
    private String inquiryDetail;
    private String inquiryTitle;
    private Date inquiryModifyDate;
    private Date inquiryDeleteDate;
    private String inquiryReply;
    private String inquiryReplyStatus;




    public InquiryContent(int inquiryCode, int memberCode, Date inquiryWriteDate, String inquiryStatus, String inquiryDetail, String inquiryTitle, Date inquiryModifyDate, Date inquiryDeleteDate, String inquiryReply, String inquiryReplyStatus, String memberId, String memberName, String memberEmail) {
        this.inquiryCode = inquiryCode;
        this.memberCode = memberCode;
        this.inquiryWriteDate = inquiryWriteDate;
        this.inquiryStatus = inquiryStatus;
        this.inquiryDetail = inquiryDetail;
        this.inquiryTitle = inquiryTitle;
        this.inquiryModifyDate = inquiryModifyDate;
        this.inquiryDeleteDate = inquiryDeleteDate;
        this.inquiryReply = inquiryReply;
        this.inquiryReplyStatus = inquiryReplyStatus;

    }


//    public static InquiryContent of(int memberCode, String memberId, String memberName, String memberEmail) {
//
//
//
//
//        )
//    }
}