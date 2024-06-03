package yep.greenFire.greenfirebackend.challenge.dto.response.inquiry;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yep.greenFire.greenfirebackend.inquiry.entity.InquiryContent;

@Getter
@RequiredArgsConstructor
@Service

public class InquiryResponse {

    //문의 등록 전 문의 목록 조회

    private int inquiryCode;
    private String inquiryStatus;
    private String inquiryReply;
    private String inquiryReplyStatus;
    private int memberCode;


    public InquiryResponse(int inquiryCode, String inquiryStatus, String inquiryReply, String inquiryReplyStatus, int memberCode) {
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
