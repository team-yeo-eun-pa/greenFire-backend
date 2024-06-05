package yep.greenFire.greenfirebackend.inquiry.product.member.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yep.greenFire.greenfirebackend.inquiry.entity.InquiryContent;

import java.util.Date;

@Getter
@RequiredArgsConstructor
@Service
public class MemberProductInquiryResponse {

    //상품문의에서 보여줘야할 것들

    //상품관련
    private int productCode;
    private String inquiryTitle;
    private String inquiryDetail;
    private Date inquiryWriteDate;
    private String inquiryReplyStatus;

    public MemberProductInquiryResponse(int productCode, String inquiryTitle, String inquiryDetail, Date inquiryWriteDate, String inquiryReplyStatus) {
        this.productCode = productCode;
        this.inquiryTitle = inquiryTitle;
        this.inquiryDetail = inquiryDetail;
        this.inquiryWriteDate = inquiryWriteDate;
        this.inquiryReplyStatus = inquiryReplyStatus;
    }

//    public static MemberProductInquiryResponse from(InquiryContent inquiryContent) {
//
//        return new MemberProductInquiryResponse(
//                inquiryContent.getInquiryProduct(),
//                //어떻게 해결할 것인가.
//                inquiryContent.getInquiryTitle(),
//                inquiryContent.getInquiryDetail(),
//                inquiryContent.getInquiryWriteDate(),
//                inquiryContent.getInquiryReplyStatus()
//
//
//        );
//    }
}
