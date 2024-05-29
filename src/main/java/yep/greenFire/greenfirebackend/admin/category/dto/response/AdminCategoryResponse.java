package yep.greenFire.greenfirebackend.admin.category.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.admin.category.domain.entity.Category;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminCategoryResponse {

    private Long categoryCode;
    private String categoryTitle;
    private Long refCategoryCode;

    public AdminCategoryResponse(Long categoryCode,String categoryTitle, Long refCategoryCode) {
        this.categoryCode=categoryCode;
        this.categoryTitle=categoryTitle;
        this.refCategoryCode=refCategoryCode;
    }


    public static AdminCategoryResponse from(final Category category){
        return new AdminCategoryResponse(
                category.getCategoryCode(),
                category.getCategoryTitle(),
                category.getRefCategoryCode()
        );
    }
}
