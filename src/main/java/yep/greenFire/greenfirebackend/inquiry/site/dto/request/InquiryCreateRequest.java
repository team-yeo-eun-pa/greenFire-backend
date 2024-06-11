package yep.greenFire.greenfirebackend.inquiry.site.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class InquiryCreateRequest {

    private int memberCode;

    @NotBlank
    private String inquiryTitle;

    @NotBlank
    private String inquiryDetail;
}
