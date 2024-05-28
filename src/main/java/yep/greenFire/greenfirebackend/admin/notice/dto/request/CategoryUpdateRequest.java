package yep.greenFire.greenfirebackend.admin.notice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CategoryUpdateRequest {

    @NotBlank
    private final String categoryTitle;

    private final Long refCategoryCode;
}
