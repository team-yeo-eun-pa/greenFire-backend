package yep.greenFire.greenfirebackend.inquiry.site.dto.response;




public class InquiryCreateResponse {
    final int inquiryCode;

    final int memberCode;
    final String inquiryTitle;
    final String inquiryDetail;

    public InquiryCreateResponse(int inquiryCode, int memberCode, String inquiryTitle, String inquiryDetail) {
        this.inquiryCode = inquiryCode;
        this.memberCode = memberCode;
        this.inquiryTitle = inquiryTitle;
        this.inquiryDetail = inquiryDetail;
    }


//    public static InquiryResponse from(InquiryContent inquiryContent) {
//        return new MemberInquiryResponse(
//                inquiryContent.getInquiryCode(),
//                inquiryContent.getInquiryStatus(),
//                inquiryContent.getInquiryReply(),
//                inquiryContent.getInquiryReplyStatus(),
//                inquiryContent.getMemberCode()
//
//        );
//
//    }
}
