package yep.greenFire.greenfirebackend.inquiry.product.seller.dto.response;

import lombok.Getter;
import yep.greenFire.greenfirebackend.inquiry.entity.InquiryContent;

import java.util.Date;

@Getter
public class SellerProductInquiryResponse {

    private int productCode;
    private String inquiryTitle;
    private String inquiryDetail;
    private Date inquiryWriteDate;
    private String inquiryReplyStatus;
    private String inquiryReply;


    public SellerProductInquiryResponse(int productCode, String inquiryTitle, String inquiryDetail, Date inquiryWriteDate, String inquiryReplyStatus, String inquiryReply) {
        this.productCode = productCode;
        this.inquiryTitle = inquiryTitle;
        this.inquiryDetail = inquiryDetail;
        this.inquiryWriteDate = inquiryWriteDate;
        this.inquiryReplyStatus = inquiryReplyStatus;
        this.inquiryReply = inquiryReply;
    }

//    public static SellerProductInquiryResponse from(InquiryContent inquiryContent) {
//
//        return new SellerProductInquiryResponse(
//
//                inquiryContent.getInquiryProduct(),
//                inquiryContent.getInquiryTitle(),
//                inquiryContent.getInquiryDetail(),
//                inquiryContent.getInquiryWriteDate(),
//                inquiryContent.getInquiryReplyStatus(),
//                inquiryContent.getInquiryReply()
//
//        );
//    }
}
