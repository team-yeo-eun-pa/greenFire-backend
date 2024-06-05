package yep.greenFire.greenfirebackend.challenge.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class AdminInquiryCreateRequest {


    private int inquiryCode;
    private String inquiryWriteDate;
    private String inquiryTitle;
    private String inquiryDetail;


    @Min(value=1)
    private int memberCode;

    @NotBlank
    private String memberId;
    @NotBlank
    private String memberName;
    @NotBlank
    private String memberEmail;




}
