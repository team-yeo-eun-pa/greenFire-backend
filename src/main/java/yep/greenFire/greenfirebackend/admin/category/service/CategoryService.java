package yep.greenFire.greenfirebackend.admin.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yep.greenFire.greenfirebackend.admin.category.domain.entity.Category;
import yep.greenFire.greenfirebackend.admin.category.domain.repository.CategoryRepository;
import yep.greenFire.greenfirebackend.admin.category.dto.request.CategoryCreateRequest;
import yep.greenFire.greenfirebackend.admin.category.dto.request.CategoryUpdateRequest;
import yep.greenFire.greenfirebackend.common.exception.NotFoundException;
import yep.greenFire.greenfirebackend.common.exception.type.ExceptionCode;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Long save(CategoryCreateRequest categoryCreateRequest) {

        final Category newCategory = Category.of(
                categoryCreateRequest.getCategoryCode(),
                categoryCreateRequest.getCategoryTitle(),
                categoryCreateRequest.getRefCategoryCode()
        );

        Category category = categoryRepository.save(newCategory);
        return category.getCategoryCode();
    }


    public void modify(Long categoryCode, CategoryUpdateRequest categoryUpdateRequest) {

        Category category = categoryRepository.findById(categoryCode)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_CATEGORY_CODE));

        category.modify(
                categoryUpdateRequest.getCategoryTitle(),
                categoryUpdateRequest.getRefCategoryCode()
        );
    }

    public void remove(Long categoryCode) {

        categoryRepository.deleteById(categoryCode);
    }
}
