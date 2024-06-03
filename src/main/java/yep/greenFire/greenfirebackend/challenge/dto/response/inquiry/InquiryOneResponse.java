package yep.greenFire.greenfirebackend.challenge.dto.response.inquiry;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yep.greenFire.greenfirebackend.inquiry.member.entity.InquiryContent;

import java.util.Date;


@Getter
@RequiredArgsConstructor
@Service
public class InquiryOneResponse {

    //문의 등록 후 업데이트 된 문의 리스트 리턴할 때 쓰는 리스퐌스~
    private int inquiryCode;

    private Date inquiryWriteDate;

    private String inquiryTitle;

    private String inquiryReplyStatus;


    public InquiryOneResponse(int inquiryCode, Date inquiryWriteDate, String inquiryTitle, String inquiryReplyStatus) {
        this.inquiryCode = inquiryCode;
        this.inquiryWriteDate = inquiryWriteDate;
        this.inquiryTitle = inquiryTitle;
        this.inquiryReplyStatus = inquiryReplyStatus;
    }

    public static InquiryOneResponse from(InquiryContent newInquiry) {

        return new InquiryOneResponse(
                newInquiry.getInquiryCode(),
                newInquiry.getInquiryWriteDate(),
                newInquiry.getInquiryTitle(),
                newInquiry.getInquiryReplyStatus()
        );
    }
}
