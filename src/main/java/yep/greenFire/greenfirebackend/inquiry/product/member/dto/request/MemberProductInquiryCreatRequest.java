package yep.greenFire.greenfirebackend.inquiry.product.member.dto.request;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class MemberProductInquiryCreatRequest {

    private int productCode;
    private String productName;
    private int memberCode;
    private String inquiryTitle;
    private String inquiryDetail;
    private Date inquiryWriteDate;

}
