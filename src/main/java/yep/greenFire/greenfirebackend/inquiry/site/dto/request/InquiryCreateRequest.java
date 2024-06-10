package yep.greenFire.greenfirebackend.inquiry.site.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;


@Getter
@RequiredArgsConstructor
public class InquiryCreateRequest {
   private int inquiryCode;

    private int memberCode;

    private Date inquiryWriteDate;

    private String inquiryStatus;

    private String inquiryTitle;

    private String inquiryDetail;

    private Date inquiryModifyDate;

    private Date inquiryDeleteDate;

    private String inquiryReply;

    private String inquiryReplyStatus;




}
