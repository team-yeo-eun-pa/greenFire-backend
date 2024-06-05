package yep.greenFire.greenfirebackend.inquiry.site.member.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yep.greenFire.greenfirebackend.inquiry.entity.InquiryContent;

import java.util.Date;


@Getter
@RequiredArgsConstructor
@Service
public class MemberInquiryOneResponse {

    //문의 등록 후 업데이트 된 문의 리스트 리턴할 때 쓰는 리스퐌스~
    private int inquiryCode;

    private Date inquiryWriteDate;

    private String inquiryTitle;

    private String inquiryReplyStatus;


    public MemberInquiryOneResponse(int inquiryCode, Date inquiryWriteDate, String inquiryTitle, String inquiryReplyStatus) {
        this.inquiryCode = inquiryCode;
        this.inquiryWriteDate = inquiryWriteDate;
        this.inquiryTitle = inquiryTitle;
        this.inquiryReplyStatus = inquiryReplyStatus;
    }

//    public static MemberInquiryOneResponse from(InquiryContent newInquiry) {
//
//        return new MemberInquiryOneResponse(
//                newInquiry.getInquiryCode(),
//                newInquiry.getInquiryWriteDate(),
//                newInquiry.getInquiryTitle(),
//                newInquiry.getInquiryReplyStatus()
//        );
//    }
}
