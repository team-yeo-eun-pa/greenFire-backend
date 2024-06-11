package yep.greenFire.greenfirebackend.inquiry.site.dto.response;

import lombok.Getter;
import yep.greenFire.greenfirebackend.inquiry.entity.InquiryContent;

@Getter
public class InquiryResponse {

    //문의 등록 전 문의 목록 조회

    private Integer inquiryCode;
    private String inquiryStatus;
    private String inquiryReply;
    private String inquiryReplyStatus;
    private Long memberCode;


    public InquiryResponse(Integer inquiryCode, String inquiryStatus, String inquiryReply, String inquiryReplyStatus, Long memberCode) {
        this.inquiryCode = inquiryCode;
        this.inquiryStatus = inquiryStatus;
        this.inquiryReply = inquiryReply;
        this.inquiryReplyStatus = inquiryReplyStatus;
        this.memberCode = memberCode;
    }



    public static InquiryResponse from(InquiryContent inquiryContent) {
        return new InquiryResponse(
                inquiryContent.getInquiryCode(),
                inquiryContent.getInquiryStatus(),
                inquiryContent.getInquiryReply(),
                inquiryContent.getInquiryReplyStatus(),
                inquiryContent.getMemberCode()

        );

    }
}
