package yep.greenFire.greenfirebackend.inquiry.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import yep.greenFire.greenfirebackend.inquiry.site.admin.dto.response.AdminInquiryResponse;

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
    @CreatedDate
    private Date inquiryWriteDate;
    private String inquiryStatus;
    private String inquiryDetail;
    private String inquiryTitle;
    @LastModifiedDate
    private Date inquiryModifyDate;
    private Date inquiryDeleteDate;
    private int replyMemberCode;
    private String inquiryReply;
    private String inquiryReplyStatus;

    private int inquiryCateCode;

    private int inquiryProduct;
    private int inquiryChallenge;



    public static InquiryContent of(
            final int inquiryCode, final int memberCode, final Date inquiryWriteDate,
            final String inquiryTitle, final String inquiryDetail, final String inquiryStatus) {
        return new InquiryContent();
    }

    public static InquiryContent of2(
            final int inquiryCode, final String inquiryWriteDate,
            final String inquiryTitle, final String inquiryDetail) {
        return new InquiryContent();
    }


    public void inquiryContent(int inquiryCode, int memberCode, Date inquiryWriteDate, String inquiryStatus, String inquiryDetail, String inquiryTitle, Date inquiryModifyDate, Date inquiryDeleteDate, String inquiryReply, String inquiryReplyStatus, String memberId, String memberName, String memberEmail) {
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


    public static AdminInquiryResponse from(AdminInquiryResponse adminInquiryResponse) {
        return new AdminInquiryResponse (
                adminInquiryResponse.getInquiryCode(),
                adminInquiryResponse.getInquiryWriteDate(),
                adminInquiryResponse.getMemberCode(),
                adminInquiryResponse.getInquiryTitle(),
                adminInquiryResponse.getInquiryDetail(),
                adminInquiryResponse.getInquiryStatus(),
                adminInquiryResponse.getInquiryReplyStatus()
        );




    }







}