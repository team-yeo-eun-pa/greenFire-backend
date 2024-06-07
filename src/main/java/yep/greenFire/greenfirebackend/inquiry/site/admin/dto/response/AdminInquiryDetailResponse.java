package yep.greenFire.greenfirebackend.inquiry.site.admin.dto.response;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yep.greenFire.greenfirebackend.inquiry.entity.InquiryContent;

import java.util.Date;

@Getter
@Service
@RequiredArgsConstructor
public class AdminInquiryDetailResponse {

    // 로그인한 관리자가 마이페이지에서 등록된 문의를 볼 때 필요한 것들이 뭐가 있을까.
    // 상세 문의 내역 보기 전에 테이블에서 보여줄 것들. . ?
    private int inquiryCode;
    private Date inquiryWriteDate;
    private int memberCode;
    private String inquiryTitle;
    private String inquiryDetail;
    private String inquiryStatus;
    private String inquiryReplyStatus;

    public AdminInquiryDetailResponse(int inquiryCode, Date inquiryWriteDate, int memberCode, String inquiryDetail, String inquiryStatus, String inquiryReplyStatus) {
        this.inquiryCode = inquiryCode;
        this.inquiryWriteDate = inquiryWriteDate;
        this.memberCode = memberCode;
        this.inquiryDetail = inquiryDetail;
        this.inquiryStatus = inquiryStatus;
        this.inquiryReplyStatus = inquiryReplyStatus;
    }


//    public static AdminInquiryDetailResponse from(InquiryContent adminInquiryContent) {
//        return new AdminInquiryDetailResponse(
//                adminInquiryContent.getInquiryCode(),
//                adminInquiryContent.getInquiryWriteDate(),
//                adminInquiryContent.getMemberCode(),
//                adminInquiryContent.getInquiryDetail(),
//                adminInquiryContent.getInquiryStatus(),
//                adminInquiryContent.getInquiryReplyStatus()
//        );
//    }
}
