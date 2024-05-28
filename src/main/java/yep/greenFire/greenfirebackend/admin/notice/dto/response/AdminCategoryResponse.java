package yep.greenFire.greenfirebackend.admin.notice.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.admin.notice.domain.entity.Category;
import yep.greenFire.greenfirebackend.admin.notice.domain.repository.CategoryRepository;

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
