package yep.greenFire.greenfirebackend.admin.notice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CategoryCreateRequest {

    @NotNull
    private final Long categoryCode;
    @NotBlank
    private final String categoryTitle;
    private final Long refCategoryCode;


}
