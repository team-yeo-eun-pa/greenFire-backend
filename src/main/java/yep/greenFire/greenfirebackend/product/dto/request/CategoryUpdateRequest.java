package yep.greenFire.greenfirebackend.product.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CategoryUpdateRequest {

    private final String categoryTitle;

    private final Long refCategoryCode;
}
