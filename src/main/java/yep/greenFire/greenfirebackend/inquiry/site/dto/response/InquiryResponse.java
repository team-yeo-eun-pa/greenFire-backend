package yep.greenFire.greenfirebackend.inquiry.site.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.inquiry.entity.InquiryContent;

import java.util.Date;

@Getter


public class InquiryResponse {

    //문의 목록 조회
    private final Integer inquiryCode;
    private final Date inquiryWriteDate;
    private final String inquiryTitle;
    private final String inquiryDetail;
    private final String inquiryReply;
    private final String inquiryReplyStatus;

    public InquiryResponse(
            Integer inquiryCode, Date inquiryWriteDate, String inquiryTitle,
            String inquiryDetail, String inquiryReply, String inquiryReplyStatus) {
        this.inquiryCode = inquiryCode;
        this.inquiryWriteDate = inquiryWriteDate;
        this.inquiryTitle = inquiryTitle;
        this.inquiryDetail = inquiryDetail;
        this.inquiryReply = inquiryReply;
        this.inquiryReplyStatus = inquiryReplyStatus;
    }

    public static InquiryResponse from(InquiryContent inquiryContent) {
        return new InquiryResponse(
                inquiryContent.getInquiryCode(),
                inquiryContent.getInquiryWriteDate(),
                inquiryContent.getInquiryTitle(),
                inquiryContent.getInquiryDetail(),
                inquiryContent.getInquiryReply(),
                inquiryContent.getInquiryReplyStatus()



        );

    }
}
