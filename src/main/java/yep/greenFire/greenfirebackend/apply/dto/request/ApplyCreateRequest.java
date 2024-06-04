package yep.greenFire.greenfirebackend.apply.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApplyCreateRequest {

    @NotBlank
    private final String storeName;
    private final String businessImg;
    @NotBlank
    private final String businessNumber;
    @NotBlank
    private final String mosNumber;
    @NotBlank
    private final String storeType;
    @NotBlank
    private final String memberPhone;
    @NotBlank
    private final String applyContent;
}
