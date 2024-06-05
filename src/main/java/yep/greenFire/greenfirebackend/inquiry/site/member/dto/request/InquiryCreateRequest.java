package yep.greenFire.greenfirebackend.inquiry.site.member.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;


@Getter
@RequiredArgsConstructor
public class InquiryCreateRequest {

    private int memberCode;

    @NotBlank
    private String inquiryTitle;

    @NotBlank
    private String inquiryDetail;





}
