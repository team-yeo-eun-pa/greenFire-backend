package yep.greenFire.greenfirebackend.inquiry.site.member.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;


@Getter
@RequiredArgsConstructor
public class InquiryCreateRequest {

    private int inquiryCode;


    @Min(value=1)
    private int memberCode;

    private Date inquiryWriteDate;

    @NotBlank
    private String inquiryTitle;

    @NotBlank
    private String inquiryDetail;

    @NotBlank
    private String inquiryStatus;

    @NotBlank
    private String memberId;

    @NotBlank
    private String memberName;

    @NotBlank
    private String memberEmail;



}
