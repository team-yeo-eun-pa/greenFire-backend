package yep.greenFire.greenfirebackend.inquiry.site.dto.response;




public class InquiryCreateResponse {
    final int inquiryCode;
    final String inquiryStatus;
    final String inquiryReply;
    final String inquiryReplyStatus;
    final int memberCode;


    public InquiryCreateResponse(int inquiryCode, String inquiryStatus, String inquiryReply, String inquiryReplyStatus, int memberCode) {
        this.inquiryCode = inquiryCode;
        this.inquiryStatus = inquiryStatus;
        this.inquiryReply = inquiryReply;
        this.inquiryReplyStatus = inquiryReplyStatus;
        this.memberCode = memberCode;
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
