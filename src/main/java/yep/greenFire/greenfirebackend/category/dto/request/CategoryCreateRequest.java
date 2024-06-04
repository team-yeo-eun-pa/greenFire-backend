package yep.greenFire.greenfirebackend.category.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class CategoryCreateRequest {

    @NotBlank
    private final String categoryTitle;
    private final Long refCategoryCode;


}
